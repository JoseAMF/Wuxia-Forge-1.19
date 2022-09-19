package net.triplenold.wuxia.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.JADE.get());
        simpleItem(ModItems.ROSELLE.get());
        simpleItem(ModItems.ROSELLE_SEEDS.get());
        simpleItem(ModItems.STAR_GRASS.get());
        simpleItem(ModItems.STAR_GRASS_SEEDS.get());
        simpleItem(ModItems.FLYING_JADE_SWORD.get());


        simpleBlock(ModBlocks.JADE_BLOCK.get());
        simpleBlock(ModBlocks.JADE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_JADE_ORE.get());
        simpleBlock(ModBlocks.JADE_SWORD_BLOCK.get());

        handheldItem(ModItems.JADE_AXE.get());
        handheldItem(ModItems.JADE_PICKAXE.get());
        handheldItem(ModItems.JADE_HOE.get());
        handheldItem(ModItems.JADE_SHOVEL.get());
        handheldItem(ModItems.JADE_SWORD.get());

        simpleItem(ModItems.TEST_MISSILE_PROJECTILE.get());
        simpleItem(ModItems.TEST_MISSILE_PROJECTILE2.get());
    }


    private ItemModelBuilder simpleItem(ResourceLocation location) {
        return withExistingParent(location.getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WuxiaMod.MOD_ID,"item/" + location.getPath()));
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WuxiaMod.MOD_ID,"item/" + item.toString()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.toString(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(WuxiaMod.MOD_ID,"item/" + item.toString()));
    }

    private ItemModelBuilder simpleBlock(Block block) {
        return cubeAll(normalizeBlockPath(block), new ResourceLocation(WuxiaMod.MOD_ID,
                "block/" + normalizeBlockPath(block)));
    }


    private String normalizeBlockPath(Block block) {
        return block.toString().replace("Block{", "").replace("}", "")
                .replace(WuxiaMod.MOD_ID+ ":", "");
    }
}
