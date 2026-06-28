package net.multyfora.simcopycats;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class StickyCopycat extends BCCopycatBlock {
    public StickyCopycat(Properties properties) {
        super(properties);
    }

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        return 0.8F;
    }
}
