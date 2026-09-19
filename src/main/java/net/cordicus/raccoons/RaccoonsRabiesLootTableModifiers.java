package net.cordicus.raccoons;

import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.porting.RRIdentifier;
//? if <=1.20.4
//import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
//? if >=1.21.1
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
//? if <1.21.11
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
//? if <26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
//? } else {
/*import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
*///? }

public class RaccoonsRabiesLootTableModifiers {

    //? if <=1.20.4 {
    /*public static final ResourceLocation STRONGHOLD_CORRIDOR_ID = BuiltInLootTables.STRONGHOLD_CORRIDOR;
    public static final ResourceLocation STRONGHOLD_CROSSING_ID = BuiltInLootTables.STRONGHOLD_CROSSING;
    *///? } else {
    public static final ResourceKey<LootTable> STRONGHOLD_CORRIDOR_ID =
            ResourceKey.create(Registries.LOOT_TABLE, RRIdentifier.ofVanilla("chests/stronghold_corridor").id);
    public static final ResourceKey<LootTable> STRONGHOLD_CROSSING_ID =
            ResourceKey.create(Registries.LOOT_TABLE, RRIdentifier.ofVanilla("chests/stronghold_crossing").id);
    //? }
    public static void modifyLootTables() {
        //? if >1.19 {

        LootTableEvents.MODIFY.register(
                //? if >=1.21.1
                (key, tableBuilder, source, registries) -> {
                //? if <=1.20.4
                //(manager, dataManager, key, tableBuilder,source) -> {

            if (key.equals(STRONGHOLD_CORRIDOR_ID)) {
                LootPool.Builder poolCorridorBuilder = LootPool.lootPool()
                        //? if >=26.3
                        //.setRolls(ContextIntProviders.exactly(1))
                        //? if <26.3
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.3f)) // Drop %
                        .add(LootItem.lootTableItem(RaccoonsRabiesItems.BANDIT_UPGRADE))
                        //? if >=26.3
                        //.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1,1))
                        //? if <26.3
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f))
                        .build());


                tableBuilder.pool(poolCorridorBuilder.build());
            }
            if (key.equals(STRONGHOLD_CROSSING_ID)) {
                LootPool.Builder poolCrossingBuilder = LootPool.lootPool()
                        //? if >=26.3
                        //.setRolls(ContextIntProviders.exactly(1))
                        //? if <26.3
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.3f)) // Drop %
                        .add(LootItem.lootTableItem(RaccoonsRabiesItems.BANDIT_UPGRADE))
                        //? if >=26.3
                        //.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1,1))
                        //? if <26.3
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f))
                        .build());

                tableBuilder.pool(poolCrossingBuilder.build());
            }
        });
        //? }
    }
}
