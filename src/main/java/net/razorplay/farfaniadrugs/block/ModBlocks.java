package net.razorplay.farfaniadrugs.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.razorplay.farfaniadrugs.FarfaniaDrugs;
import net.razorplay.farfaniadrugs.block.custom.CocaineCropBlock;
import net.razorplay.farfaniadrugs.block.custom.HallucinogenicMushroomsCropBlock;
import net.razorplay.farfaniadrugs.block.custom.MarijuanaCropBlock;
import net.razorplay.farfaniadrugs.block.custom.TobaccoCropBlock;
import net.razorplay.farfaniadrugs.item.ModItemGroup;
import net.razorplay.farfaniadrugs.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FarfaniaDrugs.MOD_ID);

    public static final RegistryObject<Block> TOBACCO_CROP = BLOCKS.register("tobacco_crop",
            () -> new TobaccoCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> COCAINE_CROP = BLOCKS.register("cocaine_crop",
            () -> new CocaineCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> MARIJUANA_CROP = BLOCKS.register("marijuana_crop",
            () -> new MarijuanaCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> HALLUCINOGENIC_MUSHROOMS_CROP = BLOCKS.register("hallucinogenic_mushrooms_crop",
            () -> new HallucinogenicMushroomsCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));

    /*public static final RegistryObject<Block> TEST = registerBlock("test",
            ()-> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));*/


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.FARFADRUGS_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
