package net.cordicus.raccoons.item.custom;

import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
//? if >1.21.1 {
//? } else {
/*import net.minecraft.world.item.*;
*///? }


public class RaccoonsRabiesArmourItem {

    public static boolean isWearingFullArmorSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(RaccoonsRabiesItems.BANDIT_HOOD)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(RaccoonsRabiesItems.BANDIT_GAMBESON)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(RaccoonsRabiesItems.BANDIT_GREAVES)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(RaccoonsRabiesItems.BANDIT_BOOTS);
    }
}
