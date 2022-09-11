package net.triplenold.wuxia.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.triplenold.wuxia.item.custom.JadeSwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(method = "setupRotations", at = @At(value = "HEAD"), cancellable = true)
    private void setupRotationsInjection(AbstractClientPlayer player, PoseStack p_117803_, float p_117804_, float p_117805_, float p_117806_, CallbackInfo info) {
        if(isSwordActive(player)) {
        }
    }


    private Boolean isSwordActive(AbstractClientPlayer player) {
        for(ItemStack item : player.getInventory().items) {
            if(item.getItem() instanceof JadeSwordItem) {
                if(((JadeSwordItem)item.getItem()).isActive()) return true;
            }
        }
        return false;
    }
}
