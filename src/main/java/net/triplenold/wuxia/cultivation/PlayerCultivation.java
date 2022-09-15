package net.triplenold.wuxia.cultivation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.particle.ModParticles;

import java.util.Random;

public class PlayerCultivation {
    private int qi;
    private Boolean isCultivating = false;
    private Vec3 cultivationPos = null;
    private float cultivationTierPercentage = 0;

    private CultivationTier cultivationTier = CultivationTier.MORTAL;

    public int getQi() {
        return qi;
    }

    public void  addQi(int qiAmount) {
        qi = Math.max(Math.min(qi + qiAmount, cultivationTier.getMaxQi()), 0);
    }
    public int secondsCultivating = 0;

    public void setCultivating(Vec3 pos) {
        isCultivating = true;
        cultivationPos = pos;
    }

    public void setCultivating() {
       isCultivating = false;
       cultivationPos = null;
    }

    public CultivationTier getCultivationTier() {
        return cultivationTier;
    }

    public void setCultivationTier(CultivationTier cultivationTier) {
        this.cultivationTier = cultivationTier;
    }

    public Boolean isCultivating() {
        return isCultivating;
    }

    public float getCultivationTierPercentage() {
        return cultivationTierPercentage;
    }

    public void addCultivationTierPercentage(float cultivationTierPercentage) {
        this.cultivationTierPercentage += cultivationTierPercentage;
    }

    public Boolean hasMoved(Vec3 pos) {
        try {
            return cultivationPos.distanceTo(pos) != 0;
        } catch (Exception e){
            return true;
        }
    }

    public void copyFrom(PlayerCultivation source) {
        this.qi = source.qi;
        this.isCultivating = source.isCultivating;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("qi", qi);
        nbt.putString("cultivationTier", cultivationTier.name());
    }

    public void loadNBTData(CompoundTag nbt) {
        qi = nbt.getInt("qi");
        cultivationTier = CultivationTier.valueOf(nbt.getString("cultivationTier"));
    }
    public void setData(Boolean isCultivating, int qi) {
        this.qi = qi;
        this.isCultivating = isCultivating;
    }

    public static void handlePlayerTick(TickEvent.PlayerTickEvent event, PlayerCultivation playerCultivation) {

        Player player = event.player;
        Vec3 playerPos = player.position();
        Level world = player.getCommandSenderWorld();
        Random random = new Random();

        if((player.tickCount % 20) == 0) {
            playerCultivation.addQi(1);
            WuxiaMod.LOGGER.debug("this should be per second");
        }

        if(playerCultivation.isCultivating() && !(!player.isOnGround() || player.isSleeping() ||
                player.isFallFlying() || player.isSwimming() || player.isCrouching() || playerCultivation.hasMoved(playerPos))) {

//            Cultivate each second rather than each tick
            if(player.tickCount % 20 == 0) {
                if(playerCultivation.getQi() > playerCultivation.getCultivationTier().getQiPerTick()) {
                    playerCultivation.addQi(-playerCultivation.getCultivationTier().getQiPerTick());
                    playerCultivation.addCultivationTierPercentage(0.1f);
                }
            }

//            Particle
            if(random.nextFloat() > 0.85f) {
                ((ServerLevel)player.getLevel()).sendParticles(ModParticles.CULTIVATION_PARTICLES.get(),
                        playerPos.x, playerPos.y+0.5, playerPos.z, 1, 0,
                        random.nextDouble(0.35d), random.nextDouble(0.35d), random.nextDouble(0.35d));
            }

        }         else if(playerCultivation.isCultivating()) {
            playerCultivation.setCultivating();
        }

    }
}
