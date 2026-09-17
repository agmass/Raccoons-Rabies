package net.cordicus.raccoons.item;

import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.cordicus.raccoons.item.custom.BanditSmithingTemplateItem;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.cordicus.raccoons.item.custom.RaccoonsRabiesArmorMaterials;
import net.cordicus.raccoons.porting.PPRegistrar;
//? if <1.21.4
import net.minecraft.core.Holder;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
//? if <1.21.11
import net.minecraft.world.item.ArmorItem;
//? if >=1.21.4
//import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;

import java.awt.*;

public class RaccoonsRabiesItems {

    // Raccoons and Drops
    public static Item RACCOON_FUR = PPRegistrar.registerItem("raccoon_fur", Item::new, new Item.Properties(), CreativeModeTabs.INGREDIENTS);
    public static Item ALBINO_RACCOON_FUR = PPRegistrar.registerItem("albino_raccoon_fur", Item::new, new Item.Properties(), CreativeModeTabs.INGREDIENTS);

    // Raccoon
    public static Item RACCOON = PPRegistrar.registerItem("raccoon", RaccoonHandheldItem::new, new Item.Properties().stacksTo(1)
                    //? if >=1.21.1
                    .component(RaccoonsRabiesComponents.RACCOON_HELD_DATA.COMPONENT_FORM, new RaccoonHandheldDataComponent(0, "", false))
            ,null);

    // Bandit Armour Set
    // Yes. I registered it 4 times. What are you gonna do about it
    //? if >=1.21.11 {

    /*public static final Item BANDIT_HOOD = PPRegistrar.registerItem("bandit_hood", settings -> new Item(settings),
            new Item.Properties()
                    .humanoidArmor(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.HELMET), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GAMBESON = PPRegistrar.registerItem("bandit_gambeson", settings -> new Item(settings),
            new Item.Properties()
                    .humanoidArmor(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.CHESTPLATE), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GREAVES = PPRegistrar.registerItem("bandit_greaves", settings -> new Item(settings),
            new Item.Properties()
                    .humanoidArmor(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.LEGGINGS), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_BOOTS = PPRegistrar.registerItem("bandit_boots", settings -> new Item(settings),
            new Item.Properties()
                    .humanoidArmor(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.BOOTS), CreativeModeTabs.COMBAT);
    *///? } else if =1.21.4 {
    /*public static final Item BANDIT_HOOD = PPRegistrar.registerItem("bandit_hood", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.HELMET, settings),
            new Item.Properties().durability(ArmorType.HELMET.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GAMBESON = PPRegistrar.registerItem("bandit_gambeson", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.CHESTPLATE, settings),
            new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GREAVES = PPRegistrar.registerItem("bandit_greaves", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.LEGGINGS, settings),
            new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)), CreativeModeTabs.COMBAT);
    public static final Item BANDIT_BOOTS = PPRegistrar.registerItem("bandit_boots", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorType.BOOTS, settings),
            new Item.Properties().durability(ArmorType.BOOTS.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)), CreativeModeTabs.COMBAT);
    *///? } else if >=1.21.1 {
    public static final Item BANDIT_HOOD = PPRegistrar.registerItem("bandit_hood", settings -> new ArmorItem(Holder.direct(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL), ArmorItem.Type.HELMET, settings),
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GAMBESON = PPRegistrar.registerItem("bandit_gambeson", settings -> new ArmorItem(Holder.direct(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL), ArmorItem.Type.CHESTPLATE, settings),
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GREAVES = PPRegistrar.registerItem("bandit_greaves", settings -> new ArmorItem(Holder.direct(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL), ArmorItem.Type.LEGGINGS, settings),
            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_BOOTS = PPRegistrar.registerItem("bandit_boots", settings -> new ArmorItem(Holder.direct(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL), ArmorItem.Type.BOOTS, settings),
            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(RaccoonsRabiesArmorMaterials.BASE_DURABILITY)),CreativeModeTabs.COMBAT);
    //? } else {
    /*public static final Item BANDIT_HOOD = PPRegistrar.registerItem("bandit_hood", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorItem.Type.HELMET, settings),
            new Item.Properties(),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GAMBESON = PPRegistrar.registerItem("bandit_gambeson", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorItem.Type.CHESTPLATE, settings),
            new Item.Properties(),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_GREAVES = PPRegistrar.registerItem("bandit_greaves", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorItem.Type.LEGGINGS, settings),
            new Item.Properties(),CreativeModeTabs.COMBAT);
    public static final Item BANDIT_BOOTS = PPRegistrar.registerItem("bandit_boots", settings -> new ArmorItem(RaccoonsRabiesArmorMaterials.BANDIT_ARMOUR_MATERIAL, ArmorItem.Type.BOOTS, settings),
            new Item.Properties(),CreativeModeTabs.COMBAT);

    *///? }

    public static final Item BANDIT_UPGRADE = PPRegistrar.registerItem("bandit_upgrade", BanditSmithingTemplateItem::createBanditUpgrade, (new Item.Properties()).rarity(Rarity.UNCOMMON),CreativeModeTabs.INGREDIENTS);

    // Raccoon Spawn Egg
    public static final Item RACCOON_SPAWN_EGG = PPRegistrar.registerItem("raccoon_spawn_egg", (settings -> new SpawnEggItem(
            //? if <1.21.11
            RREntityTypes.RACCOON,
            //? if <=1.21.1
            Color.ORANGE.darker().getRGB(), Color.ORANGE.darker().darker().getRGB(),
            settings
                    //? if >1.21.4
                    //.spawnEgg(RREntityTypes.RACCOON)
    )), new Item.Properties(),CreativeModeTabs.SPAWN_EGGS);



    public static void initItems() {}

}
