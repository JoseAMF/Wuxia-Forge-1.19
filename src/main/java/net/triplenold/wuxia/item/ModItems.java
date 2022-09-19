package net.triplenold.wuxia.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.item.custom.JadeSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WuxiaMod.MOD_ID);

    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> JADE_SWORD = ITEMS.register("jade_sword",
            () -> new JadeSwordItem(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).stacksTo(1)));

    public static final RegistryObject<Item> STELLAR_GRASS_SEEDS = ITEMS.register("stellar_grass_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STELLAR_GRASS_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> STELLAR_GRASS = ITEMS.register("stellar_grass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

    public static final RegistryObject<Item> SNOWY_GRASS_SEEDS = ITEMS.register("snowy_grass_seeds",
            () -> new ItemNameBlockItem(ModBlocks.SNOWY_GRASS_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> SNOWY_GRASS = ITEMS.register("snowy_grass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

    public static final RegistryObject<Item> SPIRITUAL_GRASS_SEEDS = ITEMS.register("spiritual_grass_seeds",
            () -> new ItemNameBlockItem(ModBlocks.SPIRITUAL_GRASS_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> SPIRITUAL_GRASS = ITEMS.register("spiritual_grass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
