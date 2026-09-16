package net.cordicus.raccoons.mixin;

import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.minecraft.client.model.HumanoidModel;
//? if <=1.21.4
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
//? if >=1.21.11
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
//? if >=1.21.11
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if <=1.21.4
@Mixin(PlayerRenderer.class)
//? if >1.21.4
//@Mixin(AvatarRenderer.class)
public class PlayerEntityRendererMixin {

    //? if <=1.21.4 {
    @Inject(method = "getArmPose(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At("HEAD"), cancellable = true)
    private static void raccoonsrabies$getArmPoseDR(Player player, ItemStack stack, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
    //? } else {
    /*@Inject(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At("HEAD"), cancellable = true)
    private static void raccoonsrabies$getArmPoseDR(Avatar player, ItemStack stack, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
    *///? }
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof RaccoonHandheldItem) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
        }
    }
}
