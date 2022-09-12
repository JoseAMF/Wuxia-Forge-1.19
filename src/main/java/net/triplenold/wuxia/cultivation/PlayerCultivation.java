package net.triplenold.wuxia.cultivation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import net.triplenold.wuxia.particle.ModParticles;

import java.util.Random;

public class PlayerCultivation {
    private int qi;
    private Boolean isCultivating = false;
    private Vec3 cultivationPos = null;
    private final int MIN_QI = 0;
    private final int MAX_QI = 1000;

    public int getQi() {
        return qi;
    }

    public void  addQi(int qiAmount) {
        qi = Math.max(Math.min(qi + qiAmount, MAX_QI), MIN_QI);
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

    public Boolean isCultivating() {
        return isCultivating;
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
    }

    public void loadNBTData(CompoundTag nbt) {
        qi = nbt.getInt("qi");
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

        if(playerCultivation.isCultivating() && !(!player.isOnGround() || player.isSleeping() ||
                player.isFallFlying() || player.isSwimming() || player.isCrouching() || playerCultivation.hasMoved(playerPos))) {

            playerCultivation.secondsCultivating++;
//            Cultivate each second rather than each tick
            if(playerCultivation.secondsCultivating % 40 == 0) {
                playerCultivation.secondsCultivating = 0;
                playerCultivation.addQi(1);
                player.sendSystemMessage(Component.literal("Qi currently at: " + playerCultivation.getQi()));
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
