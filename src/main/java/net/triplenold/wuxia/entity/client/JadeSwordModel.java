package net.triplenold.wuxia.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JadeSwordModel extends AnimatedGeoModel<JadeSwordEntity> {
    @Override
    public ResourceLocation getModelResource(JadeSwordEntity object) {
        return new ResourceLocation(WuxiaMod.MOD_ID, "geo/jade_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JadeSwordEntity object) {
        return new ResourceLocation(WuxiaMod.MOD_ID, "textures/entity/jade_sword/flying_jade_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JadeSwordEntity animatable) {
        return new ResourceLocation(WuxiaMod.MOD_ID, "animations/jade_sword.animation.json");
    }
}
