package net.triplenold.wuxia.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_WUXIA = "key.category.wuxia.wuxia";
    public static final String KEY_CULTIVATION = "key.wuxia.cultivation";

    public static final KeyMapping CULTIVATION_KEY = new KeyMapping(KEY_CULTIVATION, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_WUXIA);
}
