package net.multyfora;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(simcopycats.MODID)
public class simcopycats {
    public static final String MODID = "sim_copycats";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BREAKABLE_COPYCATS_TAB = CREATIVE_MODE_TABS.register("breakable_copycats_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.sim_copycats"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> BCBlocks.LEVITITE_COPYCAT.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(BCBlocks.LEVITITE_COPYCAT_ITEM.get());
                output.accept(BCBlocks.FRAGILE_COPYCAT_ITEM.get());
                output.accept(BCBlocks.SUPER_LIGHT_COPYCAT_ITEM.get());
                output.accept(BCBlocks.LIGHT_COPYCAT_ITEM.get());
                output.accept(BCBlocks.NORMAL_COPYCAT_ITEM.get());
                output.accept(BCBlocks.HEAVY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.SUPER_HEAVY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.ABSURDLY_HEAVY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.SLIPPERY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.STICKY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.BOUNCY_COPYCAT_ITEM.get());
                output.accept(BCBlocks.WEIGHTLESS_COPYCAT_ITEM.get());
            }).build());

    public simcopycats(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        BCBlocks.REGISTER.register(modEventBus);
        BCBlocks.ITEMS_REGISTER.register(modEventBus);
        BCBlockEntityTypes.REGISTER.register(modEventBus);
        BCBlocks.initCopycatClientRegistration();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
