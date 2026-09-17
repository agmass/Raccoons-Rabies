package net.cordicus.raccoons.item;

import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RaccoonsRabiesTags {
    public static final TagKey<Item> RACCOON_FURS = TagKey.create(Registries.ITEM, RRIdentifier.of("raccoon_furs").id);
    public static final TagKey<Item> HITTABLE = TagKey.create(BuiltInRegistries.ITEM.key(), RRIdentifier.of("hittable").id);

    public static void init() {}
}
