package net.multyfora;

import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.index.AeroDataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class BCBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(simcopycats.MODID);
    public static final DeferredRegister.Items ITEMS_REGISTER = DeferredRegister.createItems(simcopycats.MODID);

    public static final List<DeferredBlock<? extends Block>> ALL_COPYCATS = new ArrayList<>();

    // --- Levitite Copycat ---
    public static final DeferredBlock<LevititeCopycat> LEVITITE_COPYCAT = REGISTER.register("levitite_copycat",
            () -> {
                LevititeCopycat block = new LevititeCopycat(BlockBehaviour.Properties.of()
                        .strength(7.0f, 20.0f)
                        .sound(SoundType.AMETHYST)
                        .noOcclusion()
                        .requiresCorrectToolForDrops()
                        .lightLevel(state -> 10));
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> LEVITITE_COPYCAT_ITEM = ITEMS_REGISTER.register("levitite_copycat",
            () -> new BlockItem(LEVITITE_COPYCAT.get(), new Item.Properties()
                    .component(AeroDataComponents.LEVITATING, Levitating.LEVITITE)));

    // --- Fragile Copycat ---
    public static final DeferredBlock<FragileCopycat> FRAGILE_COPYCAT = REGISTER.register("fragile_copycat",
            () -> {
                FragileCopycat block = new FragileCopycat(BlockBehaviour.Properties.of()
                        .strength(0.5f, 1.0f)
                        .sound(SoundType.CROP)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> FRAGILE_COPYCAT_ITEM = ITEMS_REGISTER.register("fragile_copycat",
            () -> new BlockItem(FRAGILE_COPYCAT.get(), new Item.Properties()));

    // --- Super Light Copycat ---
    public static final DeferredBlock<SuperLightCopycat> SUPER_LIGHT_COPYCAT = REGISTER.register("super_light_copycat",
            () -> {
                SuperLightCopycat block = new SuperLightCopycat(BlockBehaviour.Properties.of()
                        .strength(1.5f, 3.0f)
                        .sound(SoundType.WOOL)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> SUPER_LIGHT_COPYCAT_ITEM = ITEMS_REGISTER.register("super_light_copycat",
            () -> new BlockItem(SUPER_LIGHT_COPYCAT.get(), new Item.Properties()));

    // --- Light Copycat ---
    public static final DeferredBlock<LightCopycat> LIGHT_COPYCAT = REGISTER.register("light_copycat",
            () -> {
                LightCopycat block = new LightCopycat(BlockBehaviour.Properties.of()
                        .strength(1.5f, 3.0f)
                        .sound(SoundType.WOOD)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> LIGHT_COPYCAT_ITEM = ITEMS_REGISTER.register("light_copycat",
            () -> new BlockItem(LIGHT_COPYCAT.get(), new Item.Properties()));

    // --- Normal Copycat ---
    public static final DeferredBlock<NormalCopycat> NORMAL_COPYCAT = REGISTER.register("normal_copycat",
            () -> {
                NormalCopycat block = new NormalCopycat(BlockBehaviour.Properties.of()
                        .strength(2.0f, 4.0f)
                        .sound(SoundType.STONE)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> NORMAL_COPYCAT_ITEM = ITEMS_REGISTER.register("normal_copycat",
            () -> new BlockItem(NORMAL_COPYCAT.get(), new Item.Properties()));

    // --- Heavy Copycat ---
    public static final DeferredBlock<HeavyCopycat> HEAVY_COPYCAT = REGISTER.register("heavy_copycat",
            () -> {
                HeavyCopycat block = new HeavyCopycat(BlockBehaviour.Properties.of()
                        .strength(3.0f, 6.0f)
                        .sound(SoundType.STONE)
                        .noOcclusion()
                        .requiresCorrectToolForDrops());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> HEAVY_COPYCAT_ITEM = ITEMS_REGISTER.register("heavy_copycat",
            () -> new BlockItem(HEAVY_COPYCAT.get(), new Item.Properties()));

    // --- Super Heavy Copycat ---
    public static final DeferredBlock<SuperHeavyCopycat> SUPER_HEAVY_COPYCAT = REGISTER.register("super_heavy_copycat",
            () -> {
                SuperHeavyCopycat block = new SuperHeavyCopycat(BlockBehaviour.Properties.of()
                        .strength(4.0f, 8.0f)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .requiresCorrectToolForDrops());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> SUPER_HEAVY_COPYCAT_ITEM = ITEMS_REGISTER.register("super_heavy_copycat",
            () -> new BlockItem(SUPER_HEAVY_COPYCAT.get(), new Item.Properties()));

    // --- Absurdly Heavy Copycat ---
    public static final DeferredBlock<AbsurdlyHeavyCopycat> ABSURDLY_HEAVY_COPYCAT = REGISTER.register("absurdly_heavy_copycat",
            () -> {
                AbsurdlyHeavyCopycat block = new AbsurdlyHeavyCopycat(BlockBehaviour.Properties.of()
                        .strength(5.0f, 10.0f)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .requiresCorrectToolForDrops());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> ABSURDLY_HEAVY_COPYCAT_ITEM = ITEMS_REGISTER.register("absurdly_heavy_copycat",
            () -> new BlockItem(ABSURDLY_HEAVY_COPYCAT.get(), new Item.Properties()));

    // --- Slippery Copycat ---
    public static final DeferredBlock<SlipperyCopycat> SLIPPERY_COPYCAT = REGISTER.register("slippery_copycat",
            () -> {
                SlipperyCopycat block = new SlipperyCopycat(BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.GLASS)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> SLIPPERY_COPYCAT_ITEM = ITEMS_REGISTER.register("slippery_copycat",
            () -> new BlockItem(SLIPPERY_COPYCAT.get(), new Item.Properties()));

    // --- Sticky Copycat ---
    public static final DeferredBlock<StickyCopycat> STICKY_COPYCAT = REGISTER.register("sticky_copycat",
            () -> {
                StickyCopycat block = new StickyCopycat(BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.HONEY_BLOCK)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> STICKY_COPYCAT_ITEM = ITEMS_REGISTER.register("sticky_copycat",
            () -> new BlockItem(STICKY_COPYCAT.get(), new Item.Properties()));

    // --- Bouncy Copycat ---
    public static final DeferredBlock<BouncyCopycat> BOUNCY_COPYCAT = REGISTER.register("bouncy_copycat",
            () -> {
                BouncyCopycat block = new BouncyCopycat(BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.SLIME_BLOCK)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> BOUNCY_COPYCAT_ITEM = ITEMS_REGISTER.register("bouncy_copycat",
            () -> new BlockItem(BOUNCY_COPYCAT.get(), new Item.Properties()));

    // --- Weightless Copycat ---
    public static final DeferredBlock<WeightlessCopycat> WEIGHTLESS_COPYCAT = REGISTER.register("weightless_copycat",
            () -> {
                WeightlessCopycat block = new WeightlessCopycat(BlockBehaviour.Properties.of()
                        .strength(1.0f, 2.0f)
                        .sound(SoundType.WOOL)
                        .noOcclusion());
                BCBlockEntityTypes.addCopycatBlock(() -> block);
                return block;
            });

    public static final DeferredItem<BlockItem> WEIGHTLESS_COPYCAT_ITEM = ITEMS_REGISTER.register("weightless_copycat",
            () -> new BlockItem(WEIGHTLESS_COPYCAT.get(), new Item.Properties()));

    public static void initCopycatClientRegistration() {
        if (!FMLEnvironment.dist.isClient()) return;
        ALL_COPYCATS.add(LEVITITE_COPYCAT);
        ALL_COPYCATS.add(FRAGILE_COPYCAT);
        ALL_COPYCATS.add(SUPER_LIGHT_COPYCAT);
        ALL_COPYCATS.add(LIGHT_COPYCAT);
        ALL_COPYCATS.add(NORMAL_COPYCAT);
        ALL_COPYCATS.add(HEAVY_COPYCAT);
        ALL_COPYCATS.add(SUPER_HEAVY_COPYCAT);
        ALL_COPYCATS.add(ABSURDLY_HEAVY_COPYCAT);
        ALL_COPYCATS.add(SLIPPERY_COPYCAT);
        ALL_COPYCATS.add(STICKY_COPYCAT);
        ALL_COPYCATS.add(BOUNCY_COPYCAT);
        ALL_COPYCATS.add(WEIGHTLESS_COPYCAT);

        for (DeferredBlock<? extends Block> copycat : ALL_COPYCATS) {
            BCClientSetup.registerCopycatBlock(copycat);
        }
    }
}
