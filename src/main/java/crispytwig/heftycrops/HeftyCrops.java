package crispytwig.heftycrops;

import crispytwig.heftycrops.registry.ModBlocks;
import crispytwig.heftycrops.registry.Config;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import vectorwing.farmersdelight.common.block.*;

@Mod(HeftyCrops.MOD_ID)
public class HeftyCrops {
    public static final String MOD_ID = "heftycrops";

    public HeftyCrops() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.ITEMS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        // Vanilla Crop Blocks
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_BEETROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_CARROT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_COCOA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_POTATO.get(), RenderType.cutout());

        // Farmer's Delight Crop Blocks
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_CABBAGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_ONION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_TOMATO.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState postState = event.getState();


        // Vanilla Crop Blocks
        if (postState.is(Blocks.BEETROOTS) && postState.getValue(BeetrootBlock.AGE) == BeetrootBlock.MAX_AGE) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_BEETROOT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.CARROTS) && postState.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_CARROT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.COCOA) && postState.getValue(CocoaBlock.AGE) == CocoaBlock.MAX_AGE) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_COCOA.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.POTATOES) && postState.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_POTATO.get().defaultBlockState(), 3);
            }


            // Farmer's Delight Crop Blocks
        } else if (ModList.get().isLoaded("farmersdelight") && (postState.is(vectorwing.farmersdelight.common.registry.ModBlocks.CABBAGE_CROP.get()) && postState.getValue(CabbageBlock.AGE) == CabbageBlock.MAX_AGE)) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_CABBAGE.get().defaultBlockState(), 3);
            }
        } else if (ModList.get().isLoaded("farmersdelight") && (postState.is(vectorwing.farmersdelight.common.registry.ModBlocks.ONION_CROP.get()) && postState.getValue(OnionBlock.AGE) == OnionBlock.MAX_AGE)) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_ONION.get().defaultBlockState(), 3);
            }
        } else if (ModList.get().isLoaded("farmersdelight") && (postState.is(vectorwing.farmersdelight.common.registry.ModBlocks.TOMATO_CROP.get()) && postState.getValue(TomatoBlock.AGE) == 7)) {
            if (level.getRandom().nextInt(Config.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, ModBlocks.HEFTY_TOMATO.get().defaultBlockState(), 3);
            }
        }
    }
}