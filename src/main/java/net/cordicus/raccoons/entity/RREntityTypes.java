package net.cordicus.raccoons.entity;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

public class RREntityTypes {
    public static final EntityType<RaccoonEntity> RACCOON = register("raccoon", EntityType.Builder.of(RaccoonEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.6f)
            .eyeHeight(0.55f)
            .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
            .clientTrackingRange(10)
    );

    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, RRIdentifier.of(path).id);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key
            //? if <=1.21.1
                //.toString()
        ));
    }

    public static void initMobEntities() {
    }
}
