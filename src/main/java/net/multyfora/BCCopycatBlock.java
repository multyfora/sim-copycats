package net.multyfora;

import com.copycatsplus.copycats.content.copycat.block.CopycatBlockBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class BCCopycatBlock extends CopycatBlockBlock {

    public BCCopycatBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        return true;
    }
}
