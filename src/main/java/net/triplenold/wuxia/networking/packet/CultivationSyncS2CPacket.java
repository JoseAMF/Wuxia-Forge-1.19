package net.triplenold.wuxia.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.triplenold.wuxia.client.CultivationPlayerData;

import java.util.function.Supplier;

public class CultivationSyncS2CPacket {
    public final int qi;
    public final Boolean isCultivating;


    public CultivationSyncS2CPacket(int qi, Boolean isCultivating) {
        this.qi = qi;
        this.isCultivating = isCultivating;
    }

    public CultivationSyncS2CPacket(FriendlyByteBuf buf) {
        qi = buf.readInt();
        isCultivating = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(qi);
        buf.writeBoolean(isCultivating);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            CultivationPlayerData.setData(qi, isCultivating);
        });
        return true;
    }
}
