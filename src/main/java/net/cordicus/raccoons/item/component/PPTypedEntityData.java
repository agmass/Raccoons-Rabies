package net.cordicus.raccoons.item.component;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import io.netty.buffer.ByteBuf;
import net.cordicus.raccoons.RaccoonsRabies;
import net.minecraft.ChatFormatting;
//? if >=1.21.11
//import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.RegistryOps;
//? if >1.20.1
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
//? if >=1.21.1 {
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.component.CustomData;
//? }

//? if >=1.21.11 {
/*import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
*///? }

import java.util.UUID;
import java.util.function.Consumer;

public final class PPTypedEntityData<IdType> {
    //? if <=1.21.4 {
    public static Codec<EntityType<?>> ENTITY_TYPE_CODEC = BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("id").codec();
    //? }
    final IdType type;
    final CompoundTag tag;

    public static <T> Codec<PPTypedEntityData<T>> codec(final Codec<T> codec) {
        return new Codec<PPTypedEntityData<T>>() {
            public <V> DataResult<Pair<PPTypedEntityData<T>, V>> decode(DynamicOps<V> dynamicOps, V object) {
                //? if <1.21.1 {
                /*return CompoundTag.CODEC
                        .decode(dynamicOps, object).flatMap((pair) -> {
                            CompoundTag compoundTag = ((CompoundTag)pair.getFirst()).copy();
                            Tag tag = compoundTag.get("id");
                            compoundTag.remove("id");
                            return codec.parse(asNbtOps(dynamicOps), tag).map((objectx) -> Pair.of(new PPTypedEntityData<>(objectx, compoundTag), pair.getSecond()));
                        });
                *///? } else {
                
                //? if >=1.21.11
                //return CustomData.COMPOUND_TAG_CODEC
                //? if <1.21.11
                return CustomData.CODEC
                        .decode(dynamicOps, object).flatMap((pair) -> {
                    CompoundTag compoundTag = ((CompoundTag)pair.getFirst()
                            //? if <=1.21.4
                            .copyTag()
                    ).copy();
                    //? if >=1.21.11
                    //Tag tag = compoundTag.remove("id");
                    //? if <=1.21.4 {
                    Tag tag = compoundTag.get("id");
                    //? }
                    return tag == null ? DataResult.error(() -> "Expected 'id' field in " + String.valueOf(object)) : codec.parse(asNbtOps(dynamicOps), tag).map((objectx) -> Pair.of(new PPTypedEntityData<>(objectx, compoundTag), pair.getSecond()));
                });
                //? }
            }

            public <V> DataResult<V> encode(PPTypedEntityData<T> typedEntityData, DynamicOps<V> dynamicOps, V object) {
                return codec.encodeStart(asNbtOps(dynamicOps), typedEntityData.type).flatMap((tag) -> {
                    CompoundTag compoundTag = typedEntityData.tag.copy();
                    compoundTag.put("id", tag);

                    //? if <1.21.1 {
                    /*return CompoundTag.CODEC.encode(compoundTag, dynamicOps, object);
                    *///? } else {
                    
                    //? if >=1.21.11
                    //return CustomData.COMPOUND_TAG_CODEC.encode(compoundTag, dynamicOps, object);
                    //? if <1.21.11
                    return CustomData.CODEC.encode(CustomData.of(compoundTag), dynamicOps, object);
                    //? }
                });
            }

            private static <T> DynamicOps<Tag> asNbtOps(DynamicOps<T> dynamicOps) {
                //? if <1.21.1 {
                /*return NbtOps.INSTANCE;
                *///? } else {
                if (dynamicOps instanceof RegistryOps<T> registryOps) {
                    return registryOps.withParent(NbtOps.INSTANCE);
                } else {
                    return NbtOps.INSTANCE;
                }
                //? }
            }
        };
    }

    //? if >=1.21.1 {
    public static  <B extends ByteBuf,T> StreamCodec<B, PPTypedEntityData<T>> streamCodec(StreamCodec<B, T> streamCodec) {
        return StreamCodec.composite(streamCodec, PPTypedEntityData::type, ByteBufCodecs.COMPOUND_TAG, PPTypedEntityData::tag, PPTypedEntityData::new);
    }
    //? }

    PPTypedEntityData(IdType object, CompoundTag compoundTag) {
        this.type = object;
        //? if >=1.21.1 {
        this.tag = stripId(compoundTag);
        //? } else {
        /*this.tag = compoundTag;
        *///? }
    }

    public static <T> PPTypedEntityData<T> of(T object, CompoundTag compoundTag) {
        return new PPTypedEntityData<T>(object, compoundTag);
    }

    private static CompoundTag stripId(CompoundTag compoundTag) {
        if (compoundTag.contains("id")) {
            CompoundTag compoundTag2 = compoundTag.copy();
            compoundTag2.remove("id");
            return compoundTag2;
        } else {
            return compoundTag;
        }
    }

    public IdType type() {
        return this.type;
    }

    public boolean contains(String string) {
        return this.tag.contains(string);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof PPTypedEntityData)) {
            return false;
        } else {
            PPTypedEntityData<?> typedEntityData = (PPTypedEntityData)object;
            return this.type == typedEntityData.type && this.tag.equals(typedEntityData.tag);
        }
    }

    public int hashCode() {
        return 31 * this.type.hashCode() + this.tag.hashCode();
    }

    public String toString() {
        String var10000 = String.valueOf(this.type);
        return var10000 + " " + String.valueOf(this.tag);
    }

    private CompoundTag tag() {
        return this.tag;
    }

    /** @deprecated */
    @Deprecated
    public CompoundTag getUnsafe() {
        return this.tag;
    }

    public CompoundTag copyTagWithoutId() {
        return this.tag.copy();
    }

}