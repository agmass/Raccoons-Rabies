package net.cordicus.raccoons.item.component;

import com.mojang.serialization.Codec;
import net.cordicus.raccoons.porting.PPComponentOrNBT;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
//? if >=1.21.1 {
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.SpawnEggItem;
//? }
//? if >=1.21.11
//import net.minecraft.world.item.component.TypedEntityData;

public class RaccoonsRabiesComponents {


    public static final PPComponentOrNBT<PPTypedEntityData<EntityType<?>>> RACCOON_DATA =
            new PPComponentOrNBT<PPTypedEntityData<EntityType<?>>>(
                    "racooon_data",
                    //? if >=1.21.11
                    //PPTypedEntityData.codec(EntityType.CODEC)
                    //? if <1.21.11
                    PPTypedEntityData.codec(PPTypedEntityData.ENTITY_TYPE_CODEC)
                    //? if >=1.21.11
                    //, PPTypedEntityData.streamCodec(EntityType.STREAM_CODEC),false
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
                    "racooon_held_data",
                    RaccoonHandheldDataComponent.CODEC
            );


    public static void init() {
        HIDE_BANDIT_HOOD.register();
        RACCOON_DATA.register();
        RACCOON_HELD_DATA.register();
    }
}
