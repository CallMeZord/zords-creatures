package zord.callmezord.zordscreatures.entity.client.iguana;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.ItemStack;


public class IguanaRenderState extends LivingEntityRenderState {

    public ItemStack stackInHand;
    public boolean isBaby;
    public boolean isMale;
    public boolean isBasking;
    public boolean isAlbino;

//ANIMATIONS
public final AnimationState idleAnimationState = new AnimationState();
public final AnimationState baskingAnimationState = new AnimationState();
public final AnimationState headShakeAnimationState = new AnimationState();


    public IguanaRenderState() {
        super();
    }

}
