package net.triplenold.wuxia.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WuxiaMod.MOD_ID);


    public static  final RegistryObject<SimpleParticleType> CULTIVATION_PARTICLES = PARTICLE_TYPES.register(
            "cultivation_particles", () -> new SimpleParticleType(true));

    public static void register (IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
