package net.cordicus.raccoons.datagen.model;

//? if >=1.21.4 {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public record BabyComponentContent() implements SelectItemModelProperty<Boolean> {
    public static final Type<BabyComponentContent, Boolean> TYPE;

    public Boolean get(final ItemStack itemStack, final  ClientLevel level, final  LivingEntity owner, final int seed, final ItemDisplayContext displayContext) {
        if (!itemStack.has(RaccoonsRabiesComponents.RACCOON_HELD_DATA.COMPONENT_FORM)) {
            return false;
        }
        return itemStack.get(RaccoonsRabiesComponents.RACCOON_HELD_DATA.COMPONENT_FORM).baby();
    }

    public Type<BabyComponentContent, Boolean> type() {
        return TYPE;
    }

    public Codec<Boolean> valueCodec() {
        return Codec.BOOL;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new BabyComponentContent()), Codec.BOOL);
    }
}
*///? } else {
public class BabyComponentContent {}
//? }
