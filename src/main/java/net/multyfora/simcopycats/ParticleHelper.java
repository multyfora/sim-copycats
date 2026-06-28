package net.multyfora.simcopycats;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class ParticleHelper {
    public static void spawnLevititeParticle(Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.15f) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 1.0f;
            double z = pos.getZ() + random.nextDouble();
            level.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0, 0.08, 0.0);
        }
    }
}
