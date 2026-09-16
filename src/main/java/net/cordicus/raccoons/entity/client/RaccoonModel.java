package net.cordicus.raccoons.entity.client;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.cordicus.raccoons.porting.RRIdentifier;
//? if >=1.21.11
//import net.minecraft.resources.Identifier;
//? if <1.21.11
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
//? if <1.21.11
import software.bernie.geckolib.renderer.GeoRenderer;
//? if >=1.21.11
import software.bernie.geckolib.renderer.base.GeoRenderState;

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
			 //RaccoonEntity raccoon
			 //? if =1.21.4
			 RaccoonEntity raccoon,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			 //? if >=1.21.11
			 //GeoRenderState raccoon
	)
	{
		return RRIdentifier.of("geo/raccoon.geo.json").id;
	}

	@Override
	//? if <1.21.11
	public ResourceLocation
	//? if >=1.21.11
	//public Identifier
	getTextureResource(
			//? if <=1.21.1
			//RaccoonEntity raccoon
			//? if =1.21.4
			RaccoonEntity raccoon,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			//? if >=1.21.11
			//GeoRenderState raccoon
	) {
		return getRaccoonTexture(raccoon
			//? if <1.21.11
				, geoRenderer
		);
	}

	@Override
	//? if <1.21.11
	public ResourceLocation
	//? if >=1.21.11
	//public Identifier
	getAnimationResource(RaccoonEntity animatable) {
		return RRIdentifier.of("animations/raccoon.animation.json").id;
	}


	//? if <1.21.11
	public static ResourceLocation
	//? if >=1.21.11
	//public static Identifier
	getRaccoonTexture(
			//? if <=1.21.1
			//RaccoonEntity raccoonType
			//? if =1.21.4
			RaccoonEntity raccoonType,  @Nullable GeoRenderer<RaccoonEntity> geoRenderer
			//? if >=1.21.11
			//GeoRenderState raccoon
	) {
		//? if >=1.21.11
		//RaccoonRenderState raccoonType = (RaccoonRenderState) raccoon;
		return switch (raccoonType.getRaccoonType()) {
			case 1 -> {
				if (raccoonType.isInSittingPose()) {
					yield AMETHYST_S.id;
				}
				yield AMETHYST.id;
			}
			case 2 -> {
				if (raccoonType.isInSittingPose()) {
					yield ALBINO_S.id;
				}
				yield ALBINO.id;
			}
			case 4 -> {
				if (raccoonType.isInSittingPose()) {
					yield CORD_S.id;
				}
				yield CORD.id;
			}
			case 5 -> {
				if (raccoonType.isInSittingPose()) {
					yield NITRON_S.id;
				}
				yield NITRON.id;
			}
			case 6 -> {
				if (raccoonType.isInSittingPose()) {
					yield BANDIT_S.id;
				}
				yield BANDIT.id;
			}
			case 7 -> {
				if (raccoonType.isInSittingPose()) {
					yield YAK_S.id;
				}
				yield YAK.id;
			}
			case 8 -> {
				if (raccoonType.isInSittingPose()) {
					yield ROCKET_S.id;
				}
				yield ROCKET.id;
			}
			default -> {
				if (raccoonType.isInSittingPose()) {
					yield RACCOON_S.id;
				}
				yield RACCOON.id;
			}
		};
	}
}
