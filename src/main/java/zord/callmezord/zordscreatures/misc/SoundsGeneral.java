package zord.callmezord.zordscreatures.misc;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.ZordsCreatures;

public class SoundsGeneral {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,  ZordsCreatures.MODID);


    public static final Holder<SoundEvent> IGUANA_HURT = SOUNDS.register("iguana_hurt", SoundEvent::createVariableRangeEvent);
    public static final Holder<SoundEvent> IGUANA_DEATH = SOUNDS.register("iguana_death", SoundEvent::createVariableRangeEvent);


    public static void registerSounds(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
