package zord.callmezord.zordscreatures.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;
import zord.callmezord.zordscreatures.item.functional.ItemFossil;

public class ItemsGeneral {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZordsCreatures.MODID);

//Fossil
    public static final DeferredItem<Item> FOSSIL = ITEMS.registerItem(
            "fossil",
        ItemFossil::new,
        new Item.Properties()
);


    //IGUANA SPAWN EGG
    public static final DeferredItem<SpawnEggItem> IGUANA_SPAWN_EGG = ITEMS.registerItem(
            "iguana_spawn_egg",
            properties -> new SpawnEggItem(
                    properties.spawnEgg(EntitiesGeneral.IGUANA.get())
            )
    );



   public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
