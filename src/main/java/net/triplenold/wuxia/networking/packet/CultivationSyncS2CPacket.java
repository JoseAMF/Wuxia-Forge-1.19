package net.triplenold.wuxia.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.triplenold.wuxia.client.CultivationPlayerData;
import net.triplenold.wuxia.cultivation.CultivationTier;

import java.util.function.Supplier;

public class CultivationSyncS2CPacket {
    public final int qi;
    public final Boolean isCultivating;

    public final CultivationTier cultivationTier;

    public CultivationSyncS2CPacket(int qi, Boolean isCultivating, CultivationTier cultivationTier) {
        this.qi = qi;
        this.isCultivating = isCultivating;
        this.cultivationTier = cultivationTier;
    }

    public CultivationSyncS2CPacket(FriendlyByteBuf buf) {
        qi = buf.readInt();
        isCultivating = buf.readBoolean();
        cultivationTier = CultivationTier.values()[buf.readInt()];
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(qi);
        buf.writeBoolean(isCultivating);
        buf.writeInt(cultivationTier.ordinal());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            CultivationPlayerData.setData(qi, isCultivating, cultivationTier);
        });
        return true;
    }
}
