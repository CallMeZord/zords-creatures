package zord.callmezord.zordscreatures.item.functional;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import zord.callmezord.zordscreatures.entity.entities.IguanaEggProjectile;

public class ItemIguanaEgg extends EggItem {
    public ItemIguanaEgg(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, @NotNull InteractionHand interHand) {
        ItemStack itemstack = player.getItemInHand(interHand);
        level.playSound((Entity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (level instanceof ServerLevel serverlevel) {
            Projectile.spawnProjectileFromRotation(IguanaEggProjectile::new, serverlevel, itemstack, player, 0.0F, 1.5F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResult.SUCCESS;
    }



}
