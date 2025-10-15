package zord.callmezord.zordscreatures.entity.client.iguana;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.entity.entities.Iguana;


public class IguanaRenderer extends MobRenderer<Iguana, IguanaRenderState, IguanaModel> {

    private static final ResourceLocation MALE_TEXTURE =
             ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "textures/entity/iguana/iguana_male.png");
    private static final ResourceLocation FEMALE_TEXTURE =
             ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "textures/entity/iguana/iguana_female.png");

    public IguanaRenderer(EntityRendererProvider.Context context) {
        super(context, new IguanaModel(context.bakeLayer(IguanaModel.LAYER_LOCATION)), 0.45F);
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull IguanaRenderState iguanaRenderState) {

        return iguanaRenderState.isMale ? MALE_TEXTURE : FEMALE_TEXTURE;
    }


    //create a render state
    @Override
    public @NotNull IguanaRenderState createRenderState() {
        return new IguanaRenderState();
    }


    //update the render state every tick
    @Override
    public void extractRenderState(Iguana entity,IguanaRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.ageInTicks = entity.tickCount + partialTicks;
        //EXTRACTS THE BABY STATE
        state.isBaby = entity.isBaby();

        //GET GENDER
        state.isMale = entity.isMale();

        state.isBasking = entity.isBasking();

        //TICKLERS ANIMATORS!
        state.baskingAnimationState.copyFrom(entity.baskingAnimationState);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);

    }
}
