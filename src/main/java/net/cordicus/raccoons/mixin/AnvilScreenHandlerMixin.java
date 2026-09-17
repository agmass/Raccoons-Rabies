package net.cordicus.raccoons.mixin;

import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilScreenHandlerMixin extends ItemCombinerMenu {

    public AnvilScreenHandlerMixin(@Nullable MenuType<?> type, int syncId, Inventory playerInventory, ContainerLevelAccess context, ItemCombinerMenuSlotDefinition slotsManager) {
        super(type, syncId, playerInventory, context
                //? if >1.21.1
                //, slotsManager
        );
    }

    @Inject(method = "onTake", at = @At("TAIL"))
    private void raccoonsRabies$renamesUpdateRaccoonType(Player player, ItemStack stack, CallbackInfo ci) {
        if (!stack.is(RaccoonsRabiesItems.RACCOON)) return; // exits if stack is not a raccoon

        if (RaccoonEntity.getNameToVariant().containsKey(stack.getHoverName().getString().toLowerCase()) && RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
            RaccoonsRabiesComponents.RACCOON_HELD_DATA.set(
                    stack,
                    new RaccoonHandheldDataComponent(
                            RaccoonEntity.getNameToVariant().get(stack.getHoverName().getString().toLowerCase()),
                            RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).owner(),
                            RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).baby())
            );

        }
    }

    @Inject(method = "createResult", at = @At("TAIL"))
    private void raccoonsRabies$renamesUpdateRaccoonResultType(CallbackInfo ci) {
        ItemStack stack = this.resultSlots.getItem(getResultSlot());
        if (!stack.is(RaccoonsRabiesItems.RACCOON)) return; // exits if stack is not a raccoon

        if (RaccoonEntity.getNameToVariant().containsKey(stack.getHoverName().getString().toLowerCase()) && RaccoonsRabiesComponents.RACCOON_HELD_DATA.has(stack)) {
            RaccoonsRabiesComponents.RACCOON_HELD_DATA.set(
                    stack,
                    new RaccoonHandheldDataComponent(
                            RaccoonEntity.getNameToVariant().get(stack.getHoverName().getString().toLowerCase()),
                            RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).owner(),
                            RaccoonsRabiesComponents.RACCOON_HELD_DATA.get(stack).baby())
            );

        }
    }

}
