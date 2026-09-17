package net.cordicus.raccoons;

import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.client.RaccoonRenderer;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.custom.RaccoonHandheldItem;
import net.cordicus.raccoons.porting.RRIdentifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
//? if <1.21.4
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;

public class RaccoonsRabiesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RREntityTypes.RACCOON, RaccoonRenderer::new);

        //? if <1.21.4 {
        FabricModelPredicateProviderRegistry.register(RaccoonsRabiesItems.RACCOON, RRIdentifier.of("type").id,
                (stack, world, entity, seed) -> ((float) RaccoonHandheldItem.getType(stack) / 10.0f));
        FabricModelPredicateProviderRegistry.register(RaccoonsRabiesItems.RACCOON, RRIdentifier.of("baby").id,
                (stack, world, entity, seed) -> RaccoonHandheldItem.isBaby(stack) ? 1.0f : 0.0f);
        //? }
    }
}