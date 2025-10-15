package zord.callmezord.zordscreatures;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;
import zord.callmezord.zordscreatures.entity.client.iguana.IguanaModel;
import zord.callmezord.zordscreatures.entity.client.iguana.IguanaRenderer;
import zord.callmezord.zordscreatures.entity.entities.Iguana;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = ZordsCreatures.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = ZordsCreatures.MODID, value = Dist.CLIENT)
public class ZordsCreaturesClient {
    public ZordsCreaturesClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

        //register the entity renderers
        EntityRenderers.register(EntitiesGeneral.IGUANA.get(), IguanaRenderer::new);

    }



    //ENTITY STUFF


    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Add our layer here.
        event.registerLayerDefinition(IguanaModel.LAYER_LOCATION, IguanaModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(EntitiesGeneral.IGUANA.get(), Iguana.createAttributes().build());

    }

    //ENTITY STUFF ENDS

}
