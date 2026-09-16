package net.cordicus.raccoons.common.effect;

import net.cordicus.raccoons.common.damage.RaccoonsRabiesDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RabiesEffect extends MobEffect {
    public RabiesEffect()
    {
        super(MobEffectCategory.HARMFUL, 0x675f5b);
    }



    @Override
    //? if >1.21.1
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier)
    //? if <=1.21.1
    //public boolean applyEffectTick(LivingEntity livingEntity, int i)
    {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }
}
