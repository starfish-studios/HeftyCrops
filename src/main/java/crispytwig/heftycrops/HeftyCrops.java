package crispytwig.heftycrops;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(HeftyCrops.MOD_ID)
public class HeftyCrops {
    public static final String MOD_ID = "heftycrops";

    public HeftyCrops() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    // remove transparent bits
    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(HEFTY_BEETROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HEFTY_CARROT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HEFTY_POTATO.get(), RenderType.cutout());
    }

    // crop changing behavior
    @SubscribeEvent
    public void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState postState = event.getState();
        if (level.getRandom().nextInt(10) == 0) { // 10% chance
            if (postState.is(Blocks.BEETROOTS) && postState.getValue(BeetrootBlock.AGE) == BeetrootBlock.MAX_AGE) {
                level.setBlock(pos, HEFTY_BEETROOT.get().defaultBlockState(), 3);
            }
            if (postState.is(Blocks.CARROTS) && postState.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE) {
                level.setBlock(pos, HEFTY_CARROT.get().defaultBlockState(), 3);
            }
            if (postState.is(Blocks.POTATOES) && postState.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE) {
                level.setBlock(pos, HEFTY_POTATO.get().defaultBlockState(), 3);
            }
        }
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> HEFTY_BEETROOT = registerBlock("hefty_beetroot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_CARROT = registerBlock("hefty_carrot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_POTATO = registerBlock("hefty_potato",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        return registryObject;
    }
}