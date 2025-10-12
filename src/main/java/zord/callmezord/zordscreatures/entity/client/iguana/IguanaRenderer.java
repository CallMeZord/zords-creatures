package zord.callmezord.zordscreatures.entity.client.iguana;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zord.callmezord.zordscreatures.ZordsCreatures;
import zord.callmezord.zordscreatures.entity.entities.Iguana;


public class IguanaRenderer extends MobRenderer<Iguana, IguanaRenderState, IguanaModel> {


    public IguanaRenderer(EntityRendererProvider.Context context) {
        super(context, new IguanaModel(context.bakeLayer(IguanaModel.LAYER_LOCATION)), 0.5F);
    }



    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull IguanaRenderState iguanaRenderState) {
        return ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "textures/entity/iguana/iguana_male.png");

    }





    //create a render state
    @Override
    public @NotNull IguanaRenderState createRenderState() {
        return new IguanaRenderState();
    }


    //update the render state every tick
    @Override
    public void extractRenderState(@NotNull Iguana entity, @NotNull IguanaRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        //EXTRACTS THE BABY STATE
        state.isBaby = entity.isBaby();
    }
}
