package net.multyfora.simcopycats.compat.copycats;

import com.copycatsplus.copycats.content.copycat.slab.CopycatSlabBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.multyfora.simcopycats.ParticleHelper;

public class CCLevititeSlab extends CopycatSlabBlock {
    public CCLevititeSlab(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        ParticleHelper.spawnLevititeParticle(level, pos, random);
    }
}
