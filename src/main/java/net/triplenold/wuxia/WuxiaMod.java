package net.triplenold.wuxia;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.entity.ModEntityTypes;
import net.triplenold.wuxia.entity.client.JadeSwordRenderer;
import net.triplenold.wuxia.entity.custom.JadeSwordEntity;
import net.triplenold.wuxia.item.ModItems;
import net.triplenold.wuxia.networking.ModNetworking;
import net.triplenold.wuxia.particle.ModParticles;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WuxiaMod.MOD_ID)
public class WuxiaMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "wuxia";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public WuxiaMod()
    {
        MixinBootstrap.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModParticles.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ModEntityTypes.register(modEventBus);
        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("removal")
    private void clientSetup(final FMLClientSetupEvent event) {
        //BLOCKS
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.JADE_SWORD_BLOCK.get(), RenderType.cutout());

        //CROPS
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ROSELLE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STAR_GRASS_CROP.get(), RenderType.cutout());

        //ENTITIES
        EntityRenderers.register(ModEntityTypes.JADE_SWORD.get(), JadeSwordRenderer::new);

    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModNetworking.register();
        });
    }

}
