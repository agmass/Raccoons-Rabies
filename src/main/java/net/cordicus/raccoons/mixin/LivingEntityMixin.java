package net.cordicus.raccoons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.item.custom.RaccoonsRabiesArmourItem;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    //? if >=1.21.1
    @Shadow public abstract boolean hasEffect(Holder<MobEffect> holder);

    //? if <1.21.1
    //@Shadow public abstract boolean hasEffect(MobEffect par1);

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "isAffectedByPotions", at = @At("RETURN"))
    private boolean raccoonsRabies$rabiesCancelsSplash(boolean original) {
        if (hasEffect(RaccoonsRabies.RABIES_EFFECT
                //? if <1.21.1
                //.value()
        )) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "isSensitiveToWater", at = @At("RETURN"))
    private boolean raccoonRabies$rabiesHurtsInWater(boolean original) {
        if (hasEffect(RaccoonsRabies.RABIES_EFFECT
                    //? if <1.21.1
                    //.value()
            )) {
            return true;
        }
        return original;
    }

    @ModifyReturnValue(method = "canBeAffected", at = @At("RETURN"))
    private boolean raccoonRabies$banditArmorRabiesImmunity(boolean original, MobEffectInstance effect) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (RaccoonsRabiesArmourItem.isWearingFullArmorSet(entity) &&
                //? if >=1.21.1
                effect.is(RaccoonsRabies.RABIES_EFFECT)
                //? if <1.21.1
                //effect.equals(RaccoonsRabies.RABIES_EFFECT)
        ) {
            return false;
        }
        return original;
    }
}
