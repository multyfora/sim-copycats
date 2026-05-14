package net.multyfora.compat.copycats;

import com.copycatsplus.copycats.CCBlockEntityTypes;
import com.copycatsplus.copycats.content.copycat.slab.CopycatMultiSlabModelCore;
import com.copycatsplus.copycats.content.copycat.slab.CopycatSlabBlock;
import com.copycatsplus.copycats.content.copycat.stairs.CopycatStairsBlock;
import com.copycatsplus.copycats.content.copycat.stairs.CopycatStairsModelCore;
import com.copycatsplus.copycats.foundation.copycat.model.CopycatModelCore;

import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.index.AeroDataComponents;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CopycatsCompat {

    private static final List<DeferredBlock<? extends Block>> COPYCAT_SLABS = new ArrayList<>();
    private static final List<DeferredBlock<? extends Block>> COPYCAT_STAIRS = new ArrayList<>();

    public static List<DeferredBlock<? extends Block>> getCopycatBlocks() {
        List<DeferredBlock<? extends Block>> all = new ArrayList<>();
        all.addAll(COPYCAT_SLABS);
        all.addAll(COPYCAT_STAIRS);
        return all;
    }

    public static boolean isLoaded() {
        return ModList.get().isLoaded("copycats");
    }

    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        if (!isLoaded()) return;
        for (var slab : COPYCAT_SLABS) {
            event.modify(CCBlockEntityTypes.MULTI_STATE_COPYCAT.get(), slab.get());
        }
        for (var stair : COPYCAT_STAIRS) {
            event.modify(CCBlockEntityTypes.COPYCAT.get(), stair.get());
        }
    }

    public static void registerBlocks(DeferredRegister.Blocks blockRegister, DeferredRegister.Items itemRegister) {
        // ======================== LEVITITE ========================

        registerSlabStairPair(blockRegister, itemRegister, "levitite_copycat_slab", "levitite_copycat_stairs",
                CCLevititeSlab::new, CCLevititeStairs::new,
                BlockBehaviour.Properties.of()
                        .strength(7.0f, 20.0f)
                        .sound(SoundType.AMETHYST)
                        .noOcclusion()
                        .requiresCorrectToolForDrops()
                        .lightLevel(state -> 10),
                props -> props.component(AeroDataComponents.LEVITATING, Levitating.LEVITITE));

        // ======================== FRAGILE ========================

        registerSlabStairPair(blockRegister, itemRegister, "fragile_copycat_slab", "fragile_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(0.5f, 1.0f)
                        .sound(SoundType.CROP)
                        .noOcclusion(),
                null);

        // ======================== SUPER LIGHT ========================

        registerSlabStairPair(blockRegister, itemRegister, "super_light_copycat_slab", "super_light_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(1.5f, 3.0f)
                        .sound(SoundType.WOOL)
                        .noOcclusion(),
                null);

        // ======================== LIGHT ========================

        registerSlabStairPair(blockRegister, itemRegister, "light_copycat_slab", "light_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(1.5f, 3.0f)
                        .sound(SoundType.WOOD)
                        .noOcclusion(),
                null);

        // ======================== NORMAL ========================

        registerSlabStairPair(blockRegister, itemRegister, "normal_copycat_slab", "normal_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(2.0f, 4.0f)
                        .sound(SoundType.STONE)
                        .noOcclusion(),
                null);

        // ======================== HEAVY ========================

        registerSlabStairPair(blockRegister, itemRegister, "heavy_copycat_slab", "heavy_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(3.0f, 6.0f)
                        .sound(SoundType.STONE)
                        .noOcclusion()
                        .requiresCorrectToolForDrops(),
                null);

        // ======================== SUPER HEAVY ========================

        registerSlabStairPair(blockRegister, itemRegister, "super_heavy_copycat_slab", "super_heavy_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(4.0f, 8.0f)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .requiresCorrectToolForDrops(),
                null);

        // ======================== ABSURDLY HEAVY ========================

        registerSlabStairPair(blockRegister, itemRegister, "absurdly_heavy_copycat_slab", "absurdly_heavy_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(5.0f, 10.0f)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .requiresCorrectToolForDrops(),
                null);

        // ======================== SLIPPERY ========================

        registerSlabStairPair(blockRegister, itemRegister, "slippery_copycat_slab", "slippery_copycat_stairs",
                CCSlipperySlab::new, CCSlipperyStairs::new,
                BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .friction(0.098F)
                        .sound(SoundType.GLASS)
                        .noOcclusion(),
                null);

        // ======================== STICKY ========================

        registerSlabStairPair(blockRegister, itemRegister, "sticky_copycat_slab", "sticky_copycat_stairs",
                CCStickySlab::new, CCStickyStairs::new,
                BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .friction(0.8F)
                        .sound(SoundType.HONEY_BLOCK)
                        .noOcclusion(),
                null);

        // ======================== BOUNCY ========================

        registerSlabStairPair(blockRegister, itemRegister, "bouncy_copycat_slab", "bouncy_copycat_stairs",
                CCBouncySlab::new, CCBouncyStairs::new,
                BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.SLIME_BLOCK)
                        .noOcclusion(),
                null);

        // ======================== WEIGHTLESS ========================

        registerSlabStairPair(blockRegister, itemRegister, "weightless_copycat_slab", "weightless_copycat_stairs",
                CopycatSlabBlock::new, CopycatStairsBlock::new,
                BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.WOOL)
                        .noOcclusion(),
                null);
    }

    private static void registerSlabStairPair(
            DeferredRegister.Blocks blockRegister, DeferredRegister.Items itemRegister,
            String slabName, String stairName,
            SlabFactory slabFactory, StairFactory stairFactory,
            BlockBehaviour.Properties properties,
            java.util.function.Consumer<Item.Properties> itemPropertyModifier) {

        DeferredBlock<? extends Block> slab = blockRegister.register(slabName, () -> slabFactory.create(properties));
        Item.Properties slabItemProps = new Item.Properties();
        if (itemPropertyModifier != null) itemPropertyModifier.accept(slabItemProps);
        itemRegister.register(slabName, () -> new BlockItem(slab.get(), slabItemProps));
        COPYCAT_SLABS.add(slab);

        DeferredBlock<? extends Block> stair = blockRegister.register(stairName, () -> stairFactory.create(properties));
        Item.Properties stairItemProps = new Item.Properties();
        if (itemPropertyModifier != null) itemPropertyModifier.accept(stairItemProps);
        itemRegister.register(stairName, () -> new BlockItem(stair.get(), stairItemProps));
        COPYCAT_STAIRS.add(stair);
    }

    public static Function<BakedModel, BakedModel> getModelFunction(Block block) {
        if (block instanceof CopycatSlabBlock)
            return m -> CopycatModelCore.createModel(m, new CopycatMultiSlabModelCore());
        if (block instanceof CopycatStairsBlock)
            return m -> CopycatModelCore.createModel(m, new CopycatStairsModelCore());
        return null;
    }

    @FunctionalInterface
    private interface SlabFactory {
        CopycatSlabBlock create(BlockBehaviour.Properties properties);
    }

    @FunctionalInterface
    private interface StairFactory {
        CopycatStairsBlock create(BlockBehaviour.Properties properties);
    }
}
