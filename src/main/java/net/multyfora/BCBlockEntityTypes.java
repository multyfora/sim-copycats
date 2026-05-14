package net.multyfora;

import com.simibubi.create.content.decoration.copycat.CopycatBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BCBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, simcopycats.MODID);

    private static final List<Supplier<Block>> COPYCAT_BLOCK_SUPPLIERS = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopycatBlockEntity>> COPYCAT =
        REGISTER.register("copycat", () -> {
            Block[] blocks = COPYCAT_BLOCK_SUPPLIERS.stream()
                .map(Supplier::get)
                .toArray(Block[]::new);
            return BlockEntityType.Builder.of(
                (pos, state) -> new CopycatBlockEntity(BCBlockEntityTypes.COPYCAT.get(), pos, state),
                blocks
            ).build(null);
        });

    public static void addCopycatBlock(Supplier<Block> supplier) {
        COPYCAT_BLOCK_SUPPLIERS.add(supplier);
    }
}
