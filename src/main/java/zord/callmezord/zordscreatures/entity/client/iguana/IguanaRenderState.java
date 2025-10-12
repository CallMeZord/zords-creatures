package zord.callmezord.zordscreatures.entity.client.iguana;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.ItemStack;


public class IguanaRenderState extends LivingEntityRenderState {

    public ItemStack stackInHand;
    public boolean isBaby;

    public IguanaRenderState() {
        super();
    }

}
