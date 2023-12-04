package net.razorplay.farfaniadrugs.item;

import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.item.custom.*;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarfaniaDrugs.MOD_ID);

    public static final RegistryObject<Item> VIUDA_NEGRA =
            ITEMS.register("viuda_negra", () -> new ViudaNegraItem(
                    new Item.Properties().maxDamage(1).group(FarfaniaDrugs.FARFADRUGSGROUP)));
    public static final RegistryObject<Item> COCAINE =
            ITEMS.register("cocaine", () -> new CocaItem(
                    new Item.Properties().maxDamage(1).group(FarfaniaDrugs.FARFADRUGSGROUP)));
    public static final RegistryObject<Item> MARIA =
            ITEMS.register("marihuana", () -> new MariaItem(
                    new Item.Properties().group(FarfaniaDrugs.FARFADRUGSGROUP).maxDamage(1)));

    public static final RegistryObject<Item> META =
            ITEMS.register("meta", () -> new MetaItem(
                    new Item.Properties().group(FarfaniaDrugs.FARFADRUGSGROUP).maxDamage(2)));
    public static final RegistryObject<Item> FENTANILO =
            ITEMS.register("fentanilo",
                    () -> new FentaniloItem(
                            new Item.Properties().group(FarfaniaDrugs.FARFADRUGSGROUP).maxDamage(1)));
    public static final RegistryObject<Item> HONGOS_ALUCINOGENOS =
            ITEMS.register("hongos_alucinogenos",
                    () -> new HongosAlucinogenos(
                            new Item.Properties().group(FarfaniaDrugs.FARFADRUGSGROUP).maxDamage(1)));

    public static final RegistryObject<Item> CIGARRILLO =
            ITEMS.register("cigarro",
                    () -> new CigarrilloItem(new Item.Properties().group(FarfaniaDrugs.FARFADRUGSGROUP).maxDamage(4)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
