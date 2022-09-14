package net.triplenold.wuxia.event;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.client.CultivationHudOverlay;
import net.triplenold.wuxia.entity.ModEntityTypes;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import net.triplenold.wuxia.particle.ModParticles;
import net.triplenold.wuxia.particle.custom.CultivationParticles;

@Mod.EventBusSubscriber(modid = WuxiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.JADE_SWORD.get(), JadeSwordEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.register(ModParticles.CULTIVATION_PARTICLES.get(),
                CultivationParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("cultivation", CultivationHudOverlay.HUD_CULTIVATION);
    }
}
