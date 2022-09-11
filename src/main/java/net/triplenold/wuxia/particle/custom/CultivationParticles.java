package net.triplenold.wuxia.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.triplenold.wuxia.WuxiaMod;

public class CultivationParticles extends TextureSheetParticle {
    protected CultivationParticles(ClientLevel clientLevel, double xCoord, double yCoord, double zCoord,
                                   SpriteSet spriteset, double xd, double yd, double zd) {
        super(clientLevel, xCoord, yCoord, zCoord);

        this.friction = 0.8F;
        this.xd = xd;
        this.yd= yd;
        this.zd = zd;


        this.quadSize *= 0.75F;
        this.lifetime = 40;
        this.setSpriteFromAge(spriteset);

        this.rCol = 1f;
        this.gCol =1f;
        this.bCol =1f;
    }

    @Override
    public void tick() {
        super.tick();

        this.fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new CultivationParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
