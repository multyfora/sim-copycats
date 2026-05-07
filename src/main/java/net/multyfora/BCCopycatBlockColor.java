package net.multyfora;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BCCopycatBlockColor implements BlockColor {

    @Override
    public int getColor(BlockState pState, @Nullable BlockAndTintGetter pLevel, @Nullable BlockPos pPos, int pTintIndex) {
        if (pLevel == null || pPos == null)
            return GrassColor.get(0.5D, 1.0D);
        BlockState material = BCCopycatBlock.getMaterial(pLevel, pPos);
        return Minecraft.getInstance().getBlockColors().getColor(material, pLevel, pPos, pTintIndex);
    }
}
