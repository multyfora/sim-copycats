package net.multyfora.simcopycats;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LevititeCopycat extends BCCopycatBlock {

    public LevititeCopycat(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        ParticleHelper.spawnLevititeParticle(level, pos, random);
    }
}
