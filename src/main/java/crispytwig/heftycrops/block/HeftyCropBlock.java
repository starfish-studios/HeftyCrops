package crispytwig.heftycrops.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class HeftyCropBlock extends BushBlock {
    public HeftyCropBlock() {
        super(Block.Properties.of(Material.VEGETABLE).strength(0.5F).sound(SoundType.SHROOMLIGHT));
    }
}