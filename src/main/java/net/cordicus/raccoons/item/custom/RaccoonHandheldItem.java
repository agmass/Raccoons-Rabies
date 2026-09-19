package net.cordicus.raccoons.item.custom;

//? <26.1 {
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
//? } else {
/*import eu.pb4.trinkets.api.DefaultTrinketSlots;
import eu.pb4.trinkets.api.TrinketsApi;
import eu.pb4.trinkets.api.TrinketAttachment;
*///? }
import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
//? if >1.20.1
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
//? if >=1.21.4 {
/*import net.minecraft.core.component.DataComponents;
*///? }
import net.minecraft.world.item.context.UseOnContext;
//? if >=1.21.11 {
/*import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.item.component.TooltipDisplay;
*///? }
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RaccoonHandheldItem extends Item {

    public RaccoonHandheldItem(Properties settings) {
        super(settings);
    }

    public static int getType(ItemStack stack) {
        if (!RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
            RaccoonsRabiesComponents.RACCOON_HELD_DATA.set(stack, new RaccoonHandheldDataComponent(0, "", false));
        }
        return RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).type();
    }

    public static void setType(ItemStack stack, int type) {

        RaccoonHandheldDataComponent component = new RaccoonHandheldDataComponent(0, "", false);
        if (RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
            component = RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack);
        }

        RaccoonsRabiesComponents.RACCOON_HELD_DATA.set(stack, component);
    }

    public static boolean isBaby(ItemStack stack) {
        if (!RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
            return false;
        }
        return RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).baby();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Vec3 hitResult = context.getClickLocation();

        Vec3 spawnLocation = new Vec3(hitResult.x, hitResult.y, hitResult.z);
        if (!context.getLevel().isClientSide()) {
            RaccoonEntity entity = new RaccoonEntity(RREntityTypes.RACCOON, context.getLevel());
            entity.setPos(spawnLocation.x, spawnLocation.y, spawnLocation.z);
            if (RaccoonsRabiesComponents.RACCOON_DATA.has(context.getItemInHand())) {
                CompoundTag nbt = RaccoonsRabiesComponents.RACCOON_DATA.get(context.getItemInHand()).copyTagWithoutId();
                if (nbt != null) {
                    //? if <1.21.11 {
                    entity.load(nbt);
                    entity.readAdditionalSaveData(nbt);
                    //? } else {
                    /*ValueInput valueInput = TagValueInput.create(ProblemReporter.DISCARDING, entity.registryAccess(),nbt);
                    entity.load(valueInput);
                    *///? }
                    entity.setPos(spawnLocation.x, spawnLocation.y, spawnLocation.z);
                }
            }
            if (RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(context.getItemInHand())) {
                RaccoonHandheldDataComponent component = RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(context.getItemInHand());
                if (!component.owner().isEmpty()) {
                    entity.setTame(true
                            //? if >=1.21.1
                            , true
                    );
                    entity.tame(context.getPlayer());
                    entity.setOrderedToSit(context.getPlayer() != null && context.getPlayer().isShiftKeyDown()); // if player is sneaking when placing sets the raccoon to be sitting
                    entity.setInSittingPose(context.getPlayer() != null && context.getPlayer().isShiftKeyDown());
                }
                else {
                    entity.setTame(false
                            //? if >=1.21.1
                            , true
                    );
                    entity.setOrderedToSit(false);
                    entity.setInSittingPose(false);
                }
                entity.setRaccoonType(component.type());
                entity.setBaby(component.baby());
            }
            else { // in the case of no data at all, falls back on this as the default
                entity.setTame(false
                        //? if >=1.21.1
                        , true
                );
                entity.setRaccoonType(0);
            }
            if (!context.getItemInHand().getHoverName().equals(this.getDefaultInstance().getHoverName())) { // custom item name :p
                entity.setCustomName(context.getItemInHand().getHoverName().copy().withStyle(ChatFormatting.RESET));
            }
            context.getItemInHand().shrink(1);
            context.getLevel().addFreshEntity(entity);
        }

        return InteractionResult.SUCCESS;
    }

    public static int AMETHYST = 1;
    public static int ALBINO = 2;
    public static int CORDICUS = 4;
    public static int NITRON = 5;
    public static int BANDIT = 6;
    public static int YAK = 7;
    public static int ROCKET = 8;
    public static int NORMAL = 0;

    //? if >=1.21.1 {
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        int raccoonType = RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).type();

        if (RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).baby()) {
            switch (raccoonType) {
                case 1: tooltip.add(Component.literal("Amethyst (Baby)").withColor((0xC890F0))); break;
                case 2: tooltip.add(Component.literal("Albino (Baby)").withColor((0x796A63))); break;
                case 4: tooltip.add(Component.literal("Cordicus (Baby)").withColor((0xfb6cc4))); break;
                case 5: tooltip.add(Component.literal("Nitron (Baby)").withColor((0xff004f))); break;
                case 6: tooltip.add(Component.literal("Bandit (Baby)").withColor((0x8C6E56))); break;
                case 7: tooltip.add(Component.literal("Yak (Baby)").withColor((0x3FC2EA))); break;
                case 8: tooltip.add(Component.literal("Rocket (Baby)").withColor((0x1B46DE))); break;
                default: tooltip.add(Component.literal("Normal (Baby)").withStyle(ChatFormatting.DARK_GRAY)); break;
            }
        }
        else {
            switch (raccoonType) {
                case 1: tooltip.add(Component.literal("Amethyst").withColor((0xC890F0))); break;
                case 2: tooltip.add(Component.literal("Albino").withColor((0x796A63))); break;
                case 4: tooltip.add(Component.literal("Cordicus").withColor((0xfb6cc4))); break;
                case 5: tooltip.add(Component.literal("Nitron").withColor((0xff004f))); break;
                case 6: tooltip.add(Component.literal("Bandit").withColor((0x8C6E56))); break;
                case 7: tooltip.add(Component.literal("Yak").withColor((0x3FC2EA))); break;
                case 8: tooltip.add(Component.literal("Rocket").withColor((0x1B46DE))); break;
                default: tooltip.add(Component.literal("Normal").withStyle(ChatFormatting.DARK_GRAY)); break;
            }
        }
    }
    //? }

    public static boolean hasRaccoonEquipped(LivingEntity livingEntity) {
        //? if <26.1 {
        Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(livingEntity);
        return trinketComponent.map(component -> component.isEquipped(RaccoonsRabiesItems.RACCOON)).orElse(false);
        //? } else {
        /*TrinketAttachment trinketComponent = TrinketsApi.getAttachment(livingEntity);
        return trinketComponent.isEquipped(RaccoonsRabiesItems.RACCOON);
        *///? }
    }

    public static ItemStack getRaccoonOnHead(LivingEntity livingEntity) {

        //? if <26.1 {
        Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(livingEntity);
        return trinketComponent.map(component -> component.getEquipped(RaccoonsRabiesItems.RACCOON).get(0).getB()).orElse(ItemStack.EMPTY);
        //? } else {
        /*TrinketAttachment trinketComponent = TrinketsApi.getAttachment(livingEntity);
        if (trinketComponent.isEquipped(RaccoonsRabiesItems.RACCOON)) {
            return trinketComponent.getSlotAccess(DefaultTrinketSlots.HEAD_HAT, 0).get();
        }
        return null;
        *///? }

    }

}
