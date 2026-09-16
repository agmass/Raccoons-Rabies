package net.cordicus.raccoons.sounds;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class RaccoonsRabiesSounds {
    public static final SoundEvent ENTITY_RACCOON_AMBIENT = registerSoundEvent("entity_raccoon_ambient");
    public static final SoundEvent ENTITY_RACCOON_HURT = registerSoundEvent("entity_raccoon_hurt");
    public static final SoundEvent ENTITY_RACCOON_DEATH = registerSoundEvent("entity_raccoon_death");


    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, RRIdentifier.of(name).id, SoundEvent.createVariableRangeEvent(RRIdentifier.of(name).id));
    }

    public static void registerSounds() {
        RaccoonsRabies.LOGGER.info("Registering Sounds for "+RaccoonsRabies.MOD_ID);
    }
}
