package net.cordicus.raccoons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;

//? if <=1.21.4
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
//? if =1.21.4 {
/*import net.minecraft.client.renderer.entity.state.PlayerRenderState;
*///? } else if >1.21.4 {
//? }
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public abstract class RaccoonOnHeadNewVersionMixin {

    //? if >=1.21.4 {
    /*@WrapOperation(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack raccoonsRabies$renderRaccoonOnHead(LivingEntity instance, EquipmentSlot equipmentSlot, Operation<ItemStack> original) {
        if (RaccoonHandheldItem.hasRaccoonEquipped(instance)) {
            return RaccoonHandheldItem.getRaccoonOnHead(instance);
        }
        return original.call(instance,equipmentSlot);
    }
    *///? }



}