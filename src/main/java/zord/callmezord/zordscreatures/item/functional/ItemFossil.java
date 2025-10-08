package zord.callmezord.zordscreatures.item.functional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.HashMap;
import java.util.List;


public class ItemFossil extends Item {

public ItemFossil(Properties properties) {
    super(properties);
}

    @Override
    public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos pos = context.getClickedPos();
    Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
Player player = context.getPlayer();

//are they crawling yet?
    if (clickedBlock == Blocks.GRINDSTONE && player.isCrouching()) {
        if(!level.isClientSide()) {

            // Item-eater & screamer & other effects
            context.getItemInHand().shrink(1);
            level.playSound(null, context.getClickedPos(),  SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.6F, 1.05f);

            //servering the loot table
            ServerLevel world = (ServerLevel) level;
            ResourceKey<LootTable> lootTableId = ResourceKey.create(
                    Registries.LOOT_TABLE,
                    ResourceLocation.parse("minecraft:chests/abandoned_mineshaft")
            );
            LootTable lootTable = world.getServer().reloadableRegistries().getLootTable(lootTableId);

            //build it up, chummerhead!
            LootParams.Builder lootBuilder = new LootParams.Builder(world)
                    .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                    .withLuck(player.getLuck())
                    .withOptionalParameter(LootContextParams.THIS_ENTITY, player);

            //spawn particles
            world.sendParticles(
                    ParticleTypes.CRIT,
                    pos.getX() + 0.5,
                    pos.getY() + 1.1,
                    pos.getZ() + 0.5,
                    10,
                    0.4, 0.3, 0.4,
                    0.2
            );


            //Generator
            List<ItemStack> loot = lootTable.getRandomItems(lootBuilder.create(LootContextParamSets.EMPTY));
            //Drop the loot above the Grindstone
            for (ItemStack stack : loot) {
                Block.popResource(world, pos.above(), stack);

                //hand swing effect
                player.swing(context.getHand(), true);
            }
        }
    }
        return super.useOn(context);
    }
}
