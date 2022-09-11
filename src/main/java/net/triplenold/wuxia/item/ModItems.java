package net.triplenold.wuxia.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.item.custom.JadeSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WuxiaMod.MOD_ID);

    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> JADE_SWORD = ITEMS.register("jade_sword",
            () -> new JadeSwordItem(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
