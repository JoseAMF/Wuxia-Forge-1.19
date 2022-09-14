package net.triplenold.wuxia.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.ModelData;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.item.custom.JadeSwordItem;

public class PlayerJadeSwordLayer <T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    public PlayerJadeSwordLayer(RenderLayerParent<T, PlayerModel<T>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, T player,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
        if(!player.isInvisible() && isSwordActive(player)) {
            final float scale = 2F;
            poseStack.pushPose();


            this.getParentModel().body.translateAndRotate(poseStack);
            poseStack.translate(0.46875D * scale, 1.53125, -0.5D * scale);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            poseStack.scale(scale, -scale, -scale);
            // render fire here
            // note: packed light flag 15728640 uses world light, 15728880 uses constant/full light
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ModBlocks.JADE_SWORD_BLOCK.get().defaultBlockState(),
                    poseStack, multiBufferSource, 15728880, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
            // finish rendering
            poseStack.popPose();
        }
    }


    private Boolean isSwordActive(T player) {
        for(ItemStack item : player.getInventory().items) {
            if(item.getItem() instanceof JadeSwordItem) {
                if(((JadeSwordItem)item.getItem()).isActive()) return true;
            }
        }
        return false;
    }
}
