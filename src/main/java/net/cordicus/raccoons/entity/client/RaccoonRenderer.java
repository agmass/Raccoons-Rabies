package net.cordicus.raccoons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
//? if <1.21.11
import software.bernie.geckolib.cache.object.BakedGeoModel;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >=1.21.11
//import software.bernie.geckolib.renderer.base.RenderPassInfo;

public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity
        //? if >1.21.4
        //, RaccoonRenderState
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
        if (((RaccoonRenderState) renderPassInfo.renderState()).isBaby) {
            widthScale = 0.7f;
            heightScale = 0.7f;
        }
        super.scaleModelForRender(renderPassInfo, widthScale, heightScale);
    }


    @Override
    public void extractRenderState(RaccoonEntity entity, RaccoonRenderState entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isBaby = entity.isBaby();
        entityRenderState.raccoonType = entity.getRaccoonType();
        entityRenderState.isInSittingPose = entity.isInSittingPose();
    }

    *///? }
}
