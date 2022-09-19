package net.triplenold.wuxia.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.WuxiaMod;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.block.custom.RoselleCropBlock;
import net.triplenold.wuxia.block.custom.StarGrassCropBlock;
import net.triplenold.wuxia.item.ModItems;

import java.util.function.Function;

public class ModBlocksStateProvider extends BlockStateProvider {

    public ModBlocksStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WuxiaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.JADE_BLOCK.get());
        simpleBlock(ModBlocks.JADE_SWORD_BLOCK.get());
        simpleBlock(ModBlocks.JADE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_JADE_ORE.get());


        makeCrop((RoselleCropBlock)ModBlocks.ROSELLE_CROP.get(), "roselle_stage", "roselle_stage");
        makeCrop((StarGrassCropBlock)ModBlocks.STAR_GRASS_CROP.get(), "star_grass_stage", "star_grass_stage");
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(WuxiaMod.MOD_ID, "block/" + textureName + state.getValue(block.getAgeProperty()))));

        return models;
    }
}
