package net.multyfora;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BCCopycatModel extends BakedModelWrapper<BakedModel> {

    private static final ModelProperty<BlockState> MATERIAL_PROPERTY = new ModelProperty<>();
    private static final ModelProperty<ModelData> WRAPPED_DATA_PROPERTY = new ModelProperty<>();

    public BCCopycatModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    public @NotNull ModelData getModelData(@NotNull BlockAndTintGetter level, @NotNull BlockPos pos,
                                              @NotNull BlockState state, @NotNull ModelData blockEntityData) {
        BlockState material = blockEntityData.get(BCCopycatBlockEntity.MATERIAL_PROPERTY);
        ModelData.Builder builder = ModelData.builder()
            .with(MATERIAL_PROPERTY, material);

        ModelData wrappedData = getModelOf(material).getModelData(level, pos, material, ModelData.EMPTY);
        builder.with(WRAPPED_DATA_PROPERTY, wrappedData);

        return builder.build();
    }

    @Override
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        BlockState material = data.get(MATERIAL_PROPERTY);
        if (material == null || material.isAir())
            return super.getRenderTypes(state, rand, data);

        ModelData wrappedData = data.get(WRAPPED_DATA_PROPERTY);
        if (wrappedData == null)
            wrappedData = ModelData.EMPTY;

        List<ChunkRenderTypeSet> sets = new ArrayList<>();
        sets.add(super.getRenderTypes(state, rand, data));

        BakedModel materialModel = getModelOf(material);
        sets.add(materialModel.getRenderTypes(material, rand, wrappedData));

        return ChunkRenderTypeSet.union(sets);
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
                                               @NotNull RandomSource rand, @NotNull ModelData modelData,
                                               @Nullable RenderType renderType) {
        BlockState material = modelData.get(MATERIAL_PROPERTY);
        if (material == null || material.isAir())
            return originalModel.getQuads(state, side, rand, modelData, renderType);

        ModelData wrappedData = modelData.get(WRAPPED_DATA_PROPERTY);
        if (wrappedData == null)
            wrappedData = ModelData.EMPTY;

        BakedModel materialModel = getModelOf(material);
        if (renderType != null && !materialModel.getRenderTypes(material, rand, wrappedData).contains(renderType))
            return Collections.emptyList();

        List<BakedQuad> quads = materialModel.getQuads(material, side, rand, wrappedData, renderType);

        return quads != null ? quads : Collections.emptyList();
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
                                               @NotNull RandomSource rand) {
        return getQuads(state, side, rand, ModelData.EMPTY, null);
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon(@NotNull ModelData data) {
        BlockState material = data.get(MATERIAL_PROPERTY);
        if (material == null || material.isAir())
            return originalModel.getParticleIcon(data);

        ModelData wrappedData = data.get(WRAPPED_DATA_PROPERTY);
        if (wrappedData == null)
            wrappedData = ModelData.EMPTY;

        return getModelOf(material).getParticleIcon(wrappedData);
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return originalModel.getParticleIcon();
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    public static BakedModel getModelOf(BlockState state) {
        return Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
    }
}
