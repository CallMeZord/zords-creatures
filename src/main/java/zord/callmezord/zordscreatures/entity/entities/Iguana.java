package zord.callmezord.zordscreatures.entity.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;
import zord.callmezord.zordscreatures.misc.TagsGeneral;

public class Iguana extends Animal {

    //Extras
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public Iguana(EntityType<? extends Iguana> entityType, Level level) {
        super(entityType, level);
    }



//AI GOALS!
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new PanicGoal(this, 2.2));
        this.goalSelector.addGoal(1, new BreedGoal(this,1.15));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(TagsGeneral.Items.IGUANA_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }


    //ATTRIBUTES!
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.FOLLOW_RANGE, 20D)
                .add(Attributes.TEMPT_RANGE, 12D);
    }






    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(TagsGeneral.Items.IGUANA_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob partner) {
        return EntitiesGeneral.IGUANA.get().create(serverLevel, EntitySpawnReason.BREEDING);
    }




    //TICK -STUFFS! (like the bug)
    @Override
    public void tick() {
        super.tick();

        // PLAY IDLE IF IT'S NOT THERE
        if (!this.isMoving() && this.onGround()) {
            this.idleAnimationState.startIfStopped(this.tickCount);
        } else {
            this.idleAnimationState.stop();
        }

        // TICK ANIM
        this.idleAnimationState.animateWhen(!this.isMoving(), this.tickCount);
    }


    //CHECKS IF THE ENTITY IS MOVING
    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }
}
