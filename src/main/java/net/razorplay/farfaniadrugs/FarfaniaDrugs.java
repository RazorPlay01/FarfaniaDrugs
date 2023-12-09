package net.razorplay.farfaniadrugs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.razorplay.farfaniadrugs.block.ModBlocks;
import net.razorplay.farfaniadrugs.effect.ModEffects;
import net.razorplay.farfaniadrugs.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarfaniaDrugs.MOD_ID)
public class FarfaniaDrugs {
    public static String localJarName = "";
    private static String currentShader = "";

    public static boolean isDefault = true;
    public static boolean shaderActive = false;

    public static final String MOD_ID = "farfaniadrugs";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarfaniaDrugs() {
        MinecraftForge.EVENT_BUS.register(this);
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (isDefault) {
            Minecraft.getInstance().gameRenderer.stopUseShader();
        }
    }

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && event.getInfo().getRenderViewEntity() == player) {
            if (Minecraft.getInstance().gameSettings.getPointOfView() == PointOfView.FIRST_PERSON) {
                if (!shaderActive) {
                    applyCustomShader(localJarName);
                }
            } else {
                if (shaderActive) {
                    removeCustomShader();
                }
            }
        }
    }

    public static void applyCustomShader(String shaderName) {
        if (!shaderActive || !currentShader.equals(shaderName)) {
            try {
                Minecraft.getInstance().gameRenderer.loadShader(new ResourceLocation(shaderName));
            } catch (Exception ignored) {

            }
            shaderActive = true;
            currentShader = shaderName;
        }
    }

    public static void removeCustomShader() {
        if (shaderActive) {
            Minecraft.getInstance().gameRenderer.stopUseShader();
            shaderActive = false;
        }
    }


    public static void loadCustomShader(String jarName) {
        localJarName = "farfaniadrugs:shaders/post/" + jarName;
        Minecraft.getInstance().displayGuiScreen((Screen) null);
        isDefault = false;
        shaderActive = false;
    }

    public static void loadShader(String jarName) {
        localJarName = "minecraft:shaders/post/" + jarName;
        Minecraft.getInstance().displayGuiScreen((Screen) null);
        isDefault = true;
        shaderActive = false;
    }
    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        if (!shaderActive) {
            applyCustomShader(localJarName);
        }
    }
}