package net.multyfora;

import java.util.Collections;
import java.util.List;

import com.simibubi.create.content.decoration.copycat.CopycatModel;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.neoforge.client.model.data.ModelData;

public class BCCopycatFullModel extends CopycatModel {

    public BCCopycatFullModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected List<BakedQuad> getCroppedQuads(BlockState state, Direction side, RandomSource rand,
                                               BlockState material, ModelData wrappedData, RenderType renderType) {
        BakedModel model = getModelOf(material);
        List<BakedQuad> quads = model.getQuads(material, side, rand, wrappedData, renderType);
        return quads != null ? quads : Collections.emptyList();
    }
}
