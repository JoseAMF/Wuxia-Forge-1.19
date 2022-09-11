package net.triplenold.wuxia.cultivation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

public class PlayerCultivation {
    private int qi;
    private Boolean isCultivating = false;
    private Vec3 cultivationPos = null;
    private final int MIN_QI = 0;
    private final int MAX_QI = 100;

    public int getQi() {
        return qi;
    }

    public void  addQi(int qiAmount) {
        qi = Math.max(Math.min(qi + qiAmount, MAX_QI), MIN_QI);
    }

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
}
