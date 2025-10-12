package zord.callmezord.zordscreatures.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.entity.entities.Iguana;

import java.util.function.Supplier;

public class EntitiesGeneral {

public static final DeferredRegister.Entities ENTITY_TYPES =
        DeferredRegister.createEntities(ZordsCreatures.MODID);


    public static final Supplier<EntityType<Iguana>> IGUANA = ENTITY_TYPES.registerEntityType(
            "iguana", Iguana::new, MobCategory.CREATURE,
            builder -> builder
                    .sized(0.8f, 0.6f)
    );


public static void register(IEventBus bus) {
    ENTITY_TYPES.register(bus);
}
}
