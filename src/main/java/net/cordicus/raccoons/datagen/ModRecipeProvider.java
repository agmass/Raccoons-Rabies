package net.cordicus.raccoons.datagen;

//? if >=26.1 {
/*import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
 *///? } else >1.18 {
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? } else {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
*///? }
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
//? if >1.18 {
import net.minecraft.data.recipes.*;
import net.minecraft.core.HolderLookup;
//? }
//? if <=1.20.1 {
/*import net.minecraft.data.recipes.FinishedRecipe;
*///? }
//? if >=1.20.4 {
//? }
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
//? if >=26.1 {
/*import net.minecraft.world.item.crafting.CookingBookCategory;
 *///? }
import net.minecraft.world.item.crafting.Ingredient;
//?if >=26.3 {
/*import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.advancements.Advancement;
*///? }

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class ModRecipeProvider extends FabricRecipeProvider {

    //? if >=26.1 {
    /*public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
     *///? } else if >1.18 {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
     //? } else {
    /*public ModRecipeProvider(FabricDataGenerator output) {
        *///? }
        //? if <1.21.1 {
        /*super(output);
        *///?} else {
        super(output, registriesFuture);
         //? }
    }

    public String getName() {
        return "Stepping On Endshells Recipes";
    }

    //? if <1.21.4 && >1.20.1
    public RecipeOutput recipeOutput;
    //? if <=1.20.1
    //public Consumer<FinishedRecipe> recipeOutput;

    //? if >=26.3 {
    /*@Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> recipeOutput1, BootstrapContext<Advancement> bootstrapContext1) {
        return new RecipeProvider(recipeOutput1,bootstrapContext1) {
            *///? } else if >=1.21.4 {
    /*@Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
    *///? }
            @Override
            //? if >=1.21.4 {
            /*public void buildRecipes() {
                *///? } else if >=1.20.4 {
                public void buildRecipes(RecipeOutput recipeOutput) {
                 //? } else if >1.18 {
                /*public void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
                *///? } else {
                /*public void generateRecipes(Consumer<FinishedRecipe> recipeOutput) {
                *///? }

                //?if >=26.3 {
                /*RecipeOutput recipeOutput = (RecipeOutput) recipeOutput1;
                *///? }

                //? if <1.21.4
                this.recipeOutput = recipeOutput;


                banditSmithing(Items.DIAMOND_BOOTS, RaccoonsRabiesItems.BANDIT_BOOTS);
                banditSmithing(Items.DIAMOND_LEGGINGS, RaccoonsRabiesItems.BANDIT_GREAVES);
                banditSmithing(Items.DIAMOND_CHESTPLATE, RaccoonsRabiesItems.BANDIT_GAMBESON);
                banditSmithing(Items.DIAMOND_HELMET, RaccoonsRabiesItems.BANDIT_HOOD);


            }

            public void banditSmithing(Item base, Item result) {
                SmithingTransformRecipeBuilder
                        .smithing(
                                Ingredient.of(RaccoonsRabiesItems.BANDIT_UPGRADE),
                                Ingredient.of(base),
                                //? if >=1.21.4
                                //tag(RaccoonsRabiesTags.RACCOON_FURS),
                                //? if <1.21.4
                                Ingredient.of(RaccoonsRabiesTags.RACCOON_FURS),
                                RecipeCategory.COMBAT,
                                result)
                        .unlocks("has_raccoon_furs",
                                this.has(RaccoonsRabiesTags.RACCOON_FURS))
                        .save(
                                //? if >=1.21.4
                                //output
                                //? if <=1.21.1
                                recipeOutput
                                ,  getItemName(result) + "_smithing");
            }

            //? if >=1.21.4 {
        /*};
    }
    *///? }

}
