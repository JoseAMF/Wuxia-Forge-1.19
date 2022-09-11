package net.triplenold.wuxia.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;

import java.util.function.Supplier;

public class CultivateC2SPacket {
    public CultivateC2SPacket() {

    }

    public CultivateC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(playerCultivation -> playerCultivation.setCultivating(player.position()));
        });

        return true;
    }
}
