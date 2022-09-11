package net.triplenold.wuxia.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JadeSwordBlock extends Block {
    public JadeSwordBlock(Properties properties) {
        super(properties);
    }
        public static final VoxelShape SHAPE = Block.box(0, 0, 0, 32, 1, 32);
}
