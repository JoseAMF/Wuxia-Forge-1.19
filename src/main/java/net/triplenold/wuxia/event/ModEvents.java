package net.triplenold.wuxia.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.cultivation.PlayerCultivation;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;
import net.triplenold.wuxia.networking.ModNetworking;
import net.triplenold.wuxia.networking.packet.CultivationSyncS2CPacket;
import net.triplenold.wuxia.particle.ModParticles;

import java.util.Random;

@Mod.EventBusSubscriber(modid = WuxiaMod.MOD_ID)
public class ModEvents {

     @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
         if(event.getObject() instanceof Player) {
             if(!event.getObject().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).isPresent()) {
                 event.addCapability(new ResourceLocation(WuxiaMod.MOD_ID, "properties"), new PlayerCultivationProvider());
             }
         }
     }

     @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
         if(event.isWasDeath()) {
             event.getOriginal().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(oldStore -> {
                 event.getOriginal().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(newStore -> {
                     newStore.copyFrom(oldStore);
                 });
             });
         }
     }

     @SubscribeEvent
     public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
         if(event.side == LogicalSide.SERVER) {
             event.player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(playerCultivation -> {
                 PlayerCultivation.handlePlayerTick(event, playerCultivation);
                 ModNetworking.sendToPlayer(new CultivationSyncS2CPacket(playerCultivation.getQi(), playerCultivation.isCultivating()), ((ServerPlayer) event.player));
             });
        }
     }
}
