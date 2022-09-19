package net.triplenold.wuxia.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.item.custom.JadeSwordItem;
import net.triplenold.wuxia.item.custom.Neutral_1_MagicMissile_Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WuxiaMod.MOD_ID);

    //GENERAL ITEMS
    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));


    //CUSTOM ITEMS
    public static final RegistryObject<Item> FLYING_JADE_SWORD = ITEMS.register("flying_jade_sword",
            () -> new JadeSwordItem(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).stacksTo(1)));

    //TOOLS - new TypeItem(ItemTier, AttackModdifier, SpeedModifier, ItemProperties)
    public static final RegistryObject<Item> JADE_SWORD = ITEMS.register("jade_sword",
            () -> new SwordItem(ModTiers.JADE, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));
    public static final RegistryObject<Item> JADE_AXE = ITEMS.register("jade_axe",
            () -> new AxeItem(ModTiers.JADE, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));
    public static final RegistryObject<Item> JADE_PICKAXE = ITEMS.register("jade_pickaxe",
            () -> new PickaxeItem(ModTiers.JADE, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));
    public static final RegistryObject<Item> JADE_HOE = ITEMS.register("jade_hoe",
            () -> new HoeItem(ModTiers.JADE, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));
    public static final RegistryObject<Item> JADE_SHOVEL = ITEMS.register("jade_shovel",
            () -> new ShovelItem(ModTiers.JADE, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    //Crops
    public static final RegistryObject<Item> ROSELLE_SEEDS = ITEMS.register("roselle_seeds",
            () -> new ItemNameBlockItem(ModBlocks.ROSELLE_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> ROSELLE = ITEMS.register("roselle",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).food(new FoodProperties.Builder()
                    .nutrition(2).saturationMod(2f).build())));
    public static final RegistryObject<Item> STAR_GRASS_SEEDS = ITEMS.register("star_grass_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STAR_GRASS_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));

    public static final RegistryObject<Item> STAR_GRASS = ITEMS.register("star_grass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB)));


    //PROJECTILES
    public static final RegistryObject<Item> TEST_MISSILE_PROJECTILE = ITEMS.register("test_projectile",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).stacksTo(1)));

    public static final RegistryObject<Item> TEST_MISSILE_PROJECTILE2 = ITEMS.register("test_projectile2",
            () -> new Neutral_1_MagicMissile_Item(new Item.Properties().tab(ModCreativeModeTab.WUXIA_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
