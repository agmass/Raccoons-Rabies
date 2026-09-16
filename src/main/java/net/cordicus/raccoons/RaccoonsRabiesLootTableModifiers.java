package net.cordicus.raccoons;

import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class RaccoonsRabiesLootTableModifiers {
    public static final ResourceKey<LootTable> STRONGHOLD_CORRIDOR_ID =
            ResourceKey.create(Registries.LOOT_TABLE, RRIdentifier.ofVanilla("chests/stronghold_corridor").id);
    public static final ResourceKey<LootTable> STRONGHOLD_CROSSING_ID =
            ResourceKey.create(Registries.LOOT_TABLE, RRIdentifier.ofVanilla("chests/stronghold_crossing").id);

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.equals(STRONGHOLD_CORRIDOR_ID)) {
                LootPool.Builder poolCorridorBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.3f)) // Drop %
                        .add(LootItem.lootTableItem(RaccoonsRabiesItems.BANDIT_UPGRADE))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());

                tableBuilder.pool(poolCorridorBuilder.build());
            }
            if (key.equals(STRONGHOLD_CROSSING_ID)) {
                LootPool.Builder poolCrossingBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.3f)) // Drop %
                        .add(LootItem.lootTableItem(RaccoonsRabiesItems.BANDIT_UPGRADE))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());

                tableBuilder.pool(poolCrossingBuilder.build());
            }
        });
    }
}
