package net.cordicus.raccoons;

import net.cordicus.raccoons.command.SpawnRaccoonCommand;
import net.cordicus.raccoons.common.effect.RabiesEffect;
import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.porting.RRIdentifier;import net.cordicus.raccoons.sounds.RaccoonsRabiesSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaccoonsRabies implements ModInitializer {
	public static final String MOD_ID = "raccoons-rabies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Holder<MobEffect> RABIES_EFFECT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, RRIdentifier.of("rabies").id, new RabiesEffect());

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(RREntityTypes.RACCOON, RaccoonEntity.createRaccoonAttributes());
		RaccoonsRabiesItems.initItems();
		RREntityTypes.initMobEntities();
		RaccoonsRabiesComponents.init();
		RaccoonsRabiesSounds.registerSounds();
		RaccoonsRabiesTags.init();
		RaccoonsRabiesLootTableModifiers.modifyLootTables();
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST),
				MobCategory.CREATURE,
				RREntityTypes.RACCOON,
				8, 1, 4);


		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			SpawnRaccoonCommand.register(server.getCommands().getDispatcher(), null);
		});

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(((entity, source, baseDamageTaken) -> {
			if (source.getDirectEntity() != null) {
				if (source.getDirectEntity() instanceof RaccoonEntity raccoonEntity) {
					int check = raccoonEntity.getRandom().nextInt(100 + 1);
					if (check <= 50) { // 50% chance to give rabies, rabies duration is 40 ticks + (the check * 2) (min is 2 seconds, max is 140 ticks or 7 seconds)
						entity.addEffect(new MobEffectInstance(RaccoonsRabies.RABIES_EFFECT
								//? if <=1.20.4
								//.value()
								, (check * 2) + 40, 0));
					}
				}
			}
			return true;
		}));

	}
}
