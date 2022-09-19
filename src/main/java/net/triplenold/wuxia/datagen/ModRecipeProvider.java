package net.triplenold.wuxia.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {
//        ShapedRecipeBuilder.shaped(ModBlocks.JADE_BLOCK.get())
//                .define('J', ModItems.JADE.get())
//                .pattern("JJJ")
//                .pattern("JJJ")
//                .pattern("JJJ")
//                .unlockedBy("has_jade",
//                        inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JADE.get()).build()))
//                .save(finishedRecipeConsumer);

//        ShapelessRecipeBuilder.shapeless(ModItems.JADE.get())
//                .requires(ModBlocks.JADE_BLOCK.get())
//                .unlockedBy("has_jade_block", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(ModBlocks.JADE_BLOCK.get()).build()))
//                .save(finishedRecipeConsumer);

        nineBlockStorageRecipes(finishedRecipeConsumer, ModItems.JADE.get(), ModBlocks.JADE_BLOCK.get());
    }
}
