package net.multyfora;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class SlipperyCopycat extends BCCopycatBlock {
    public SlipperyCopycat(Properties properties) {
        super(properties);
    }

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        return 0.098F;
    }
}
