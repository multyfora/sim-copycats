package net.multyfora.simcopycats;

import net.multyfora.simcopycats.compat.copycats.CopycatsCompat;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.multyfora.simcopycats.simcopycats.compat.copycats.CopycatsCompat;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(simcopycats.MODID)
public class simcopycats {
    public static final String MODID = "sim_copycats";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_TABS.register("tab", () -> CreativeModeTab.builder()
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
                output.accept(BCBlocks.ENVELOPE_COPYCAT_ITEM.get());
                for (var block : CopycatsCompat.getCopycatBlocks()) {
                    output.accept(block.get().asItem());
                }
            }).build());

    public simcopycats(IEventBus modEventBus, ModContainer modContainer) {
        BCBlocks.REGISTER.register(modEventBus);
        BCBlocks.ITEMS_REGISTER.register(modEventBus);
        CopycatsCompat.registerBlocks(BCBlocks.REGISTER, BCBlocks.ITEMS_REGISTER);
        BCBlocks.registerAllFullBlockBEs();
        CREATIVE_TABS.register(modEventBus);
        modEventBus.addListener(CopycatsCompat::onBlockEntityTypeAddBlocks);

        if (FMLEnvironment.dist.isClient()) {
            BCBlocks.initClient();
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
    }
}
