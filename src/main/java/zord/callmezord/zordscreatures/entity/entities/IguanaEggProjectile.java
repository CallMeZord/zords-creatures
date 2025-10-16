package zord.callmezord.zordscreatures.entity.entities;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import zord.callmezord.zordscreatures.entity.EntitiesGeneral;
import zord.callmezord.zordscreatures.item.ItemsGeneral;


import java.util.Objects;
import java.util.Optional;

public class IguanaEggProjectile extends ThrowableItemProjectile {

    private static final EntityDimensions ZERO_SIZED_DIMENSIONS = EntityDimensions.fixed(0.0F, 0.0F);

    public IguanaEggProjectile(EntityType<? extends IguanaEggProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public IguanaEggProjectile(Level level, LivingEntity owner, ItemStack item) {
        super(EntityType.EGG, owner, level, item);
    }

    public IguanaEggProjectile(Level level, double x, double y, double z, ItemStack item) {
        super(EntityType.EGG, x, y, z, level, item);
    }

    public void handleEntityEvent(byte id) {
        if (id == 3) {
            double d0 = 0.08;

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08);
            }
        }

    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            if (this.random.nextInt(2) == 0) {
                int i = 1;
                if (this.random.nextInt(26) == 0) {
                    i = 2;
                }

                for(int j = 0; j < i; ++j) {
                    Iguana iguana = (Iguana) EntitiesGeneral.IGUANA.get().create(this.level(), EntitySpawnReason.TRIGGERED);
                    if (iguana != null) {
                        iguana.setAge(-24000);
                        iguana.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);

                        Objects.requireNonNull(iguana);
                        if (!iguana.fudgePositionAfterSizeChange(ZERO_SIZED_DIMENSIONS)) {
                            break;
                        }
                        iguana.setMale(this.random.nextBoolean());

                        if (this.random.nextInt(100) == 0) {iguana.setAlbino(true);} else {iguana.setAlbino(false);}

                        this.level().addFreshEntity(iguana);
                    }}}

            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        } }


    protected Item getDefaultItem() {
        return ItemsGeneral.IGUANA_EGG.get();
    }
}
