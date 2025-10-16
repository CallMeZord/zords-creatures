package zord.callmezord.zordscreatures.entity.spawns;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;
import zord.callmezord.zordscreatures.entity.entities.Iguana;


@EventBusSubscriber(modid = ZordsCreatures.MODID)
public class SpawnRuleChecker {

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {


        //SPAWN REG

        //IGUANAS
        event.register(
                EntitiesGeneral.IGUANA.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world,reason, pos, random) -> {
                    return Iguana.checkIguanaSpawnRules(entityType, world, reason, pos);
                },
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );

    }
}
