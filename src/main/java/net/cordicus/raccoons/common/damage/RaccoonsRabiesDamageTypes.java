package net.cordicus.raccoons.common.damage;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class RaccoonsRabiesDamageTypes
{
    public static final ResourceKey<DamageType> RABIES = ResourceKey.create(Registries.DAMAGE_TYPE, RRIdentifier.of("rabies").id);


}

