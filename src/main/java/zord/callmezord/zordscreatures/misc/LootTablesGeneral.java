package zord.callmezord.zordscreatures.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import zord.callmezord.zordscreatures.ZordsCreatures;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LootTablesGeneral {

    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet();
    private static Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS;

    public static ResourceKey<LootTable> IGUANA_GROW_UP;
    public static ResourceKey<LootTable> FOSSIL_LOOT;


    private static ResourceKey<LootTable> register(String name) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, name)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> name) {
        if (LOCATIONS.add(name)) {
            return name;
        } else {
            throw new IllegalArgumentException(String.valueOf(name.location()) + " is already a registered built-in loot table, Zord's Creatures here btw!");
        }
    }


    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS;
    }

    static {
        IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
        FOSSIL_LOOT = register("gameplay/fossil_loot");
        IGUANA_GROW_UP = register("gameplay/iguana_growing_up");
    }


}
