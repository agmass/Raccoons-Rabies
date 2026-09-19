package net.cordicus.raccoons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CustomHeadLayer.class)
public abstract class HeadFeatureRendererMixin
{

    //? if <=1.21.1 {
    @WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack raccoonsRabies$renderRaccoonOnHead(LivingEntity instance, EquipmentSlot equipmentSlot, Operation<ItemStack> original) {
        if (RaccoonHandheldItem.hasRaccoonEquipped(instance)) {
            return RaccoonHandheldItem.getRaccoonOnHead(instance);
        }

        return original.call(instance, equipmentSlot);
    }
    //? }

}