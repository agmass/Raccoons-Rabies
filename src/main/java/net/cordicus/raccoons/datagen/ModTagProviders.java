package net.cordicus.raccoons.datagen;
//? if >=26.1 {
/*import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
*///? } else {
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
//? }
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//? if >1.21.4
//import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModTagProviders {

    //? if >=26.1 {
    /*public static final class ItemTags extends FabricTagsProvider.ItemTagsProvider {
     *///?} else {
    public static final class ItemTags extends FabricTagProvider.ItemTagProvider {
        //? }


        //? if >=26.1 {
        /*public ItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
         *///?} else {
        public ItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            //? }
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            add(RaccoonsRabiesItems.BANDIT_BOOTS, net.minecraft.tags.ItemTags.TRIMMABLE_ARMOR);
            add(RaccoonsRabiesItems.BANDIT_GREAVES, net.minecraft.tags.ItemTags.TRIMMABLE_ARMOR);
            add(RaccoonsRabiesItems.BANDIT_GAMBESON, net.minecraft.tags.ItemTags.TRIMMABLE_ARMOR);
            add(RaccoonsRabiesItems.BANDIT_HOOD, net.minecraft.tags.ItemTags.TRIMMABLE_ARMOR);

            add(RaccoonsRabiesItems.RACCOON_FUR, RaccoonsRabiesTags.RACCOON_FURS);
            add(RaccoonsRabiesItems.ALBINO_RACCOON_FUR, RaccoonsRabiesTags.RACCOON_FURS);
        }

        public void add(Item item, TagKey<Item> tag) {
            //? if >=26.2 {
            /*tag(tag)
                    .add(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
            *///? } else if >1.21.4 {
            /*valueLookupBuilder(tag)
                    .add(item);
            *///? } else{
            tag(tag)
                    .add(BuiltInRegistries.ITEM.getResourceKey(item).get());
            //? }
        }

        public void addList(List<Item> list, TagKey<Item> tag) {
            //? if >=26.2 {
            /*TagAppender<Item> itemTagAppender = tag(tag);
             *///? } else  if >1.21.4 {
            /*TagAppender<Item, Item> itemTagAppender = valueLookupBuilder(tag);
             *///? }
            for (Item item : list) {
                //? if >=26.2 {
                /*itemTagAppender.add(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
                 *///? } else  if >1.21.4 {
                /*itemTagAppender.add(item);
                 *///? } else {
                tag(tag)
                        .add(BuiltInRegistries.ITEM.getResourceKey(item).get());
                //? }
            }
        }
    }
}
