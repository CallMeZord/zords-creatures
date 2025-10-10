package zord.callmezord.zordscreatures.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.item.ItemsGeneral;

public class BlocksGeneral {
    // Create a single deferred register for all blocks
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZordsCreatures.MODID);


    //BLOCKS GO HERE TO LIVE!
    public static final DeferredBlock<Block> TEST_BLOCK = registerBlock("test_block");





    private static DeferredBlock<Block> registerBlock(String name) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.of());

        registerBlockItem(name, deferredBlock);

        return deferredBlock;
    }

    private static void registerBlockItem(String name, DeferredBlock<Block> block) {
        ItemsGeneral.ITEMS.registerSimpleBlockItem(name, block, new Item.Properties());
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
