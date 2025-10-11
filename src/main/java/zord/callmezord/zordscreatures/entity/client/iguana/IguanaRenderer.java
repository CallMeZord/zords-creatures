package zord.callmezord.zordscreatures.entity.client.iguana;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import zord.callmezord.zordscreatures.entity.entities.Iguana;


public class IguanaRenderer extends AgeableMobRenderer<Iguana, IguanaRenderState, IguanaModel> {

    public IguanaRenderer(EntityRendererProvider.Context p_251790_) {
        super(p_251790_, new IguanaModel(p_251790_.bakeLayer(ModelLayers.CAMEL)), new IguanaModel(p_251790_.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }

    @Override
    public IguanaRenderState createRenderState() {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(IguanaRenderState iguanaRenderState) {
        return null;
    }
}
