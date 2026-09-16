package net.cordicus.raccoons.item.custom;

import net.cordicus.raccoons.porting.RRIdentifier;
//? if <1.21.11
import net.minecraft.Util;
//? if >=1.21.11
//import net.minecraft.util.Util;

import net.minecraft.tags.ItemTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
//? if >1.21.1 {
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
//? } else {
/*import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
*///? }
import java.util.EnumMap;
import java.util.List;

public class RaccoonsRabiesArmorMaterials
{
    public static final int BASE_DURABILITY = 33;
    //? if >1.21.1 {
    static ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(RRIdentifier.of("equipment_asset").id);
    public static final ResourceKey<EquipmentAsset> BANDIT_KEY = ResourceKey.create(REGISTRY_KEY, RRIdentifier.of("bandit").id);


    public static final ArmorMaterial BANDIT_ARMOUR_MATERIAL = new ArmorMaterial(BASE_DURABILITY, Util.make(new EnumMap<>(ArmorType.class), map ->
    {
        map.put(ArmorType.BOOTS, 2);
        map.put(ArmorType.LEGGINGS, 4);
        map.put(ArmorType.CHESTPLATE, 6);
        map.put(ArmorType.HELMET, 2);
        map.put(ArmorType.BODY, 4);
    }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0, 0, ItemTags.REPAIRS_DIAMOND_ARMOR, BANDIT_KEY);
    //? } else {
    /*public static final ArmorMaterial BANDIT_ARMOUR_MATERIAL = new ArmorMaterial(Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), map ->
    {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.BODY, 4);
    }), 5, SoundEvents.ARMOR_EQUIP_CHAIN, ()->{return Ingredient.of(Items.DIAMOND);}, List.of(new ArmorMaterial.Layer(RRIdentifier.of("bandit").id)),0,0);
    *///? }
}
