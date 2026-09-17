package net.cordicus.raccoons.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
//? if <26.1 && >1.19
import net.minecraft.client.gui.GuiGraphics;
//? if <=1.21.4
import net.minecraft.client.renderer.RenderType;
//? if >=26.1
//import net.minecraft.client.gui.GuiGraphicsExtractor;

//? if >=26.2
//import net.minecraft.client.gui.Hud;
//? if >=1.21.11
//import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
//? if <26.2
@Mixin(Gui.class)
//? if >=26.2
//@Mixin(Hud.class)
public abstract class InGameHudMixin {

    @Unique private static final RRIdentifier fullTexture = RRIdentifier.of("hud/heart/rabies_full");
    @Unique private static final RRIdentifier fullBlinkingTexture = RRIdentifier.of("hud/heart/rabies_full_blinking");
    @Unique private static final RRIdentifier halfTexture = RRIdentifier.of("hud/heart/rabies_half");
    @Unique private static final RRIdentifier halfBlinkingTexture = RRIdentifier.of("hud/heart/rabies_half_blinking");
    @Unique private static final RRIdentifier hardcoreFullTexture = RRIdentifier.of("hud/heart/rabies_hardcore_full");
    @Unique private static final RRIdentifier hardcoreFullBlinkingTexture = RRIdentifier.of("hud/heart/rabies_hardcore_full_blinking");
    @Unique private static final RRIdentifier hardcoreHalfTexture = RRIdentifier.of("hud/heart/rabies_hardcore_half");
    @Unique private static final RRIdentifier hardcoreHalfBlinkingTexture = RRIdentifier.of("hud/heart/rabies_hardcore_half_blinking");


    //? if >1.20.1 {
    //? if <26.1 {
    @Inject(method = "renderHeart", at = @At("HEAD"), cancellable = true)
    private void raccoonsRabies$rabiesHearts(GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo ci) {
        if (type != Gui.HeartType.ABSORBING && type != Gui.HeartType.CONTAINER && Minecraft.getInstance().getCameraEntity() instanceof Player player && player.hasEffect(RaccoonsRabies.RABIES_EFFECT
                //? if <=1.20.4
                //.value()
        )) {
            //? } else {
    /*@Inject(method = "extractHeart", at = @At("HEAD"), cancellable = true)
    //? if <26.2 {
    private void raccoonsRabies$rabiesHearts(GuiGraphicsExtractor context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo ci) {
        if (type != Gui.HeartType.ABSORBING && type != Gui.HeartType.CONTAINER && Minecraft.getInstance().getCameraEntity() instanceof Player player && player.hasEffect(RaccoonsRabies.RABIES_EFFECT)) {
    //? } else {
    /^private void raccoonsRabies$rabiesHearts(GuiGraphicsExtractor context, Hud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo ci) {
        if (type != Hud.HeartType.ABSORBING && type != Hud.HeartType.CONTAINER && Minecraft.getInstance().getCameraEntity() instanceof Player player && player.hasEffect(RaccoonsRabies.RABIES_EFFECT)) {
            ^///? }
            *///? }

            RRIdentifier texture = fullTexture;
            if (!hardcore) {
                if (half) {
                    texture = blinking ? halfBlinkingTexture : halfTexture;
                } else {
                    texture = blinking ? fullBlinkingTexture : fullTexture;
                }
            } else if (half) {
                texture = blinking ? hardcoreHalfBlinkingTexture : hardcoreHalfTexture;
            } else {
                texture = blinking ? hardcoreFullBlinkingTexture : hardcoreFullTexture;
            }

            //? if <=1.21.4 {
            RenderSystem.enableBlend();
            context.blitSprite(
                    //? if >1.21.1
                    //RenderType::guiTextured,
                    texture.id, x, y, 9, 9);
            RenderSystem.disableBlend();
            //? } else {
            /*context.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    texture.id, x, y, 9, 9);
            *///? }
            ci.cancel();
        }
    }
    //? }
}
