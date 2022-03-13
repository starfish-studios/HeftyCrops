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
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod(HeftyCrops.MOD_ID)
public class HeftyCrops {
    public static final String MOD_ID = "heftycrops";


    public HeftyCrops() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(HEFTY_BEETROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HEFTY_CARROT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(HEFTY_POTATO.get(), RenderType.cutout());

        /* ItemBlockRenderTypes.setRenderLayer(HEFTY_CABBAGE.get(), RenderType.cutout()); */
        /* ItemBlockRenderTypes.setRenderLayer(HEFTY_ONION.get(), RenderType.cutout()); */
        /* ItemBlockRenderTypes.setRenderLayer(HEFTY_TOMATO.get(), RenderType.cutout()); */
    }

    @SubscribeEvent
    public void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getWorld();
        BlockState postState = event.getState();

        // Vanilla Crop Blocks

        if (postState.is(Blocks.BEETROOTS) && postState.getValue(BeetrootBlock.AGE) == BeetrootBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_BEETROOT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.CARROTS) && postState.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_CARROT.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.POTATOES) && postState.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_POTATO.get().defaultBlockState(), 3);
            }
        }
        // Farmer's Delight Crop Blocks

        /*
        } else if (postState.is(Blocks.ONIONS) && postState.getValue(OnionBlock.AGE) == OnionBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_ONION.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.TOMATOES) && postState.getValue(TomatoBlock.AGE) == TomatoBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_TOMATO.get().defaultBlockState(), 3);
            }
        } else if (postState.is(Blocks.ONIONS) && postState.getValue(OnionBlock.AGE) == OnionBlock.MAX_AGE) {
            if (level.getRandom().nextInt(HEFTY_CROP_WEIGHT.get()) == 0) {
                level.setBlock(pos, HEFTY_ONION.get().defaultBlockState(), 3);
            }
        }

        */
    }

    // Blocks

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Vanilla Crop Blocks
    public static final RegistryObject<Block> HEFTY_BEETROOT = registerBlock("hefty_beetroot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_CARROT = registerBlock("hefty_carrot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_POTATO = registerBlock("hefty_potato",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));

/*    // Farmer's Delight Crop Blocks
    public static final RegistryObject<Block> HEFTY_ONION = registerBlock("hefty_onion",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_TOMATO = registerBlock("hefty_tomato",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.HONEY_BLOCK).requiresCorrectToolForDrops()));*/

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
        return registryObject;
    }

    // config

    public static ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.IntValue HEFTY_CROP_WEIGHT;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        HEFTY_CROP_WEIGHT = COMMON_BUILDER.comment("Chance of hefty crop spawning. Larger number = rarer. A value of 1 will guarantee a hefty crop spawns.").defineInRange("weight", 25, 1, 100);
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}