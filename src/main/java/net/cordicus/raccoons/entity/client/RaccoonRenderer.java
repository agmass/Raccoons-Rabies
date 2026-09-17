package net.cordicus.raccoons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
//? if <1.21.11
import software.bernie.geckolib.cache.object.BakedGeoModel;
//? if >=1.21.11 {
/*import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
*///? }
import net.minecraft.world.entity.Entity;
//? <26.1 {
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >=1.21.11 {
/*import software.bernie.geckolib.renderer.base.RenderPassInfo;
import software.bernie.geckolib.constant.dataticket.DataTicket;
*///? }
//? } else {
/*import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.RenderPassInfo;
import com.geckolib.constant.dataticket.DataTicket;
*///? }

public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity
        //? if >1.21.4
        //, LivingEntityRenderState
        > {
    public RaccoonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RaccoonModel());
    }

    //? if <=1.21.4 {
    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, RaccoonEntity animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.isBaby()) {
            poseStack.scale(0.7F, 0.7F, 0.7F);
        } else {
            poseStack.scale(1.0F, 1.0F, 1.0F);
        }
        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
    //? } else {

    /*@Override
    public void scaleModelForRender(RenderPassInfo renderPassInfo, float widthScale, float heightScale) {
        if (((LivingEntityRenderState)renderPassInfo.renderState()).isBaby) {
            widthScale = 0.7f;
            heightScale = 0.7f;
        }
        super.scaleModelForRender(renderPassInfo, widthScale, heightScale);
    }


    public static DataTicket<Integer> type = DataTicket.create("raccoon_type", Integer.class);
    public static DataTicket<Boolean> isSitting = DataTicket.create("is_sitting", Boolean.class);

    @Override
    public void extractRenderState(RaccoonEntity entity, LivingEntityRenderState entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isBaby = entity.isBaby();
        entityRenderState.addGeckolibData(type, entity.getRaccoonType());
        entityRenderState.addGeckolibData(isSitting, entity.isInSittingPose());
    }

    *///? }
}
