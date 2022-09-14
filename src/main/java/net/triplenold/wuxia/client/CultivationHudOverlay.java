package net.triplenold.wuxia.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.cultivation.PlayerCultivation;

public class CultivationHudOverlay {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(WuxiaMod.MOD_ID,
            "textures/cultivation_hud/hud_bg.png");

    private static final ResourceLocation QI = new ResourceLocation(WuxiaMod.MOD_ID,
            "textures/cultivation_hud/hud_qi.png");

    public static final IGuiOverlay HUD_CULTIVATION = ((gui, poseStack, partialTick, width, height) -> {
       int x = width/2;
       int y = height;

       float Opacity = (CultivationPlayerData.getQi() + 0.0f) / (CultivationPlayerData.cultivationTier.getMaxQi() + 0.0f);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        GuiComponent.blit(poseStack, x-(x/2)-62, y-82, 0, 0,
                62, 82, 62, 82);

        RenderSystem.setShaderTexture(0, QI);
        RenderSystem.setShaderColor(1, 1, 0.5f, Opacity);

        GuiComponent.blit(poseStack, x-(x/2)-62, y-82, 0, 0,
                62, 82, 62, 82);
    });
}
