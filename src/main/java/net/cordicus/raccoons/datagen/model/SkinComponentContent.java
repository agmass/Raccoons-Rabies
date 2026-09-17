package net.cordicus.raccoons.datagen.model;

//?if >=1.21.4 {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public record SkinComponentContent() implements SelectItemModelProperty<Integer> {
    public static final SelectItemModelProperty.Type<SkinComponentContent, Integer> TYPE;

    public Integer get(final ItemStack itemStack, final  ClientLevel level, final  LivingEntity owner, final int seed, final ItemDisplayContext displayContext) {
        if (!itemStack.has(RaccoonsRabiesComponents.RACCOON_HELD_DATA.COMPONENT_FORM)) {
            return 0;
        }
        return itemStack.get(RaccoonsRabiesComponents.RACCOON_HELD_DATA.COMPONENT_FORM).type();
    }

    public SelectItemModelProperty.Type<SkinComponentContent, Integer> type() {
        return TYPE;
    }

    public Codec<Integer> valueCodec() {
        return Codec.INT;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new SkinComponentContent()), Codec.INT);
    }
}
*///? } else {
public class SkinComponentContent {}
//? }
