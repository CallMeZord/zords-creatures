package zord.callmezord.zordscreatures.misc;


import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import zord.callmezord.zordscreatures.ZordsCreatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootTableGeneral extends DatapackBuiltinEntriesProvider {

public LootTableGeneral(PackOutput output, CompletableFuture<RegistrySetBuilder.PatchedRegistries> registries) {

    super(output, registries, Set.of(ZordsCreatures.MODID));

}


public static void gatherData(DataGenerator generator, PackOutput output, CompletableFuture<RegistrySetBuilder.PatchedRegistries> registries) {

    generator.addProvider(true, new LootTableGeneral(output, registries));

}


}