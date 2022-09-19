package net.triplenold.wuxia.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import net.triplenold.wuxia.entity.custom.TestMissileProjectileEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WuxiaMod.MOD_ID);

    public static final RegistryObject<EntityType<JadeSwordEntity>> JADE_SWORD = ENTITY_TYPES.register("jade_sword",
            () -> EntityType.Builder.of(JadeSwordEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f)
            .build(new ResourceLocation(WuxiaMod.MOD_ID, "jade_sword").toString()));

    public static final RegistryObject<EntityType<TestMissileProjectileEntity>> TEST_MISSILE_PROJECTILE = ENTITY_TYPES.register(
            "test_missile_projectile",
            () -> EntityType.Builder.<TestMissileProjectileEntity>of(TestMissileProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .build(new ResourceLocation(WuxiaMod.MOD_ID, "test_missile_projectile").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
