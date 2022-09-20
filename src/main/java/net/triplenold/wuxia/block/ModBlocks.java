package net.triplenold.wuxia.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.block.custom.JadeSwordBlock;
import net.triplenold.wuxia.block.custom.SnowyGrassCropBlock;
import net.triplenold.wuxia.block.custom.SpiritualGrassCropBlock;
import net.triplenold.wuxia.block.custom.StarGrassCropBlock;
import net.triplenold.wuxia.item.ModCreativeModeTab;
import net.triplenold.wuxia.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WuxiaMod.MOD_ID);


    //BLOCKS
    public static final RegistryObject<Block> JADE_BLOCK = registerBlock("jade_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.WUXIA_TAB);

    //FLOWER-BLOCK
    public static final RegistryObject<Block> GINGER_PLANT = registerBlock("ginger_plant",
            () -> new FlowerBlock(MobEffects.LEVITATION, 8,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion()), ModCreativeModeTab.WUXIA_TAB);

    public static final RegistryObject<Block> POTTED_GINGER_PLANT = BLOCKS.register("potted_ginger_plant",
            () -> new FlowerPotBlock(null, ModBlocks.GINGER_PLANT,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    //ORES
    public static final RegistryObject<Block> JADE_ORE = registerBlock("jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.WUXIA_TAB);
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = registerBlock("deepslate_jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.WUXIA_TAB);

    //Non-Block Block
    public static final RegistryObject<Block> JADE_SWORD_BLOCK = registerBlock("jade_sword_block",
            () -> new JadeSwordBlock(
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion()), ModCreativeModeTab.WUXIA_TAB);

    //CROPS
    public static final RegistryObject<Block> STAR_GRASS_CROP = BLOCKS.register("star_grass_crop",
            () -> new StarGrassCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> SNOWY_GRASS_CROP = BLOCKS.register("snowy_grass_crop",
            () -> new SnowyGrassCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> SPIRITUAL_GRASS_CROP = BLOCKS.register("spiritual_grass_crop",
            () -> new SpiritualGrassCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> registry = BLOCKS.register(name, block);
        registerBlockItem(name, registry, tab);

        return  registry;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}