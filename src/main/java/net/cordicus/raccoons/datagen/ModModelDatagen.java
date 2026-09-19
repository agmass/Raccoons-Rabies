package net.cordicus.raccoons.datagen;


//? if >=1.18 {
//? if >=26.1 {
/*import net.cordicus.raccoons.datagen.model.BabyComponentContent;
import net.cordicus.raccoons.datagen.model.SkinComponentContent;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
 *///? } else if >1.18 {
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.cordicus.raccoons.datagen.model.BabyComponentContent;
import net.cordicus.raccoons.datagen.model.SkinComponentContent;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//? } else {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
 *///? }
//? if >=1.21.4 {
/*import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
*///? } else {
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
//? }
//? if >=1.21.11 {
/*import net.minecraft.client.renderer.item.properties.select.ComponentContents;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
*///? }
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class ModModelDatagen extends FabricModelProvider {

    //? if >=26.1 {
    /*public ModModelDatagen(FabricPackOutput output) {
        super(output);
    }
    *///? } else if >1.18 {
    public ModModelDatagen(FabricDataOutput output) {
        super(output);
    }
    //? } else {
    /*public PerkyModelDatagen(FabricDataGenerator output) {
        super(output);
    }
    *///? }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.BANDIT_HOOD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.BANDIT_GREAVES, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.BANDIT_GAMBESON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.BANDIT_BOOTS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.BANDIT_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.ALBINO_RACCOON_FUR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.RACCOON_FUR, ModelTemplates.FLAT_ITEM);
        //? if <=1.21.4 {
        itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.RACCOON_SPAWN_EGG, new ModelTemplate(Optional.of(RRIdentifier.ofVanilla("item/template_spawn_egg").id), Optional.empty()));
        //? } else {
        /*itemModelGenerator.generateFlatItem(RaccoonsRabiesItems.RACCOON_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        *///? }

        //? if >=1.21.4 {
        /*createRaccoon(itemModelGenerator, RaccoonsRabiesItems.RACCOON);
        *///? }
    }

    //? if >=1.21.4 {
    /*public void createRaccoon(ItemModelGenerators itemModelGenerator, Item item) {
        RRIdentifier base_id = RRIdentifier.of("item/raccoon_normal");

        RRIdentifier amethyst_id = RRIdentifier.of("item/raccoon_amethyst");
        RRIdentifier bandit_id = RRIdentifier.of("item/raccoon_bandit");
        RRIdentifier albino_id = RRIdentifier.of("item/raccoon_albino");
        RRIdentifier cordicus_id = RRIdentifier.of("item/raccoon_cordicus");
        RRIdentifier nitron_id = RRIdentifier.of("item/raccoon_nitron");
        RRIdentifier rocket_id = RRIdentifier.of("item/raccoon_rocket");
        RRIdentifier yak_id = RRIdentifier.of("item/raccoon_yak");

        itemModelGenerator.itemModelOutput.accept(item,
                ItemModelUtils.select(
                        new BabyComponentContent(),
                        ItemModelUtils.plainModel(base_id.id),
                        ItemModelUtils.when(
                                false,
                                ItemModelUtils.select(
                                    new SkinComponentContent(),
                                    ItemModelUtils.plainModel(base_id.id),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.AMETHYST,
                                                ItemModelUtils.plainModel(amethyst_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.ALBINO,
                                                ItemModelUtils.plainModel(albino_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.BANDIT,
                                                ItemModelUtils.plainModel(bandit_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.CORDICUS,
                                                ItemModelUtils.plainModel(cordicus_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.NITRON,
                                                ItemModelUtils.plainModel(nitron_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.NORMAL,
                                                ItemModelUtils.plainModel(base_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.YAK,
                                                ItemModelUtils.plainModel(yak_id.id)
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.ROCKET,
                                                ItemModelUtils.plainModel(rocket_id.id)
                                        )
                                )
                        ),
                        ItemModelUtils.when(
                                true,
                                ItemModelUtils.select(
                                        new SkinComponentContent(),
                                        ItemModelUtils.plainModel(base_id.id),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.AMETHYST,
                                                ItemModelUtils.plainModel(amethyst_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.ALBINO,
                                                ItemModelUtils.plainModel(albino_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.BANDIT,
                                                ItemModelUtils.plainModel(bandit_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.CORDICUS,
                                                ItemModelUtils.plainModel(cordicus_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.NITRON,
                                                ItemModelUtils.plainModel(nitron_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.NORMAL,
                                                ItemModelUtils.plainModel(base_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.YAK,
                                                ItemModelUtils.plainModel(yak_id.id.withSuffix("_baby"))
                                        ),
                                        ItemModelUtils.when(
                                                RaccoonHandheldItem.ROCKET,
                                                ItemModelUtils.plainModel(rocket_id.id.withSuffix("_baby"))
                                        )
                                )
                        )
                )
        );
    }
    *///? }
}
//? }
