package net.cordicus.raccoons.mixin;

import net.cordicus.raccoons.datagen.model.BabyComponentContent;
import net.cordicus.raccoons.datagen.model.SkinComponentContent;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? >=1.21.4 {


/*import net.minecraft.client.renderer.item.properties.select.ContextEntityType;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
//? if >=1.21.11
//import net.minecraft.resources.Identifier;
//? if <1.21.11
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
@Mixin(SelectItemModelProperties.class)
public abstract class ModelPropertiesMixin {

    //? if >=1.21.11
    //@Shadow  @Final  public static ExtraCodecs.LateBoundIdMapper<Identifier, SelectItemModelProperty.Type<?, ?>> ID_MAPPER;

    //? if <1.21.11
    @Shadow @Final public static ExtraCodecs.LateBoundIdMapper<ResourceLocation, SelectItemModelProperty.Type<?, ?>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void addTheModelPropertiesBecauseThereIsNoFapiFunctionForThisForSomeReason(CallbackInfo ci) {
        ID_MAPPER.put(RRIdentifier.of("baby_component_content").id, BabyComponentContent.TYPE);
        ID_MAPPER.put(RRIdentifier.of("skin_component_content").id, SkinComponentContent.TYPE);
    }
}
*///? } else {
@Mixin(Minecraft.class)
public abstract class ModelPropertiesMixin {
}
//? }