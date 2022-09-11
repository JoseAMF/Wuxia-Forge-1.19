package net.triplenold.wuxia.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;
import net.triplenold.wuxia.particle.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Player.class)
public class PlayerMixin {


    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        Player player = (Player)(Object)this;
        Random random = new Random();
        Vec3 playerPos = player.position();

    }
}
