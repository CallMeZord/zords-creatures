package zord.callmezord.zordscreatures.entity.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;

public class Iguana extends Animal {


    public Iguana(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }




//AI GOALS!
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new PanicGoal(this, 2));
        this.goalSelector.addGoal(1, new BreedGoal(this,1.2));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.getItem() == Items.SWEET_BERRIES, false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    //ATTRIBUTES!
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }






    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem() == Items.SWEET_BERRIES;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntitiesGeneral.IGUANA.get().create(serverLevel, EntitySpawnReason.BREEDING);
    }





}
