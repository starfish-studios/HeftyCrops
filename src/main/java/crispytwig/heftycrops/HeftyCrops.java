package crispytwig.heftycrops;

import crispytwig.heftycrops.registry.HCBlocks;
import crispytwig.heftycrops.registry.HCConfig;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;
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
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mod(HeftyCrops.MOD_ID)
public class HeftyCrops {
    public static final String MOD_ID = "heftycrops";

    public HeftyCrops() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        HCBlocks.BLOCKS.register(bus);
        HCBlocks.ITEMS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(HCBlocks.HEFTY_BEETROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HCBlocks.HEFTY_CARROT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HCBlocks.HEFTY_COCOA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HCBlocks.HEFTY_POTATO.get(), RenderType.cutout());
//        ItemBlockRenderTypes.setRenderLayer(HEFTY_CABBAGE.get(), RenderType.cutout()); /**/
        ItemBlockRenderTypes.setRenderLayer(HCBlocks.HEFTY_ONION.get(), RenderType.cutout());
//        ItemBlockRenderTypes.setRenderLayer(HEFTY_TOMATO.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState postState = event.getState();

        if (postState.is(Blocks.BEETROOTS) && postState.getValue(BeetrootBlock.AGE) == BeetrootBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HCConfig.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HCBlocks.HEFTY_BEETROOT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.CARROTS) && postState.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HCConfig.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HCBlocks.HEFTY_CARROT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.COCOA) && postState.getValue(CocoaBlock.AGE) == CocoaBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HCConfig.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HCBlocks.HEFTY_COCOA.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.POTATOES) && postState.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HCConfig.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HCBlocks.HEFTY_POTATO.get().defaultBlockState(), 3);
            }
        } else if (ModList.get().isLoaded("farmersdelight") && (postState.is(ModBlocks.ONION_CROP.get()) && postState.getValue(OnionBlock.AGE) == OnionBlock.MAX_AGE)) {
            if (level.getRandom().nextInt(HCConfig.HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HCBlocks.HEFTY_ONION.get().defaultBlockState(), 3);
            }
        }
            /* else if (postState.is(Blocks.TOMATOES) && postState.getValue(TomatoBlock.AGE) == TomatoBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_TOMATO.get().defaultBlockState(), 3);
            }
        */
    }
}