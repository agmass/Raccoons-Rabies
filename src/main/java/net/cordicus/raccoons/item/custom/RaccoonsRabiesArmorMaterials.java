package net.cordicus.raccoons.item.custom;

import net.cordicus.raccoons.porting.RRIdentifier;
//? if <1.21.11
import net.minecraft.Util;
//? if >=1.21.11
//import net.minecraft.util.Util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
//? if >1.21.1 {
/*import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
*///? } else {
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
//? }
import java.util.EnumMap;
import java.util.List;

public class RaccoonsRabiesArmorMaterials
{
    public static final int BASE_DURABILITY = 33;
    //? if >1.21.1 {
    /*public static final ResourceKey<EquipmentAsset> BANDIT_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, RRIdentifier.of("bandit").id);


    public static final ArmorMaterial BANDIT_ARMOUR_MATERIAL = new ArmorMaterial(BASE_DURABILITY, Util.make(new EnumMap<>(ArmorType.class), map ->
    {
        map.put(ArmorType.BOOTS, 2);
        map.put(ArmorType.LEGGINGS, 4);
        map.put(ArmorType.CHESTPLATE, 6);
        map.put(ArmorType.HELMET, 2);
        map.put(ArmorType.BODY, 4);
    }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0, 0, ItemTags.REPAIRS_DIAMOND_ARMOR, BANDIT_KEY);
    *///? } else if >1.20.4 {
    public static final ArmorMaterial BANDIT_ARMOUR_MATERIAL = new ArmorMaterial(Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), map ->
    {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.BODY, 4);
    }), 5, SoundEvents.ARMOR_EQUIP_CHAIN, ()->{return Ingredient.of(Items.DIAMOND);}, List.of(new ArmorMaterial.Layer(RRIdentifier.of("bandit").id)),0,0);
    //? } else {
    /*public static final ArmorMaterial BANDIT_ARMOUR_MATERIAL = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return BASE_DURABILITY;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case BOOTS -> 2;
                case LEGGINGS -> 4;
                case CHESTPLATE -> 6;
                case HELMET -> 2;
            };
        }

        @Override
        public int getEnchantmentValue() {
            return 5;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_CHAIN;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DIAMOND);
        }

        @Override
        public String getName() {
            return "bandit";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };
    *///? }
}
