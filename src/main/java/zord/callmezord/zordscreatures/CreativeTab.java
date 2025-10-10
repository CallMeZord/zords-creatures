package zord.callmezord.zordscreatures;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.item.ItemsGeneral;

import java.util.function.Supplier;

public class CreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZordsCreatures.MODID);


    // Creates a creative tab with the id "zordscreatures:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ZORDS_CREATURES_TAB = CREATIVE_MODE_TABS.register("zords_creatures", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zordscreatures")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)

            .icon( () -> ItemsGeneral.FOSSIL.get().getDefaultInstance())

            .displayItems((parameters, output) -> {
                output.accept(ItemsGeneral.FOSSIL.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

}

