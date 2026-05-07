package net.multyfora;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BCBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, simcopycats.MODID);

    private static final List<Supplier<net.minecraft.world.level.block.Block>> COPYCAT_BLOCK_SUPPLIERS = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BCCopycatBlockEntity>> COPYCAT =
        REGISTER.register("copycat", () -> {
            net.minecraft.world.level.block.Block[] blocks = COPYCAT_BLOCK_SUPPLIERS.stream()
                .map(Supplier::get)
                .toArray(net.minecraft.world.level.block.Block[]::new);
            return BlockEntityType.Builder.of(BCCopycatBlockEntity::create, blocks).build(null);
        });

    public static void addCopycatBlock(Supplier<net.minecraft.world.level.block.Block> supplier) {
        COPYCAT_BLOCK_SUPPLIERS.add(supplier);
    }
}
