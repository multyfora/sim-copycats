package net.multyfora.compat.copycats;

import com.copycatsplus.copycats.content.copycat.stairs.CopycatStairsBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CCLevititeStairs extends CopycatStairsBlock {
    public CCLevititeStairs(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.15f) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 1.0f;
            double z = pos.getZ() + random.nextDouble();
            level.addParticle(
                    net.minecraft.core.particles.ParticleTypes.END_ROD,
                    x, y, z,
                    0.0, 0.08, 0.0
            );
        }
    }
}
