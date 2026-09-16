package net.cordicus.raccoons.item.custom;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
//? if <1.21.11
import net.minecraft.Util;
//? if >=1.21.11
//import net.minecraft.resources.Identifier;
//? if <1.21.11
import net.minecraft.resources.ResourceLocation;
//? if >=1.21.11
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import java.util.List;

public class BanditSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    public static final Component BANDIT_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", RRIdentifier.of("smithing_template.bandit_upgrade.apply_to").id))
            .withStyle(DESCRIPTION_FORMAT);
    public static final Component BANDIT_UPGRADE_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", RRIdentifier.of("smithing_template.bandit_upgrade.ingredients").id))
            .withStyle(DESCRIPTION_FORMAT);
    public static final Component BANDIT_UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("upgrade", RRIdentifier.of("bandit_upgrade").id))
            .withStyle(TITLE_FORMAT);
    public static final Component BANDIT_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", RRIdentifier.of("smithing_template.base_slot_description").id));
    public static final Component BANDIT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", RRIdentifier.of("smithing_template.additions_slot_description").id));
    public static final Component BANDIT_UPGRADE_UPPGRADE_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", RRIdentifier.of("smithing_template.upgrade_description").id));
 
    private static final RRIdentifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = RRIdentifier.ofVanilla("item/empty_armor_slot_helmet");
    private static final RRIdentifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = RRIdentifier.ofVanilla("item/empty_armor_slot_chestplate");
    private static final RRIdentifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = RRIdentifier.ofVanilla("item/empty_armor_slot_leggings");
    private static final RRIdentifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = RRIdentifier.ofVanilla("item/empty_armor_slot_boots");
    private static final RRIdentifier EMPTY_SLOT_HOE_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_hoe");
    private static final RRIdentifier EMPTY_SLOT_AXE_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_axe");
    private static final RRIdentifier EMPTY_SLOT_SWORD_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_sword");
    private static final RRIdentifier EMPTY_SLOT_SHOVEL_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_shovel");
    private static final RRIdentifier EMPTY_SLOT_PICKAXE_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_pickaxe");
    private static final RRIdentifier EMPTY_SLOT_INGOT_TEXTURE = RRIdentifier.ofVanilla("item/empty_slot_ingot");

    public BanditSmithingTemplateItem(Component appliesToText, Component ingredientsText
            //? <=1.21.1
            //, Component upgradeText
            , Component baseSlotDescriptionText, Component additionsSlotDescriptionText,
                                      //? if >=1.21.11
                                      //List<Identifier>
                                      //? if <1.21.11
                                      List<ResourceLocation>
                                              emptyBaseSlotTextures,
                                      //? if >=1.21.11
                                      //List<Identifier>
                                      //? if <1.21.11
                                      List<ResourceLocation>
                                              emptyAdditionsSlotTextures, Properties settings) {
        super(appliesToText, ingredientsText
                //? if <=1.21.1
                //, upgradeText
                , baseSlotDescriptionText, additionsSlotDescriptionText,  emptyBaseSlotTextures, emptyAdditionsSlotTextures
                //? if >1.21.1
                , settings
        );
    }

    public static SmithingTemplateItem createBanditUpgrade(Item.Properties settings) {
        return new SmithingTemplateItem(BANDIT_UPGRADE_APPLIES_TO_TEXT,
                BANDIT_UPGRADE_INGREDIENTS_TEXT,
                BANDIT_UPGRADE_TEXT,
                BANDIT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                //? if <=1.21.1
                //BANDIT_UPGRADE_UPPGRADE_DESCRIPTION_TEXT,
                getBanditUpgradeEmptyBaseSlotTextures(),
                getBanditUpgradeEmptyAdditionsSlotTextures()
                //? if >1.21.1
                , settings
        );
    }

    //? if >=1.21.11
    //private static List<Identifier>
    //? if <1.21.11
    private static List<ResourceLocation>
    getBanditUpgradeEmptyBaseSlotTextures() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE.id,
                EMPTY_SLOT_SWORD_TEXTURE.id,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE.id,
                EMPTY_SLOT_PICKAXE_TEXTURE.id,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE.id,
                EMPTY_SLOT_AXE_TEXTURE.id,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE.id,
                EMPTY_SLOT_HOE_TEXTURE.id,
                EMPTY_SLOT_SHOVEL_TEXTURE.id
        );
    }

    //? if >=1.21.11
    //private static List<Identifier>
    //? if <1.21.11
    private static List<ResourceLocation>
    getBanditUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE.id);
    }

}
