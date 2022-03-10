package crispytwig.heftycrops.registry;

import crispytwig.heftycrops.HeftyCrops;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HeftyCrops.MOD_ID);

    // Crop Blocks
    public static final RegistryObject<Block> HEFTY_BEETROOT = BLOCKS.register("hefty_beetroot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_CARROT = BLOCKS.register("hefty_carrot",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HEFTY_POTATO = BLOCKS.register("hefty_potato",
            () -> new Block(Block.Properties.of(Material.VEGETABLE).strength(1.0F, 1.0F).sound(SoundType.SHROOMLIGHT).requiresCorrectToolForDrops()));

    public abstract boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, FluidState fluidstate);

}
