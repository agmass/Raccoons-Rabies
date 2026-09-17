package net.cordicus.raccoons;

import net.cordicus.raccoons.datagen.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RaccoonsRabiesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModTagProviders.ItemTags::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelDatagen::new);
	}
}
