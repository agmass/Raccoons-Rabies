package net.cordicus.raccoons.mixin;

import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
//? if >=1.21.11
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.11 {
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
//? }
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
            if (!this.level().isClientSide()) {
                RaccoonEntity raccoon = RREntityTypes.RACCOON.create(this.level()
                        //? if >1.21.1
                        , EntitySpawnReason.CONVERSION
                );
                if (raccoon != null) {
                    if (stack.get(DataComponents.CUSTOM_DATA) != null) {
                        CompoundTag nbt = stack.get(DataComponents.CUSTOM_DATA).copyTag();
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
                            raccoon.setTame(true, true);
                            //? if <1.21.11
                            raccoon.setOwnerUUID(UUID.fromString(component.owner()));
                            //? if <1.21.11
                            raccoon.setOwnerReference(EntityReference.of(UUID.fromString(component.owner())));
                        }
                        else {
                            raccoon.setTame(false, true);
                        }
                        raccoon.setOrderedToSit(false);
                        raccoon.setInSittingPose(false);

                        raccoon.setRaccoonType(component.type());
                        raccoon.setBaby(component.baby());
                    }
                    else { // data fallback
                        raccoon.setTame(false, true);
                        raccoon.setOrderedToSit(false);
                        raccoon.setInSittingPose(false);
                    }
                    if (!stack.getHoverName().equals(RaccoonsRabiesItems.RACCOON.getDefaultInstance().getHoverName())) {
                        raccoon.setCustomName(stack.getHoverName().copy().withStyle(ChatFormatting.RESET));
                    }
                    raccoon.setPos(this.getX(), this.getY(), this.getZ());
                    raccoon.copyPosition(this);
                    this.level().addFreshEntity(raccoon);
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
