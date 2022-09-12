package net.triplenold.wuxia.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.triplenold.wuxia.client.CultivationPlayerData;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;
import net.triplenold.wuxia.item.custom.JadeSwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> {

    @Shadow
    public ModelPart rightArm;
    @Shadow public ModelPart leftArm;

    @Shadow public ModelPart rightLeg;
    @Shadow public ModelPart leftLeg;

    @Inject(method="setupAnim", at=@At("TAIL"))
    private void setupAnim(T player, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_, CallbackInfo info) {
        if(player instanceof Player) {
            (player).getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(playerCultivation -> {
                if(isSwordActive(player)) {
                    leftArm.xRot    = 0;
                    rightArm.xRot   = 0;
                    leftLeg.xRot    = 0;
                    rightLeg.xRot   = 0;

                    leftArm.zRot    = 0;
                    rightArm.zRot   = 0;
                    leftLeg.zRot    = 0;
                    rightLeg.zRot   = 0;

                    leftArm.yRot    = 0;
                    rightArm.yRot   = 0;
                    leftLeg.yRot    = 0;
                    rightLeg.yRot   = 0;
                } else if(CultivationPlayerData.getIsCulltivating()) {
                    this.rightArm.xRot += (-(float)Math.PI / 5F);
                    this.leftArm.xRot += (-(float)Math.PI / 5F);
                    this.rightLeg.xRot = -1.4137167F;
                    this.rightLeg.yRot = ((float)Math.PI / 10F);
                    this.rightLeg.zRot = 0.07853982F;
                    this.leftLeg.xRot = -1.4137167F;
                    this.leftLeg.yRot = (-(float)Math.PI / 10F);
                    this.leftLeg.zRot = -0.07853982F;

                }
            });
        }

    }

    private Boolean isSwordActive(T player) {
        for(ItemStack item : ((Player)player).getInventory().items) {
            if(item.getItem() instanceof JadeSwordItem) {
                if(((JadeSwordItem)item.getItem()).isActive()) return true;
            }
        }
        return false;
    }
}