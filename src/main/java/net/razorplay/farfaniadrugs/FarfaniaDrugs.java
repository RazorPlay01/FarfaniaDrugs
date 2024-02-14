package net.razorplay.farfaniadrugs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.razorplay.farfaniadrugs.block.ModBlocks;
import net.razorplay.farfaniadrugs.commands.ModMessages;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItems;
import net.razorplay.farfaniadrugs.util.PlayerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarfaniaDrugs.MOD_ID)
public class FarfaniaDrugs {
    public static final ResourceLocation DEFAULT_SHADER = new ResourceLocation("minecraft:shaders/post/blit.json");
    public static ResourceLocation customShader;
    public static ResourceLocation currentShader;
    //public static boolean isDefault = true;
    public static boolean shaderActive = false;
    boolean isModEffectActive = false;
    public static final String MOD_ID = "farfaniadrugs";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarfaniaDrugs() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModMessages.register();

        modEventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.TOBACCO_CROP.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.COCAINE_CROP.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.HALLUCINOGENIC_MUSHROOMS_CROP.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.MARIJUANA_CROP.get(), RenderType.getCutout());
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        PlayerEntity player = Minecraft.getInstance().player;
        List<Effect> potionEffects = Arrays.asList(ModEffects.FENTANYL_EFFECT.get(), ModEffects.BLACK_WIDOW_POISON_EFFECT.get(),
                ModEffects.COCAINE_EFFECT.get(), ModEffects.MARIJUANA_EFFECT.get(), ModEffects.METHAMPHETAMINE_EFFECT.get(),
                ModEffects.HALLUCINOGENIC_MUSHROOMS_EFFECT.get(), ModEffects.CIGARETTE_EFFECT.get());
        isModEffectActive = PlayerUtil.hasPotionEffect(player, potionEffects);
        if (player != null) {
            CompoundNBT playerData = player.getPersistentData();
            if (!isModEffectActive) {
                Minecraft.getInstance().gameRenderer.stopUseShader();
                playerData.putBoolean("shaderActive", false);
            } else {
                if (!playerData.getBoolean("shaderActive") && !playerData.getString("currentShader").equals(playerData.getString("customShader"))) {
                    Minecraft.getInstance().gameRenderer.loadShader(new ResourceLocation(playerData.getString("customShader")));
                    playerData.putBoolean("shaderActive", true);
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && event.getInfo().getRenderViewEntity() == player) {
            CompoundNBT playerData = player.getPersistentData();
            if (!(Minecraft.getInstance().gameSettings.getPointOfView() == PointOfView.FIRST_PERSON)) {
                if (playerData.getBoolean("shaderActive")) {
                    Minecraft.getInstance().gameRenderer.stopUseShader();
                    playerData.putBoolean("shaderActive", false);
                }
                if (isModEffectActive) {
                    Minecraft.getInstance().gameSettings.setPointOfView(PointOfView.FIRST_PERSON);
                }
            }
        }
    }
}