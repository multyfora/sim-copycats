package net.multyfora.simcopycats;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class EnvelopeCopycat extends BCCopycatBlock {

    public EnvelopeCopycat(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, fallDistance);
        } else {
            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            Vec3 vec3 = entity.getDeltaMovement();
            if (vec3.y < 0.0) {
                double scale = 0.65 * (entity instanceof LivingEntity ? 1.0 : 0.8);
                entity.setDeltaMovement(vec3.x, -vec3.y * scale, vec3.z);
            }
        }
    }
}
