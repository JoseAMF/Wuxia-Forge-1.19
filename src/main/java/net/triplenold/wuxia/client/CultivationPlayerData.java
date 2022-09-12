package net.triplenold.wuxia.client;

public class CultivationPlayerData {
    private static int qi;
    private static Boolean isCulltivating;

    public static int getQi() {
        return qi;
    }

    public static void setQi(int qi) {
        CultivationPlayerData.qi = qi;
    }

    public static Boolean getIsCulltivating() {
        return isCulltivating;
    }

    public static void setIsCulltivating(Boolean isCulltivating) {
        CultivationPlayerData.isCulltivating = isCulltivating;
    }

    public static void setData(int qi, Boolean isCulltivating) {
        CultivationPlayerData.qi = qi;
        CultivationPlayerData.isCulltivating = isCulltivating;
    }
}
