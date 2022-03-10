
package crispytwig.heftycrops.blocks;

import crispytwig.heftycrops.registry.ModBlocks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class CropBlocks extends Block {

    public CropBlocks(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEFTY_POTATO.get(), renderType -> renderType == RenderType.cutoutMipped());
    }

    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, FluidState fluidstate) {
        return false;
    }
}
