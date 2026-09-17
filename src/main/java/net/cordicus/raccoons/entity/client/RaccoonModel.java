package net.cordicus.raccoons.entity.client;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.porting.RRIdentifier;
//? if >=1.21.11
//import net.minecraft.resources.Identifier;
//? if <1.21.11
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
//? if <1.21.11
import software.bernie.geckolib.renderer.GeoRenderer;

//? <26.1 {
import software.bernie.geckolib.model.GeoModel;
//? if >=1.21.11
//import software.bernie.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
*///? }

public class RaccoonModel extends GeoModel<RaccoonEntity> {
	//AWAKE
	private static final RRIdentifier RACCOON = RRIdentifier.of("textures/entity/raccoon.png");
	private static final RRIdentifier AMETHYST = RRIdentifier.of("textures/entity/amethyst_raccoon.png");
	private static final RRIdentifier ALBINO = RRIdentifier.of("textures/entity/albino_raccoon.png");
	//SLEEPING
	private static final RRIdentifier RACCOON_S = RRIdentifier.of("textures/entity/raccoon_sleeping.png");
	private static final RRIdentifier AMETHYST_S = RRIdentifier.of("textures/entity/amethyst_raccoon_sleeping.png");
	private static final RRIdentifier ALBINO_S = RRIdentifier.of("textures/entity/albino_raccoon_sleeping.png");

	private static final RRIdentifier CORD = RRIdentifier.of("textures/entity/cord_raccoon.png");
	private static final RRIdentifier NITRON = RRIdentifier.of("textures/entity/nitron_raccoon.png");
	private static final RRIdentifier CORD_S = RRIdentifier.of("textures/entity/cord_raccoon_sleeping.png");
	private static final RRIdentifier NITRON_S = RRIdentifier.of("textures/entity/nitron_raccoon_sleeping.png");
	private static final RRIdentifier BANDIT = RRIdentifier.of("textures/entity/bandit_raccoon.png");
	private static final RRIdentifier BANDIT_S = RRIdentifier.of("textures/entity/bandit_raccoon_sleeping.png");
	private static final RRIdentifier YAK = RRIdentifier.of("textures/entity/yak_raccoon.png");
	private static final RRIdentifier YAK_S = RRIdentifier.of("textures/entity/yak_raccoon_sleeping.png");
	private static final RRIdentifier ROCKET = RRIdentifier.of("textures/entity/rocket_raccoon.png");
	private static final RRIdentifier ROCKET_S = RRIdentifier.of("textures/entity/rocket_raccoon_sleeping.png");



	@Override
	//? if <1.21.11
	public ResourceLocation
	//? if >=1.21.11
	//public Identifier
	getModelResource(
			 //? if <=1.21.1
			 RaccoonEntity raccoon
			 //? if =1.21.4
			 //RaccoonEntity raccoon,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			 //? if >=1.21.11
			 //GeoRenderState raccoon
	)
	{
		//? if  <1.21.11
		return RRIdentifier.of("geo/raccoon.geo.json").id;
		//? if >=1.21.11
		//return RRIdentifier.of("entity/raccoon").id;
	}

	@Override
	//? if <1.21.11
	public ResourceLocation
	//? if >=1.21.11
	//public Identifier
	getTextureResource(
			//? if <=1.21.1
			RaccoonEntity raccoon
			//? if =1.21.4
			//RaccoonEntity raccoon,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			//? if >=1.21.11
			//GeoRenderState raccoon
	) {
		return getRaccoonTexture(raccoon
			//? if <1.21.11 && >1.21.1
				//, geoRenderer
		);
	}

	@Override
	//? if <1.21.11
	public ResourceLocation
	//? if >=1.21.11
	//public Identifier
	getAnimationResource(RaccoonEntity animatable) {
		//? if <1.21.11
		return RRIdentifier.of("animations/raccoon.animation.json").id;
		//? if >=1.21.11
		//return RRIdentifier.of("entity/raccoon").id;
	}


	//? if <1.21.11
	public static ResourceLocation
	//? if >=1.21.11
	//public static Identifier
	getRaccoonTexture(
			//? if <=1.21.1
			RaccoonEntity raccoonType
			//? if =1.21.4
			//RaccoonEntity raccoonType,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			//? if >=1.21.11
			//GeoRenderState raccoon
	) {
		//? if >=1.21.11 {
		/*boolean isSitting = raccoon.getGeckolibData(RaccoonRenderer.isSitting);
		return switch (raccoon.getGeckolibData(RaccoonRenderer.type).intValue()) {
		*///? } else {
		
		boolean isSitting = raccoonType.isInSittingPose();
		return switch (raccoonType.getRaccoonType()) {
		//? }

			case 1 -> {
				if (isSitting) {
					yield AMETHYST_S.id;
				}
				yield AMETHYST.id;
			}
			case 2 -> {
				if (isSitting) {
					yield ALBINO_S.id;
				}
				yield ALBINO.id;
			}
			case 4 -> {
				if (isSitting) {
					yield CORD_S.id;
				}
				yield CORD.id;
			}
			case 5 -> {
				if (isSitting) {
					yield NITRON_S.id;
				}
				yield NITRON.id;
			}
			case 6 -> {
				if (isSitting) {
					yield BANDIT_S.id;
				}
				yield BANDIT.id;
			}
			case 7 -> {
				if (isSitting) {
					yield YAK_S.id;
				}
				yield YAK.id;
			}
			case 8 -> {
				if (isSitting) {
					yield ROCKET_S.id;
				}
				yield ROCKET.id;
			}
			default -> {
				if (isSitting) {
					yield RACCOON_S.id;
				}
				yield RACCOON.id;
			}
		};
	}
}
