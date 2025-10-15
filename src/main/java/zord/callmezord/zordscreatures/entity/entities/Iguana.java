package zord.callmezord.zordscreatures.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zord.callmezord.zordscreatures.entity.GenderedAnimal;
import zord.callmezord.zordscreatures.entity.ai.AiBasking;
import zord.callmezord.zordscreatures.entity.ai.AiGenderedBreeding;
import zord.callmezord.zordscreatures.item.ItemsGeneral;
import zord.callmezord.zordscreatures.misc.LootTablesGeneral;
import zord.callmezord.zordscreatures.misc.TagsGeneral;


public class Iguana extends Animal implements GenderedAnimal {
    //Extras
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState baskingAnimationState = new AnimationState();

    private static final EntityDataAccessor<Boolean> IS_BASKING =
            SynchedEntityData.defineId(Iguana.class, EntityDataSerializers.BOOLEAN);

    public Iguana(EntityType<? extends Iguana> entityType, Level level) {
        super(entityType, level);
    }


    //AI GOALS!
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new AiGenderedBreeding<>(this, 1.25F));
        this.goalSelector.addGoal(3, new AiBasking(this, 1.1F, 1800, 1200, 2400, 16));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.2F));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25F, stack -> stack.is(TagsGeneral.Items.IGUANA_FOOD), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1F));

        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }


    //ATTRIBUTES!
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.FOLLOW_RANGE, 20D)
                .add(Attributes.TEMPT_RANGE, 12D);
    }



    //GENDER HANDLING
    //DATA READER & ACCESSOR
    @Override
    public boolean isMale() {
        return this.entityData.get(IS_MALE);}
    @Override
    public void setMale(boolean male) {
        this.entityData.set(IS_MALE, male);}

    private static final EntityDataAccessor<Boolean> IS_MALE =
            SynchedEntityData.defineId(Iguana.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_MALE, false);
        builder.define(IS_BASKING, false);
    }

    //READ & SAVE DATA
    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("IsMale", this.isMale());
    }
    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
            this.setMale(input.getBooleanOr("IsMale", this.isMale()));
    }



    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {

        if (spawnGroupData == null) {
            this.setMale(this.random.nextBoolean());
        }
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }
//


    //DROP SHED ON GROW UP
    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby()) {
            Level var2 = this.level();
            if (var2 instanceof ServerLevel serverlevel) {
                if (serverlevel.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                    this.dropFromGiftLootTable(serverlevel, LootTablesGeneral.IGUANA_GROW_UP, this::spawnAtLocation);
                }
            }
        }
    }



    //GENERALIZED / TASKS
        @Override
        public boolean isFood (ItemStack itemStack){
            return itemStack.is(TagsGeneral.Items.IGUANA_FOOD);
        }

        @Override
        public AgeableMob getBreedOffspring (@NotNull ServerLevel serverLevel, @NotNull AgeableMob partner){
            if (!serverLevel.isClientSide()) {
                if(!this.isMale()){
                    BlockPos pos = this.blockPosition();
                    this.spawnAtLocation(serverLevel, new ItemStack(ItemsGeneral.IGUANA_EGG.get()), 0.25F);

                    this.setAge(5400);
                    this.resetLove();

                    if (partner instanceof Animal mate) {
                        mate.resetLove();
                        mate.setAge(5400);
                    }
                }
            }
            return null;}



        //TICK -STUFFS! (like the bug)
        @Override
        public void tick () {
            super.tick();
            boolean moving = isMoving();

            if (!isBasking()) {
                // PLAY IDLE IF THE ENTITY STOPS MOVING
                this.idleAnimationState.animateWhen(!moving && this.onGround(), this.tickCount);
            }
            else {
                //BASKING ANIMATION
                this.baskingAnimationState.animateWhen(true, this.tickCount);
            }

        }


        //CHECKS IF THE ENTITY IS MOVING
        private boolean isMoving () {
            return this.getDeltaMovement().horizontalDistanceSqr() > 0.001D;
        }


        public void setBasking(boolean value) {
            this.entityData.set(IS_BASKING, value);
        }
        //BASKING VALUE
        public boolean isBasking() {
            return this.entityData.get(IS_BASKING);
        }
    }
