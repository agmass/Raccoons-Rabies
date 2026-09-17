package net.cordicus.raccoons.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.minecraft.client.model.HumanoidModel;
//? <26.2
import net.minecraft.client.renderer.MultiBufferSource;
//? if >=1.21.11
//import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
//? if >1.21.1
//import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HumanoidArmorLayer.class)
public abstract class ArmorFeatureRendererMixin<T extends
        //? if >1.21.1
        //HumanoidRenderState
        //? if <=1.21.1
        LivingEntity
        , M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public ArmorFeatureRendererMixin(RenderLayerParent<T, M> context) {
        super(context);
    }

    //? if <1.21.11 {
    @WrapMethod(method = "renderArmorPiece")
    //? >1.21.1 {
    /*private void raccoonRabies$removeHood(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack changingarg, EquipmentSlot slot, int light, A armorModel, Operation<Void> original){
        ItemStack stack = changingarg;
        *///? } else if <=1.21.1 {
    private void raccoonRabies$removeHood(PoseStack matrices, MultiBufferSource vertexConsumers, T changingarg, EquipmentSlot slot, int light, A armorModel, Operation<Void> original){
      ItemStack stack = changingarg.getItemBySlot(slot);
      //? }
        if (slot.equals(EquipmentSlot.HEAD)) {
            if ((RaccoonsRabiesComponents.HIDE_BANDIT_HOOD.has(stack))) {
                if (RaccoonsRabiesComponents.HIDE_BANDIT_HOOD.get(stack)) {
                    return;
                }
            }
        }
        original.call(matrices,vertexConsumers,changingarg,slot,light,armorModel);
    }
    //? } else {
    /*@WrapMethod(method = "renderArmorPiece")
    private void raccoonRabies$removeHood(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, EquipmentSlot slot, int i, HumanoidRenderState humanoidRenderState, Operation<Void> original){
        if (slot.equals(EquipmentSlot.HEAD)) {
            if ((RaccoonsRabiesComponents.HIDE_BANDIT_HOOD.has(stack))) {
                if (RaccoonsRabiesComponents.HIDE_BANDIT_HOOD.get(stack)) {
                    return;
                }
            }
        }
        original.call(poseStack, submitNodeCollector, stack, slot, i, humanoidRenderState);
    }
    *///? }
}