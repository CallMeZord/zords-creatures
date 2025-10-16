package zord.callmezord.zordscreatures;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.item.ItemsGeneral;


public class CreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZordsCreatures.MODID);


    // Creates a creative tab with the id "zordscreatures:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ZORDS_CREATURES_TAB = CREATIVE_MODE_TABS.register("zords_creatures", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zordscreatures")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)

            .icon( () -> ItemsGeneral.FOSSIL.get().getDefaultInstance())

            .displayItems((parameters, output) -> {

                //SPAWN EGGS
                output.accept(ItemsGeneral.IGUANA_SPAWN_EGG.get());
                output.accept(ItemsGeneral.PANCAKE_SLUG_SPAWN_EGG.get());


                //ITEMS
                output.accept(ItemsGeneral.FOSSIL.get());

                output.accept(ItemsGeneral.SHED.get());
                output.accept(ItemsGeneral.IGUANA_EGG.get());


                //BLOCKS

            }).build());
}
