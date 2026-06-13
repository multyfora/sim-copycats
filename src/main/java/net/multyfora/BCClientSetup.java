package net.multyfora;

import net.multyfora.compat.copycats.CopycatsCompat;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(modid = simcopycats.MODID, value = Dist.CLIENT)
public class BCClientSetup {

    private static final List<DeferredBlock<? extends Block>> COPYCAT_BLOCKS = new ArrayList<>();

    public static void registerCopycatBlock(DeferredBlock<? extends Block> block) {
        COPYCAT_BLOCKS.add(block);
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        Map<ModelResourceLocation, BakedModel> modelRegistry = event.getModels();

        for (DeferredBlock<? extends Block> deferred : COPYCAT_BLOCKS) {
            Block block = deferred.get();
            Function<BakedModel, BakedModel> modelFunc = BCCopycatFullModel::new;
            Function<BakedModel, BakedModel> compatFunc = CopycatsCompat.getModelFunction(block);
            if (compatFunc != null) modelFunc = compatFunc;

            List<ModelResourceLocation> locations = new ArrayList<>();
            ResourceLocation blockRl = BuiltInRegistries.BLOCK.getKey(block);
            block.getStateDefinition().getPossibleStates().forEach(state -> {
                locations.add(BlockModelShaper.stateToModelLocation(blockRl, state));
            });

            for (ModelResourceLocation location : locations) {
                BakedModel original = modelRegistry.get(location);
                if (original != null) {
                    modelRegistry.put(location, modelFunc.apply(original));
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        BlockColor colorHandler = new BCCopycatBlockColor();
        for (DeferredBlock<? extends Block> deferred : COPYCAT_BLOCKS) {
            event.register(colorHandler, deferred.get());
        }
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        BlockColor blockColor = new BCCopycatBlockColor();
        for (DeferredBlock<? extends Block> deferred : COPYCAT_BLOCKS) {
            Block block = deferred.get();
            event.register((stack, tintIndex) -> {
                if (net.minecraft.client.Minecraft.getInstance().level == null)
                    return -1;
                return blockColor.getColor(block.defaultBlockState(), null, null, tintIndex);
            }, block.asItem());
        }
    }
}
