// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class loong_boat<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "loong_boat"), "main");
	private final ModelPart front;
	private final ModelPart back;
	private final ModelPart left;
	private final ModelPart right;
	private final ModelPart bottom;
	private final ModelPart bottom_no_water;
	private final ModelPart paddle_left;
	private final ModelPart paddle_left2;
	private final ModelPart paddle_left3;
	private final ModelPart paddle_right;
	private final ModelPart paddle_right2;
	private final ModelPart paddle_right3;

	public loong_boat(ModelPart root) {
		this.front = root.getChild("front");
		this.back = root.getChild("back");
		this.left = root.getChild("left");
		this.right = root.getChild("right");
		this.bottom = root.getChild("bottom");
		this.bottom_no_water = root.getChild("bottom_no_water");
		this.paddle_left = root.getChild("paddle_left");
		this.paddle_left2 = root.getChild("paddle_left2");
		this.paddle_left3 = root.getChild("paddle_left3");
		this.paddle_right = root.getChild("paddle_right");
		this.paddle_right2 = root.getChild("paddle_right2");
		this.paddle_right3 = root.getChild("paddle_right3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 18.0F, -23.0F, 0.0F, -3.1416F, 0.0F));

		PartDefinition cube_r1 = front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(94, 0).mirror().addBox(-6.5F, -1.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(94, 0).addBox(2.5F, -1.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 11.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(88, 23).addBox(-5.0F, -3.0F, 1.0F, 9.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -14.0F, 11.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(110, 0).addBox(-2.5F, -5.0F, 9.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 9).addBox(-4.0F, -4.0F, 2.0F, 8.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 11.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r4 = front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(75, 113).mirror().addBox(2.5F, -3.0F, 0.0F, 13.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.0F, 11.0F, 0.2467F, 0.0886F, -0.3381F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(75, 113).addBox(-15.5F, -3.0F, 0.0F, 13.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 11.0F, 0.2467F, -0.0886F, 0.3381F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(102, 86).mirror().addBox(4.0F, -7.0F, 4.0F, 13.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -19.0F, 12.0F, 0.6109F, 1.1026F, 0.5585F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(102, 86).addBox(-17.0F, -7.0F, 4.0F, 13.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 12.0F, 0.6109F, -1.1026F, -0.5585F));

		PartDefinition cube_r8 = front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(104, 116).mirror().addBox(2.0F, -1.5F, -16.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, -0.7024F, -0.1002F, 0.0844F));

		PartDefinition cube_r9 = front.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(109, 120).mirror().addBox(-3.0F, -1.0F, -16.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, -0.8499F, -0.544F, 0.5323F));

		PartDefinition cube_r10 = front.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(109, 120).addBox(2.0F, -1.0F, -16.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, -0.8499F, 0.544F, -0.5323F));

		PartDefinition cube_r11 = front.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(104, 116).addBox(-4.0F, -1.5F, -16.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, -0.7024F, 0.1002F, -0.0844F));

		PartDefinition cube_r12 = front.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(84, 101).addBox(-8.0F, -7.5F, -8.0F, 16.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r13 = front.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(80, 37).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 6.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r14 = front.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 29).addBox(-5.0F, -10.0F, -6.0F, 10.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 4.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition front_r1 = front.addOrReplaceChild("front_r1", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -5.0F, -5.0F, 16.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(4.0F, 22.0F, 15.0F));

		PartDefinition cube_r15 = back.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(92, 56).addBox(0.0F, -5.5F, 1.0F, 0.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(40, 67).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -13.0F, 13.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r16 = back.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(92, 59).addBox(-5.0F, -4.5F, -3.0F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 11.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition back_r1 = back.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(30, 49).addBox(-8.0F, -6.0F, -5.0F, 16.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, 7.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 19).addBox(-22.0F, -7.0F, -1.0F, 44.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 22.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-22.0F, -7.0F, -1.0F, 44.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.0F, 22.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -9.0F, -3.0F, 44.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 21.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition bottom_no_water = partdefinition.addOrReplaceChild("bottom_no_water", CubeListBuilder.create().texOffs(0, 106).addBox(-18.0F, -8.0F, -3.0F, 37.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition paddle_left = partdefinition.addOrReplaceChild("paddle_left", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 13.0F, -3.0F, 2.1642F, 0.8727F, 2.8798F));

		PartDefinition paddle_left2 = partdefinition.addOrReplaceChild("paddle_left2", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 13.0F, 7.0F, 2.1642F, 0.8727F, 2.8798F));

		PartDefinition paddle_left3 = partdefinition.addOrReplaceChild("paddle_left3", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 13.0F, -13.0F, 2.1642F, 0.8727F, 2.8798F));

		PartDefinition paddle_right = partdefinition.addOrReplaceChild("paddle_right", CubeListBuilder.create().texOffs(0, 84).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 84).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 13.0F, -3.0F, 2.1642F, -0.8727F, -2.8798F));

		PartDefinition paddle_right2 = partdefinition.addOrReplaceChild("paddle_right2", CubeListBuilder.create().texOffs(0, 84).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 84).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 13.0F, 7.0F, 2.1642F, -0.8727F, -2.8798F));

		PartDefinition paddle_right3 = partdefinition.addOrReplaceChild("paddle_right3", CubeListBuilder.create().texOffs(0, 84).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 84).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 13.0F, -13.0F, 2.1642F, -0.8727F, -2.8798F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bottom_no_water.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_left2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_left3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_right2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		paddle_right3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}