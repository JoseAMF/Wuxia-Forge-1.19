package net.triplenold.wuxia.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.entity.ModEntityTypes;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import net.triplenold.wuxia.entity.layer.PlayerJadeSwordLayer;
import net.triplenold.wuxia.networking.ModNetworking;
import net.triplenold.wuxia.networking.packet.CultivateC2SPacket;
import net.triplenold.wuxia.util.ICultivator;
import net.triplenold.wuxia.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = WuxiaMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.CULTIVATION_KEY.consumeClick()) {
                ModNetworking.sendToServer(new CultivateC2SPacket());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = WuxiaMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.CULTIVATION_KEY);
        }

        @SubscribeEvent
        public static void registerPlayerLayers(final EntityRenderersEvent.AddLayers event) {

            if (event.getSkin("default") instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new PlayerJadeSwordLayer<>(playerRenderer));
            }
            // add layer renders to slim model
            if (event.getSkin("slim") instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new PlayerJadeSwordLayer<>(playerRenderer));
            }


        }

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.TEST_MISSILE_PROJECTILE.get(),
                    ThrownItemRenderer::new);
        }

    }



}
