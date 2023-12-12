package net.razorplay.farfaniadrugs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.razorplay.farfaniadrugs.block.ModBlocks;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarfaniaDrugs.MOD_ID)
public class FarfaniaDrugs {
    public static final ResourceLocation DEFAULT_SHADER = new ResourceLocation("minecraft:shaders/post/blit.json");
    public static ResourceLocation customShader;
    public static ResourceLocation currentShader;
    public static boolean isDefault = true;
    public static boolean shaderActive = false;

    public static final String MOD_ID = "farfaniadrugs";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarfaniaDrugs() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);

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
        if (isDefault) {
            Minecraft.getInstance().gameRenderer.stopUseShader();
        }
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && event.getInfo().getRenderViewEntity() == player) {
            if (Minecraft.getInstance().gameSettings.getPointOfView() == PointOfView.FIRST_PERSON) {
                if (!shaderActive) {
                    applyShader(customShader);
                }
            } else {
                if (shaderActive) {
                    removeShader();
                }
                if (!isDefault) {
                    Minecraft.getInstance().gameSettings.setPointOfView(PointOfView.FIRST_PERSON);
                }
            }
        }
    }

    public static void applyShader(ResourceLocation shader) {
        if (!shaderActive || !currentShader.equals(shader)) {
            if (shader == null) {
                Minecraft.getInstance().gameRenderer.loadShader(DEFAULT_SHADER);
            } else {
                Minecraft.getInstance().gameRenderer.loadShader(shader);
            }

            shaderActive = true;
            currentShader = shader;
        }
    }

    public static void removeShader() {
        if (shaderActive) {
            Minecraft.getInstance().gameRenderer.stopUseShader();
            shaderActive = false;
        }
    }

    public static void loadDefaultShader() {
        customShader = DEFAULT_SHADER;
        Minecraft.getInstance().displayGuiScreen((Screen) null);
        isDefault = true;
        shaderActive = false;
    }

    public static void loadCustomShader(ResourceLocation shader) {
        customShader = shader;
        Minecraft.getInstance().displayGuiScreen((Screen) null);
        isDefault = false;
        shaderActive = false;
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if (player != null) {
                FarfaniaDrugs.loadCustomShader(FarfaniaDrugs.currentShader);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player != null) {
            FarfaniaDrugs.loadCustomShader(FarfaniaDrugs.currentShader);
        }
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if (event.getPlayer() != null && event.getPlayer() instanceof PlayerEntity) {
            if (event.isWasDeath()) {
                loadDefaultShader();
            } else {
                if (shaderActive) {
                    loadCustomShader(currentShader);
                } else {
                    loadDefaultShader();
                }
            }
        }
    }
}