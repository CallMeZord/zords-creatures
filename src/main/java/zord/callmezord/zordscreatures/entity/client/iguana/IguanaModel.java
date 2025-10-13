package zord.callmezord.zordscreatures.entity.client.iguana;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.entity.animation.json.AnimationHolder;
import zord.callmezord.zordscreatures.ZordsCreatures;


public class IguanaModel extends EntityModel<IguanaRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "iguana"), "main");

    //ANIMATION HOLDER
    public static final AnimationHolder IGUANA_IDLE =
            Model.getAnimation(ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "iguana/iguana_idle"));
    public static final AnimationHolder IGUANA_WALK =
            Model.getAnimation(ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "iguana/iguana_walk"));
    public static final AnimationHolder IGUANA_BASKING =
            Model.getAnimation(ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "iguana/iguana_sunbathing"));
    public static final AnimationHolder IGUANA_HEAD_SHAKE =
            Model.getAnimation(ResourceLocation.fromNamespaceAndPath(ZordsCreatures.MODID, "iguana/iguana_head_shaker"));


     //the parts, because why not + ANIM storage
private final ModelPart everything;
    private final ModelPart body;
    private final ModelPart proto_head;
    private final ModelPart head;
    private final KeyframeAnimation idle;
    private final KeyframeAnimation walk;
    private final KeyframeAnimation basking;
    private final KeyframeAnimation head_shake;


    //register them or something, or was it baking them?
    public IguanaModel(ModelPart root) {
        super(root);

        ModelPart modelpart = root.getChild("everything");

        this.everything = root.getChild("everything");
        this.body = everything;
        this.proto_head = this.everything.getChild("proto_head");
        this.head = this.proto_head.getChild("Head");

        //ANIMATION BAKING
        this.idle = IGUANA_IDLE.get().bake(root);
        this.walk = IGUANA_WALK.get().bake(root);
        this.basking = IGUANA_BASKING.get().bake(root);
        this.head_shake = IGUANA_HEAD_SHAKE.get().bake(root);
    }


    //do the layer gizmos
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root1 = meshdefinition.getRoot();

        PartDefinition everything = root1.addOrReplaceChild("everything", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

        PartDefinition left_f = everything.addOrReplaceChild("left_f", CubeListBuilder.create(), PartPose.offset(3.25F, 1.45F, -4.0F));

        PartDefinition left1 = left_f.addOrReplaceChild("left1", CubeListBuilder.create().texOffs(50, 31).addBox(3.0F, -7.9F, -3.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 50).addBox(2.0F, -4.0F, -5.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.25F, 7.45F, 2.0F));

        PartDefinition right_f = everything.addOrReplaceChild("right_f", CubeListBuilder.create(), PartPose.offset(-3.25F, 1.45F, -4.0F));

        PartDefinition right1 = right_f.addOrReplaceChild("right1", CubeListBuilder.create().texOffs(50, 24).addBox(2.0F, -8.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 35).addBox(2.0F, -4.1F, -6.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.75F, 7.55F, 3.0F));

        PartDefinition right_b = everything.addOrReplaceChild("right_b", CubeListBuilder.create(), PartPose.offset(-3.25F, 1.45F, 4.5F));

        PartDefinition right3 = right_b.addOrReplaceChild("right3", CubeListBuilder.create().texOffs(23, 50).addBox(0.5F, -4.1F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(46, 46).addBox(2.0F, -8.0F, -5.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.75F, 7.55F, 3.5F));

        PartDefinition left_b = everything.addOrReplaceChild("left_b", CubeListBuilder.create(), PartPose.offset(3.25F, 1.45F, 4.5F));

        PartDefinition left3 = left_b.addOrReplaceChild("left3", CubeListBuilder.create().texOffs(49, 19).addBox(1.5F, -4.1F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 46).addBox(2.0F, -8.0F, -5.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 7.55F, 3.5F));

        PartDefinition body = everything.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.25F, -1.0F, 0.75F));

        PartDefinition b1 = body.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -9.0F, -6.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 7.0F, -0.75F));

        PartDefinition tail_seg_1 = body.addOrReplaceChild("tail_seg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 6.25F));

        PartDefinition t1 = tail_seg_1.addOrReplaceChild("t1", CubeListBuilder.create().texOffs(30, 19).addBox(-1.0F, -7.0F, -4.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, 5.0F, 4.0F));

        PartDefinition tail_seg_2 = tail_seg_1.addOrReplaceChild("tail_seg_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.25F, 7.0F));

        PartDefinition t3 = tail_seg_2.addOrReplaceChild("t3", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, -6.0F, -4.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 4.75F, 4.0F));

        PartDefinition seg2_hair = tail_seg_2.addOrReplaceChild("seg2_hair", CubeListBuilder.create().texOffs(7, 26).addBox(0.0F, -2.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -1.25F, 4.0F));

        PartDefinition tail_seg_3 = tail_seg_2.addOrReplaceChild("tail_seg_3", CubeListBuilder.create().texOffs(10, 29).addBox(0.25F, -3.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.75F, 7.0F));

        PartDefinition t5 = tail_seg_3.addOrReplaceChild("t5", CubeListBuilder.create().texOffs(48, 55).addBox(1.0F, -5.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 4.0F, 4.0F));

        PartDefinition seg1_hair = tail_seg_1.addOrReplaceChild("seg1_hair", CubeListBuilder.create().texOffs(7, 26).addBox(0.0F, -2.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -2.0F, 4.0F));

        PartDefinition hair_torso = body.addOrReplaceChild("hair_torso", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.0F, -7.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -2.0F, 0.25F));

        PartDefinition proto_head = everything.addOrReplaceChild("proto_head", CubeListBuilder.create(), PartPose.offset(0.0F, -0.75F, -6.0F));

        PartDefinition head = proto_head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1167F, -0.76F, -2.7667F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1667F, -0.35F, -0.0833F));

        PartDefinition eye_r1 = head.addOrReplaceChild("eye_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.04F, -0.46F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1567F, -0.2F, -1.7667F, 0.0F, 0.0F, -3.1416F));

        PartDefinition h1 = head.addOrReplaceChild("h1", CubeListBuilder.create().texOffs(30, 30).addBox(-1.0F, -7.0F, -5.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(-0.5F, -5.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1667F, 5.0F, -0.9167F));

        PartDefinition top_hair = head.addOrReplaceChild("top_hair", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, -2.0F, -2.4167F));

        PartDefinition NeckBeard = head.addOrReplaceChild("NeckBeard", CubeListBuilder.create().texOffs(10, 53).addBox(0.0F, -0.25F, -3.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, 1.25F, -1.9167F));

        PartDefinition male_neck = head.addOrReplaceChild("male_neck", CubeListBuilder.create().texOffs(36, 40).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, 1.6F, -1.5167F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }



    //EXTRA VALUES
    private boolean wasBaby = false;



    //GENERAL MODEL CHANGES & MAIN ANIM
    @Override
    public void setupAnim(IguanaRenderState renderState) {

        this.root().getAllParts().forEach(ModelPart::resetPose);

        //ANIMATIONS

        this.walk.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 4, 3);
        this.idle.apply(renderState.idleAnimationState, renderState.ageInTicks);
        //

        this.applyHeadRotation(renderState, renderState.yRot, renderState.xRot);
        applyBabyScaling(renderState);
    }






    //HEAD ROTATION
    private void applyHeadRotation(IguanaRenderState state, float yRot, float xRot) {
        float headYaw = Mth.clamp(state.yRot, -30.0F, 30.0F);
        float headPitch = Mth.clamp(state.xRot, -25.0F, 45.0F);

        this.head.yRot += headYaw * ((float) Math.PI / 180F);
        this.head.xRot += headPitch * ((float) Math.PI / 180F);
    }



    //BABY SCALING
    private void applyBabyScaling(IguanaRenderState state) {
        if (state.isBaby != wasBaby) {
            if (state.isBaby) {
                this.proto_head.xScale = 1.6F;
                this.proto_head.yScale = 1.6F;
                this.proto_head.zScale = 1.6F;

                this.everything.xScale = 0.4F;
                this.everything.yScale = 0.4F;
                this.everything.zScale = 0.4F;
                this.everything.y = 21.5F;
            } else {
                this.proto_head.xScale = 1F;
                this.proto_head.yScale = 1F;
                this.proto_head.zScale = 1F;

                this.everything.xScale = 1F;
                this.everything.yScale = 1F;
                this.everything.zScale = 1F;
                this.everything.y = 18.0F;
            }
            wasBaby = state.isBaby;
        }
    }
}
