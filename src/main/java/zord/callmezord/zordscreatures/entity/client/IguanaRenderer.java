package zord.callmezord.zordscreatures.entity.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import zord.callmezord.zordscreatures.entity.entities.Iguana;

public class IguanaRenderer extends MobRenderer<Iguana, IguanaModel<Iguana>> {
public IguanaRenderer(EntityRendererProvider.Context context, IguanaModel<Iguana> model, float shadowRadius) {
    super(context, model, shadowRadius);


}

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return null;
    }

    @Override
    public EntityRenderState createRenderState() {
        return null;
    }
}
