package net.triplenold.wuxia.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;

import java.util.function.Supplier;

public class SetPlayerFlightC2SPacket {
    private final Boolean value;
    public SetPlayerFlightC2SPacket(Boolean value) {

        this.value = value;
    }

    public SetPlayerFlightC2SPacket(FriendlyByteBuf buf) {
        this.value = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(value);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if(player.isCreative()) return;
            player.getAbilities().mayfly = value;
            player.getAbilities().flying = value;

            player.onUpdateAbilities();
        });

        return true;
    }
}
