package net.cordicus.raccoons.entity.custom;

import net.cordicus.raccoons.RaccoonsRabies;
import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.item.RaccoonsRabiesItems;
import net.cordicus.raccoons.item.RaccoonsRabiesTags;
import net.cordicus.raccoons.item.component.PPTypedEntityData;
import net.cordicus.raccoons.item.component.RaccoonHandheldDataComponent;
import net.cordicus.raccoons.item.component.RaccoonsRabiesComponents;
import net.cordicus.raccoons.sounds.RaccoonsRabiesSounds;
import net.minecraft.ChatFormatting;
//? <26.2
import net.minecraft.advancements.CriteriaTriggers;
//? >=26.2
//import net.minecraft.advancements.triggers.CriteriaTriggers;
//? if >=1.21.1
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
//? if >1.20.1
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
//? if >1.21.1
//import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
//? if >=1.21.11 {
/*import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? }
//? if >=1.21.1
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
//? if <=1.20.4 {
/*import net.minecraft.world.level.GameRules;
 *///? } else if <=1.21.4 {
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.PlayState;
import net.minecraft.world.level.GameRules;
//? }
//? <26.1 {

//?if >=1.21.11
//import software.bernie.geckolib.animatable.manager.AnimatableManager;
//? if >1.20.4
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
//? if <=1.20.4 {
/*
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
*///? } else {
import software.bernie.geckolib.animation.AnimationController;
//? }
import software.bernie.geckolib
        //? if <=1.20.4
        //.core
        .animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
//? if >=1.21.4
//import software.bernie.geckolib.animatable.GeoEntity;
//? } else {
/*import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animation.RawAnimation;
import com.geckolib.util.GeckoLibUtil;
import com.geckolib.animatable.manager.AnimatableManager;
*///? }

import java.util.Map;
import java.util.UUID;

public class RaccoonEntity extends TamableAnimal implements NeutralMob, GeoEntity
        //? if <=1.20.4
        //, GeoAnimatable
{
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(RaccoonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IDLED = SynchedEntityData.defineId(RaccoonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(RaccoonEntity.class, EntityDataSerializers.INT);
    private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID angryAt;


    public RaccoonEntity(EntityType<? extends Animal> entityType, Level world) {
        super((EntityType<? extends TamableAnimal>) entityType, world);
    }

    private static final Map<String, Integer> NAME_TO_VARIANT = Map.of(
            "cord", 4,
            "cordicus", 4,
            "nitron", 5,
            "n1tr0n", 5,
            "n1tr0n__", 5,
            "bandit", 6,
            "yak", 7,
            "thetrueyak", 7,
            "rocket", 8,
            "rocket raccoon", 8
    );

    public static Map<String, Integer> getNameToVariant() {
        return NAME_TO_VARIANT;
    }


    @Override
    //? if <=1.21.4
    public void readAdditionalSaveData(CompoundTag nbt) {
        //? if >=1.21.11
        //protected void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        int type = nbt.getInt("Type")
                //? if >=1.21.11
                //.get()
                ;
        this.setRaccoonType(type);
    }
    @Override
    //? if <=1.21.4
    public void addAdditionalSaveData(CompoundTag nbt) {
        //? if >=1.21.11
        //protected void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Type", this.getRaccoonType());
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        if (name != null) {
            String nameString = name.getString();
            if (NAME_TO_VARIANT.containsKey((nameString.toLowerCase()))) {  // named variants are no longer case-sensitive
                this.setRaccoonType(NAME_TO_VARIANT.getOrDefault(nameString.toLowerCase(), 0));
            }
        }
    }



    //? if >1.20.4 {
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TYPE, 0);
        builder.define(IDLED, false);
        builder.define(ANGER_TIME, 0);
    }
    //? } else {
    /*@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TYPE, 0);
        this.entityData.define(IDLED, false);
        this.entityData.define(ANGER_TIME, 0);
    }
    *///? }

    public int getRaccoonType() {
        return this.entityData.get(TYPE);
    }
    public boolean getRaccoonIdle() {
        return this.entityData.get(IDLED);
    }

    public void setRaccoonType(int type) {
        this.entityData.set(TYPE, type);
    }

    public void setRaccoonIdle(boolean bool) {
        this.entityData.set(IDLED, bool);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            this.idleAnimationTimeout--;
        }

    }

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.raccoon.idle");
    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.raccoon.walk");
    protected static final RawAnimation SIT = RawAnimation.begin().thenLoop("animation.raccoon.sitting");


    //? if <=1.21.4 {
    protected <E extends RaccoonEntity> PlayState raccoonAnimController(final software.bernie.geckolib
            //? if <=1.20.4
            //.core
            .animation.AnimationState<E> event) {
        if (isInSittingPose()) {
            return event.setAndContinue(SIT);
        }
        else if (event.isMoving()) {
            return event.setAndContinue(WALK);
        }
        return event.setAndContinue(IDLE);
    }
    //? } else {
    /*protected AnimationController.AnimationStateHandler<RaccoonEntity> raccoonAnimController() {
        return test -> {
            if (test.animatable().isInSittingPose()) {
                return test.setAndContinue(SIT);
            } else if (test.isMoving()) {
                return test.setAndContinue(WALK);
            }
            return test.setAndContinue(IDLE);
        };
    }
    *///? }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        //? if <=1.21.4
        controllers.add(new AnimationController<>(this, "raccoon", 5, this::raccoonAnimController));
        //? if >1.21.4
        //controllers.add(new AnimationController<>(raccoonAnimController()));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0F, 1.0F): 0.0f;
        this.walkAnimation.update(f, 0.2F
                //? if >1.21.1
                //, 1f
        );
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getPLevel().isClientSide()) {
            setupAnimationStates();
        }
    }

    public Level getPLevel() {
        //? if <=1.19
        //return level;
        //? if >1.19
        return level();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.150));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.250, Ingredient.of(Items.ROTTEN_FLESH), true));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.150));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F
                //? if <=1.20.4
                //, false
        ));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }


    @Override
    //? if <=1.20.4
    //public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag compoundTag) {
    //? if >1.20.4 {
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty,
                                        //? if >1.21.1
                                        //EntitySpawnReason spawnReason
                                        //? if <=1.21.1
                                        MobSpawnType spawnReason
            , @Nullable SpawnGroupData entityData) {
        //? }
        this.setRaccoonType(this.getRandomRaccoonType());

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData
                //? if <=1.20.4
                //, compoundTag
        );
    }

    public int getRandomRaccoonType() { // gets random raccoon type using original code
        int number = this.random.nextInt(100) + 1;
        if (number == 1) {
            // AMETHYST
            return 1;
        } else if (number <= 19) {
            // ALBINO
            return 2;
        }
        // NORMAL
        return 0;
    }

    public int getWeightedRaccoonType(int type1, int type2) { // takes 2 ints, ~45% chance of being type1, ~45% chance of being type2, and ~10% chance to be a random type
        int number = this.random.nextInt(11);
        if (number < 4) { // 0-4, type1
            return type1;
        }
        else if (number < 10) { // 5-9, type 2
            return type2;
        }
        return getRandomRaccoonType(); // 10, random type
    }

    public static AttributeSupplier.Builder createRaccoonAttributes() {
        //? if >1.21.1
        //return Animal.createAnimalAttributes()
                //? if <=1.21.1
                return Animal.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 4.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.MAX_HEALTH, 8.0f)
                .add(Attributes.ARMOR, 0.5f);
    }

    //? if <=1.20.4 {
    /*@Override
    public void tame(Player player) {
        super.tame(player);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0f);
        this.setHealth(12.0f);
    }
    *///? } else {
    @Override
    protected void applyTamingSideEffects() {
        if (this.isTame()) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0f);
            this.setHealth(12.0f);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(4.0f);
        }
    }
    //? }

    @Override
    public boolean isFood(ItemStack stack) {
        //? if >=1.21.1
        return stack.has(DataComponents.FOOD);
        //? if <1.21.1 && >1.20.1
        //return stack.getFoodComponent() != null;
        //? if <=1.20.1
        //return stack.getItem().getFoodProperties() != null;
    }

    public boolean doHurtTarget(ServerLevel world, Entity target) {
        boolean bl = super.doHurtTarget(
                //? if >1.21.1
                //world,
                target);
        if (bl) {
            int check = this.getRandom().nextInt(100 + 1);
            if (target instanceof LivingEntity livingEntity && check <= 50) { // 50% chance to give rabies, rabies duration is 40 ticks + (the check * 2) (min is 2 seconds, max is 140 ticks or 7 seconds)
                livingEntity.addEffect(new MobEffectInstance(RaccoonsRabies.RABIES_EFFECT
                        //? if <=1.20.4
                        //.value()
                        , (check * 2) + 40, 0));
            }
        }
        return bl;
    }


    public boolean isAngryAtAllPlayers(ServerLevel world) {
        //? if <1.21.11
        return world.getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER) && this.isAngry() && this.getPersistentAngerTarget() == null;
        //? if >=1.21.11
        //return world.getGameRules().get(GameRules.UNIVERSAL_ANGER) && this.isAngry() && this.getPersistentAngerTarget() == null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(!this.getPLevel().isClientSide()){
            if(itemStack.isEmpty() && player.isShiftKeyDown() && ((this.isTame() && this.isOwnedBy(player)) || player.getAbilities().instabuild)) { // creative mode players can always pick up raccoons, otherwise requires them to be tamed and the player to own them
                this.discard();
                player.setItemInHand(hand, new ItemStack(RaccoonsRabiesItems.RACCOON));
                ItemStack handStack = player.getItemInHand(hand);

                //? if <=1.21.4 {

                CompoundTag nbt = new CompoundTag();
                this.save(nbt);
                this.saveWithoutId(nbt);
                this.addAdditionalSaveData(nbt);
                if (this.isTame()) { // keeping this just in case :p
                    nbt.putUUID("Owner", this.getOwnerUUID());
                }
                String ownerUuid = (this.isTame() && this.getOwnerUUID() != null) ? this.getOwnerUUID().toString() : ""; // preserved entity nbt and essential raccoon entity data are now stored separately for convenience and optimization reasons

                //? } else {
                /*TagValueOutput tagValueOutput = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, player.registryAccess());
                save(tagValueOutput);
                saveWithoutId(tagValueOutput);
                addAdditionalSaveData(tagValueOutput);
                CompoundTag nbt = tagValueOutput.buildResult();

                String ownerUuid = (this.isTame() && getOwnerReference() != null) ? getOwnerReference().getUUID().toString() : "";

                *///? }


                RaccoonsRabiesComponents.RACCOON_HELD_DATA.set(handStack, new RaccoonHandheldDataComponent(this.getRaccoonType(), ownerUuid, this.isBaby()));
                RaccoonsRabiesComponents.RACCOON_DATA.set(handStack, PPTypedEntityData.of(RREntityTypes.RACCOON, nbt));
                if (this.getCustomName() != null) { // sets custom name to item name too
                    //? if >=1.21.1
                    handStack.set(DataComponents.CUSTOM_NAME, (this.getCustomName().copy().withStyle(ChatFormatting.ITALIC)));
                    //? if <=1.20.4
                    //handStack.setHoverName((this.getCustomName().copy().withStyle(ChatFormatting.ITALIC)));
                }
                return InteractionResult.SUCCESS;
            }
        }

        if (this.getPLevel().isClientSide()) {
            boolean bl = this.isOwnedBy(player) || this.isTame() || isFood(itemStack) && !this.isTame() && !this.isAngry();
            return bl ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth()) {
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                //? if >=1.21.1
                float f = itemStack.get(DataComponents.FOOD) != null ? (float)itemStack.get(DataComponents.FOOD).nutrition() : 1.0F;
                //? if <=1.20.4 && >1.20.1
                //float f = itemStack.getFoodComponent() != null ? (float)itemStack.getFoodComponent().getNutrition() : 1.0F;
                //? if <=1.20.1
                //float f = itemStack.getItem().getFoodProperties() != null ? (float)itemStack.getItem().getFoodProperties().getNutrition() : 1.0F;
                this.heal(2.0F * f);
                return InteractionResult.SUCCESS;
            } else {
                InteractionResult actionResult = super.mobInteract(player, hand);
                if ((!actionResult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                } else {
                    return actionResult;
                }
            }
        } else if (isFood(itemStack) && !this.isAngry()) {
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);
                this.getPLevel().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
            } else {
                this.getPLevel().broadcastEntityEvent(this, EntityEvent.TAMING_FAILED);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0, (0.55F * this.getEyeHeight()), (this.getBbWidth() * 0.4F));
    }

    class EscapeWhenNotAggressiveGoal extends PanicGoal {
        public EscapeWhenNotAggressiveGoal(double speed) {
            super(RaccoonEntity.this, speed);
        }

        @Override
        public boolean shouldPanic() {
            return !RaccoonEntity.this.isAggressive() && super.shouldPanic();
        }
    }

    public boolean isAggressive() {
        return false;
    }

    class MateGoal extends BreedGoal {
        public MateGoal(double chance) {
            super(RaccoonEntity.this, chance);
        }

        @Override
        protected void breed() {
            ServerLevel serverWorld = (ServerLevel)this.level;
            RaccoonEntity raccoonEntity = (RaccoonEntity) ((RaccoonEntity) this.animal).getBreedOffspring(serverWorld, this.partner);
            if (raccoonEntity != null) {
                ServerPlayer serverPlayerEntity = this.animal.getLoveCause();
                ServerPlayer serverPlayerEntity2 = this.partner.getLoveCause();
                ServerPlayer serverPlayerEntity3 = serverPlayerEntity;
                if (serverPlayerEntity != null) {
                    raccoonEntity.addTrustedUuid(serverPlayerEntity.getUUID());
                } else {
                    serverPlayerEntity3 = serverPlayerEntity2;
                }

                if (serverPlayerEntity2 != null && serverPlayerEntity != serverPlayerEntity2) {
                    raccoonEntity.addTrustedUuid(serverPlayerEntity2.getUUID());
                }

                if (serverPlayerEntity3 != null) {
                    serverPlayerEntity3.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverPlayerEntity3, this.animal, this.partner, raccoonEntity);
                }

                this.animal.setAge(6000);
                this.partner.setAge(6000);
                this.animal.resetLove();
                this.partner.resetLove();
                raccoonEntity.setAge(-24000);
                raccoonEntity.setPos(this.animal.getX(), this.animal.getY(), this.animal.getZ());
                serverWorld.addFreshEntityWithPassengers(raccoonEntity);
                this.level.broadcastEntityEvent(this.animal, EntityEvent.IN_LOVE_HEARTS);
                //? if <1.21.11
                if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                //? if >=1.21.11
                //if (this.level.getGameRules().get(GameRules.MOB_DROPS).booleanValue()) {
                    this.level
                            .addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), this.animal.getRandom().nextInt(7) + 1));
                }

            }
        }
    }

    private void addTrustedUuid(UUID uuid) {

    }

    @Nullable
    public RaccoonEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        int childType = getWeightedRaccoonType(this.getRaccoonType(), ((RaccoonEntity) passiveEntity).getRaccoonType());
        RaccoonEntity raccoonEntity = (RaccoonEntity) RREntityTypes.RACCOON.create(serverWorld
                //? >1.21.1
                //, EntitySpawnReason.BREEDING
        );
        if (raccoonEntity != null) {
            raccoonEntity.setRaccoonType(childType);
            //? if <=1.21.4
            UUID uUID = this.getOwnerUUID();
            //? if >=1.21.11
            //UUID uUID = this.getOwnerReference().getUUID();
            if (uUID != null) {

                //? if <=1.21.4
                raccoonEntity.setOwnerUUID(uUID);
                //? if >=1.21.11
                //raccoonEntity.setOwnerReference(EntityReference.of(uUID));
                raccoonEntity.setTame(true
                        //? if >=1.21.1
                        , true
                );
            }
        }
        return raccoonEntity;
    }

    @Override
    public int getMaxHeadXRot() {
        return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
    }

    //? if <=1.21.4 {
    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int angerTime) {
        this.entityData.set(ANGER_TIME, angerTime);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.angryAt;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
    }
    //? } else {
    /*@Override
    public long getPersistentAngerEndTime() {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setPersistentAngerEndTime(long l) {
        this.entityData.set(ANGER_TIME, (int) l);
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
        return EntityReference.of(angryAt);
    }

    @Override
    public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> entityReference) {
        angryAt = entityReference.getUUID();
    }

    @Override
    public void startPersistentAngerTimer() {
        setPersistentAngerEndTime(ANGER_TIME_RANGE.sample(random));
    }

    *///? }




    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(this.getRaccoonType() == 1){
            return SoundEvents.AMETHYST_BLOCK_CHIME;
        }
        return RaccoonsRabiesSounds.ENTITY_RACCOON_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if(this.getRaccoonType() == 1){
            return SoundEvents.AMETHYST_BLOCK_STEP;
        }
        return RaccoonsRabiesSounds.ENTITY_RACCOON_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        if(this.getRaccoonType() == 1){
            return SoundEvents.AMETHYST_BLOCK_BREAK;
        }
        return RaccoonsRabiesSounds.ENTITY_RACCOON_DEATH;
    }

    //? if >1.21.1
    //public boolean hurtServer(ServerLevel world, DamageSource source, float amount) { // owner cannot hit their own raccoons
        //? if <=1.21.1
        public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player && this.isTame()) {
            if (this.getOwner() != null && player.equals(this.getOwner()) && !player.getMainHandItem().is(RaccoonsRabiesTags.HITTABLE)) {
                return false;
            }
        }

        //? if >1.21.1
        //return super.hurtServer(world, source, amount);
        //? if <=1.21.1
        return super.hurt(source, amount);
    }
}
