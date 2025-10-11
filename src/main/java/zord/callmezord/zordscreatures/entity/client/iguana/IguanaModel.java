package zord.callmezord.zordscreatures.entity.client.iguana;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;



public class IguanaModel extends EntityModel<IguanaRenderState> {
 //   public static final MeshTransformer BABY_HEAD_TRANSFORMER = new BabyModelTransform(false, 4.0F, 4.0F, Set.of("head"));
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.45F);

//the parts, because why not
    protected final ModelPart everything;
    protected final ModelPart body;
    protected final ModelPart proto_head;


//register them or something, or was it baking them?
    public IguanaModel(ModelPart root) {
        super(root);

        ModelPart modelpart = root.getChild("everything");

        this.everything = root.getChild("everything");
        this.body = this.everything.getChild("body");
        this.proto_head = this.everything.getChild("proto_head");
    }

//do the layer gizmos
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition everything = partdefinition.addOrReplaceChild("everything", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

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

        PartDefinition Head = proto_head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1167F, -0.76F, -2.7667F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1667F, -0.35F, -0.0833F));

        PartDefinition eye_r1 = Head.addOrReplaceChild("eye_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.04F, -0.46F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1567F, -0.2F, -1.7667F, 0.0F, 0.0F, -3.1416F));

        PartDefinition h1 = Head.addOrReplaceChild("h1", CubeListBuilder.create().texOffs(30, 30).addBox(-1.0F, -7.0F, -5.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(-0.5F, -5.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1667F, 5.0F, -0.9167F));

        PartDefinition top_hair = Head.addOrReplaceChild("top_hair", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, -2.0F, -2.4167F));

        PartDefinition NeckBeard = Head.addOrReplaceChild("NeckBeard", CubeListBuilder.create().texOffs(10, 53).addBox(0.0F, -0.25F, -3.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, 1.25F, -1.9167F));

        PartDefinition male_neck = Head.addOrReplaceChild("male_neck", CubeListBuilder.create().texOffs(36, 40).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1667F, 1.6F, -1.5167F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

}
