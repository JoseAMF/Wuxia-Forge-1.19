package net.triplenold.wuxia.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab WUXIA_TAB = new CreativeModeTab("wuxiatab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.JADE.get());
        }
    };
}
