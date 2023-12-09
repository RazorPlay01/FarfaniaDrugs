package net.razorplay.farfaniadrugs.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.effect.custom.*;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, FarfaniaDrugs.MOD_ID);

    public static final RegistryObject<FentanylEffect> FENTANYL_EFFECT = EFFECTS.register("fentanyl_effect", () ->
            new FentanylEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<CocaineEffect> COCAINE_EFFECT = EFFECTS.register("cocaine_effect", () ->
            new CocaineEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<BlackWidowPoisonEffect> BLACK_WIDOW_POISON_EFFECT = EFFECTS.register("black_widow_poison_effect", () ->
            new BlackWidowPoisonEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<CigaretteEffect> CIGARETTE_EFFECT = EFFECTS.register("cigarette_effect", () ->
            new CigaretteEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<HallucinogenicMushroomsEffect> HALLUCINOGENIC_MUSHROOMS_EFFECT = EFFECTS.register("hallucinogenic_mushrooms_effect", () ->
            new HallucinogenicMushroomsEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<MarijuanaEffect> MARIJUANA_EFFECT = EFFECTS.register("marijuana_effect", () ->
            new MarijuanaEffect(EffectType.HARMFUL, 0xFF69B4));
    public static final RegistryObject<MethamphetamineEffect> METHAMPHETAMINE_EFFECT = EFFECTS.register("methamphetamine_effect", () ->
            new MethamphetamineEffect(EffectType.HARMFUL, 0xFF69B4));


    public static void register(IEventBus modEventBus) {
        // Corregir la llamada al método register
        EFFECTS.register(modEventBus);
    }

    private static <T extends Effect> RegistryObject<T> createEffect(String name, T effect) {
        return RegistryObject.of(new ResourceLocation(FarfaniaDrugs.MOD_ID, name), ForgeRegistries.POTIONS);
    }
}
