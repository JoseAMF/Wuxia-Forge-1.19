package net.triplenold.wuxia.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.networking.packet.CultivateC2SPacket;
import net.triplenold.wuxia.networking.packet.CultivationSyncS2CPacket;
import net.triplenold.wuxia.networking.packet.SetPlayerFlightC2SPacket;

public class ModNetworking {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(WuxiaMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(CultivateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CultivateC2SPacket::new)
                .encoder(CultivateC2SPacket::toBytes)
                .consumerMainThread(CultivateC2SPacket::handle)
                .add();

        net.messageBuilder(SetPlayerFlightC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetPlayerFlightC2SPacket::new)
                .encoder(SetPlayerFlightC2SPacket::toBytes)
                .consumerMainThread(SetPlayerFlightC2SPacket::handle)
                .add();

        net.messageBuilder(CultivationSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CultivationSyncS2CPacket::new)
                .encoder(CultivationSyncS2CPacket::toBytes)
                .consumerMainThread(CultivationSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
