package zord.callmezord.zordscreatures.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.ZordsCreatures;

public class ItemsGeneral {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZordsCreatures.MODID);



//Fossil
    public static final DeferredItem<Item> FOSSIL = ITEMS.registerItem(
            "fossil",
        Item::new,
        new Item.Properties()
);



   public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
