package com.drmangotea.tfmg;



import com.drmangotea.tfmg.items.weapons.advanced_potato_cannon.AdvancedPotatoCannonRenderHandler;
import com.drmangotea.tfmg.items.weapons.flamethrover.FlamethrowerRenderHandler;
import com.drmangotea.tfmg.items.weapons.quad_potato_cannon.QuadPotatoCannonRenderHandler;
import com.drmangotea.tfmg.ponder.TFMGPonderIndex;
import com.drmangotea.tfmg.registry.TFMGPartialModels;
import com.drmangotea.tfmg.registry.TFMGParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CreateTFMGClient {
    public static final QuadPotatoCannonRenderHandler QUAD_POTATO_CANNON_RENDER_HANDLER = new QuadPotatoCannonRenderHandler();


    public static final AdvancedPotatoCannonRenderHandler ADVANCED_POTATO_CANNON_RENDER_HANDLER = new AdvancedPotatoCannonRenderHandler();

    public static final FlamethrowerRenderHandler FLAMETHROWER_RENDER_HANDLER = new FlamethrowerRenderHandler();


    public CreateTFMGClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        TFMGPartialModels.init();
        modEventBus.addListener(TFMGParticleTypes::registerFactories);
        modEventBus.register(this);



        ADVANCED_POTATO_CANNON_RENDER_HANDLER.registerListeners(forgeEventBus);
        QUAD_POTATO_CANNON_RENDER_HANDLER.registerListeners(forgeEventBus);
        FLAMETHROWER_RENDER_HANDLER.registerListeners(forgeEventBus);
    }


    @SubscribeEvent
    public void setup(final FMLClientSetupEvent event) {
        TFMGPonderIndex.register();
        TFMGPonderIndex.registerTags();
    }


}




