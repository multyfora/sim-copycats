package net.multyfora;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = simcopycats.MODID, value = Dist.CLIENT)
public class simcopycatsClient {

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        simcopycats.LOGGER.info("HELLO FROM CLIENT SETUP");
        simcopycats.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
