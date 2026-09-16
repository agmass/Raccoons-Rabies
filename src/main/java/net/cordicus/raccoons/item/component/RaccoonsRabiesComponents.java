package net.cordicus.raccoons.item.component;

import com.mojang.serialization.Codec;
import net.cordicus.raccoons.porting.PPComponentOrNBT;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.TypedEntityData;

public class RaccoonsRabiesComponents {


    public static final PPComponentOrNBT<PPTypedEntityData<EntityType<?>>> RACCOON_DATA =
            new PPComponentOrNBT<PPTypedEntityData<EntityType<?>>>(
                    "racooon_data",
                    PPTypedEntityData.codec(EntityType.CODEC)
                    //? if >1.20.4
                    , PPTypedEntityData.streamCodec(EntityType.STREAM_CODEC),false
            );


    public static final PPComponentOrNBT<Boolean> HIDE_BANDIT_HOOD =
            new PPComponentOrNBT<Boolean>(
                    "hide_bandit_hood",
                    Codec.BOOL
                    //? if >1.20.4
                    , ByteBufCodecs.BOOL
            );


    public static final PPComponentOrNBT<RaccoonHandheldDataComponent> RACCOON_HELD_DATA =
            new PPComponentOrNBT<>(
                    "racooon_data",
                    RaccoonHandheldDataComponent.CODEC
            );


    public static void init() {
        RACCOON_DATA.register();
    }
}
