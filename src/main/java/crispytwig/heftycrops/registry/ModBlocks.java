package crispytwig.heftycrops.registry;

import crispytwig.heftycrops.HeftyCrops;
import crispytwig.heftycrops.block.CropBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HeftyCrops.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HeftyCrops.MOD_ID);

    // Vanilla Crop Blocks
    public static final RegistryObject<Block> HEFTY_BEETROOT = registerBlock("hefty_beetroot", CropBlock::new);
    public static final RegistryObject<Block> HEFTY_CARROT = registerBlock("hefty_carrot", CropBlock::new);
    public static final RegistryObject<Block> HEFTY_COCOA = registerBlock("hefty_cocoa", CropBlock::new);
    public static final RegistryObject<Block> HEFTY_POTATO = registerBlock("hefty_potato", CropBlock::new);

    // Farmer's Delight Crop Blocks
    public static final RegistryObject<Block> HEFTY_CABBAGE = registerBlock("hefty_cabbage", CropBlock::new);
    public static final RegistryObject<Block> HEFTY_ONION = registerBlock("hefty_onion", CropBlock::new);
    public static final RegistryObject<Block> HEFTY_TOMATO = registerBlock("hefty_tomato", CropBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
        return registryObject;
    }
}
