package net.triplenold.wuxia.cultivation;

public enum CultivationTier {
    MORTAL(100,100, 1),
    FOUNDATION(500,80, 5),
    QI_CONDENSATION(1000,60, 10),
    CORE_FORMATION(2000,40, 20),
    NASCENT_SOUL(4000,20, 40),
    IMMORTAL(10000,0, 100);

    private final int maxQi;
    private final int breakthroughPercentage;
    private final int qiPerTick;


    CultivationTier(int maxQi, int breakthroughPercentage, int qiPerTick) {
        this.maxQi = maxQi;
        this.breakthroughPercentage = breakthroughPercentage;
        this.qiPerTick = qiPerTick;
    }

    public int getBreakthroughPercentage() {
        return breakthroughPercentage;
    }

    public int getMaxQi() {
        return maxQi;
    }

    public int getQiPerTick() {
        return qiPerTick;
    }
}
