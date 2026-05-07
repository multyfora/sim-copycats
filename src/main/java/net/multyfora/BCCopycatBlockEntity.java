package net.multyfora;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.redstone.RoseQuartzLampBlock;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.common.world.AuxiliaryLightManager;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import java.util.List;

public class BCCopycatBlockEntity extends SmartBlockEntity implements Clearable {

    public static final ModelProperty<BlockState> MATERIAL_PROPERTY = new ModelProperty<>();

    private BlockState material = AllBlocks.COPYCAT_BASE.getDefaultState();
    private ItemStack consumedItem = ItemStack.EMPTY;

    public BCCopycatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static BCCopycatBlockEntity create(BlockPos pos, BlockState state) {
        return new BCCopycatBlockEntity(BCBlockEntityTypes.COPYCAT.get(), pos, state);
    }

    public BlockState getMaterial() {
        return material;
    }

    public boolean hasCustomMaterial() {
        return !AllBlocks.COPYCAT_BASE.has(getMaterial());
    }

    public void setMaterial(BlockState blockState) {
        this.material = blockState;
        if (!level.isClientSide()) {
            notifyUpdate();
        } else {
            redraw();
        }
    }

    public boolean cycleMaterial() {
        if (material.hasProperty(TrapDoorBlock.HALF) && material.getOptionalValue(TrapDoorBlock.OPEN).orElse(false))
            setMaterial(material.cycle(TrapDoorBlock.HALF));
        else if (material.hasProperty(BlockStateProperties.FACING))
            setMaterial(material.cycle(BlockStateProperties.FACING));
        else if (material.hasProperty(BlockStateProperties.HORIZONTAL_FACING))
            setMaterial(material.setValue(BlockStateProperties.HORIZONTAL_FACING,
                material.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
        else if (material.hasProperty(BlockStateProperties.AXIS))
            setMaterial(material.cycle(BlockStateProperties.AXIS));
        else if (material.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
            setMaterial(material.cycle(BlockStateProperties.HORIZONTAL_AXIS));
        else if (material.hasProperty(BlockStateProperties.LIT))
            setMaterial(material.cycle(BlockStateProperties.LIT));
        else if (material.hasProperty(RoseQuartzLampBlock.POWERING))
            setMaterial(material.cycle(RoseQuartzLampBlock.POWERING));
        else
            return false;
        return true;
    }

    public ItemStack getConsumedItem() {
        return consumedItem;
    }

    public void setConsumedItem(ItemStack stack) {
        consumedItem = stack.copyWithCount(1);
        setChanged();
    }

    private void redraw() {
        if (!isVirtual())
            requestModelDataUpdate();
        if (level != null) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 16);
            updateLight();
        }
    }

    private void updateLight() {
        if (level != null) {
            AuxiliaryLightManager lightManager = level.getAuxLightManager(getBlockPos());
            if (lightManager != null)
                lightManager.setLightAt(getBlockPos(), material.getLightEmission(level, getBlockPos()));
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        updateLight();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    @Override
    public void clearContent() {
        material = AllBlocks.COPYCAT_BASE.getDefaultState();
        consumedItem = ItemStack.EMPTY;
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(tag, registries, clientPacket);
        BlockState prevMaterial = material;
        consumedItem = ItemStack.parseOptional(registries, tag.getCompound("Item"));
        if (!tag.contains("Material")) {
            consumedItem = ItemStack.EMPTY;
            return;
        }
        material = NbtUtils.readBlockState(blockHolderGetter(), tag.getCompound("Material"));
        if (clientPacket && prevMaterial != material)
            redraw();
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(tag, registries, clientPacket);
        tag.put("Item", consumedItem.saveOptional(registries));
        tag.put("Material", NbtUtils.writeBlockState(material));
    }

    @Override
    public ModelData getModelData() {
        return ModelData.builder()
            .with(MATERIAL_PROPERTY, material)
            .build();
    }
}
