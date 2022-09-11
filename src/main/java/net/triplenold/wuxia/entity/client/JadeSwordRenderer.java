package net.triplenold.wuxia.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JadeSwordRenderer extends GeoEntityRenderer<JadeSwordEntity> {

    public JadeSwordRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new JadeSwordModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(JadeSwordEntity instance){
        return new ResourceLocation(WuxiaMod.MOD_ID, "textures/entity/jade_sword/jade_sword.png");
    }

    @Override
    public RenderType getRenderType(JadeSwordEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1f, 1f,1f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
