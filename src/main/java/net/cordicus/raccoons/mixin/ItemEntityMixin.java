package net.cordicus.raccoons.mixin;

import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
//? if >1.20.1
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
//? if >=1.21.4
//import net.minecraft.world.entity.EntitySpawnReason;
//? if >=1.21.11
//import net.minecraft.world.entity.EntityReference;
//? if >=1.21.1 {
import net.minecraft.core.component.DataComponents;
//? }
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.11 {
/*import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
*///? }
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow
    public abstract ItemStack getItem();

    public ItemEntityMixin(EntityType<?> type, ServerLevel world) {
        super(type, world);
    }


    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void raccoonsRabies$spawnRaccoonOnDrop(CallbackInfo ci) {
        ItemStack stack = getItem();
        if (stack != null && stack.is(RaccoonsRabiesItems.RACCOON)) {
            if (!this.level
                    //? if >1.19
                    ()
                    .isClientSide()) {
                RaccoonEntity raccoon = RREntityTypes.RACCOON.create(this.level
                        //? if >1.19
                        ()
                        //? if >1.21.1
                        //, EntitySpawnReason.CONVERSION
                );
                if (raccoon != null) {
                    if (RaccoonsRabiesComponents.RACCOON_DATA.has(stack)) {
                        CompoundTag nbt = RaccoonsRabiesComponents.RACCOON_DATA.get(stack).copyTagWithoutId();
                        if (nbt != null) {
                            //? if <1.21.11 {
                            raccoon.load(nbt);
                            raccoon.readAdditionalSaveData(nbt);
                            //? } else {
                            /*ValueInput valueInput = TagValueInput.create(ProblemReporter.DISCARDING, registryAccess(), nbt);
                            raccoon.load(valueInput);

                            *///? }
                        }
                    }
                    if (RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
                        RaccoonHandheldDataComponent component = RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack);
                        if (!component.owner().isEmpty()) {
                            raccoon.setTame(true
                                    //? if >=1.21.1
                                    , true
                            );
                            //? if <1.21.11
                            raccoon.setOwnerUUID(UUID.fromString(component.owner()));
                            //? if >=1.21.11
                            //raccoon.setOwnerReference(EntityReference.of(UUID.fromString(component.owner())));
                        }
                        else {
                            raccoon.setTame(false
                                    //? if >=1.21.1
                                    , true
                            );
                        }
                        raccoon.setOrderedToSit(false);
                        raccoon.setInSittingPose(false);

                        raccoon.setRaccoonType(component.type());
                        raccoon.setBaby(component.baby());
                    }
                    else { // data fallback
                        raccoon.setTame(false
                                //? if >=1.21.1
                                , true
                        );
                        raccoon.setOrderedToSit(false);
                        raccoon.setInSittingPose(false);
                    }
                    if (!stack.getHoverName().equals(RaccoonsRabiesItems.RACCOON.getDefaultInstance().getHoverName())) {
                        raccoon.setCustomName(stack.getHoverName().copy().withStyle(ChatFormatting.RESET));
                    }
                    raccoon.setPos(this.getX(), this.getY(), this.getZ());
                    raccoon.copyPosition(this);
                    this.level
                            //? if >1.19
                            ()
                            .addFreshEntity(raccoon);
                    stack.shrink(1);
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "hasPickUpDelay", at = @At("HEAD"), cancellable = true)
    private void raccoonsRabies$noRaccoonItemPickup(CallbackInfoReturnable<Boolean> cir) {
        if (getItem().is(RaccoonsRabiesItems.RACCOON)) { // item should never be able to be picked up, as it will always spawn a raccoon when dropped, nbt or not
            cir.setReturnValue(true);
        }
    }


}
