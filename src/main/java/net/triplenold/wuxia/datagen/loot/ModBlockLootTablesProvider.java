package net.triplenold.wuxia.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.block.custom.RoselleCropBlock;
import net.triplenold.wuxia.item.ModItems;

public class ModBlockLootTablesProvider extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.JADE_BLOCK.get());
        this.dropSelf(ModBlocks.JADE_SWORD_BLOCK.get());

        this.add(ModBlocks.JADE_ORE.get(),
                (block -> createOreDrop(block, ModItems.JADE.get())));
        this.add(ModBlocks.DEEPSLATE_JADE_ORE.get(),
                (block -> createOreDrop(block, ModItems.JADE.get())));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.ROSELLE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RoselleCropBlock.AGE, 6));

        this.add(ModBlocks.ROSELLE_CROP.get(), createCropDrops(ModBlocks.ROSELLE_CROP.get(), ModItems.ROSELLE.get(),
                ModItems.ROSELLE_SEEDS.get(), lootitemcondition$builder));

        lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STAR_GRASS_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RoselleCropBlock.AGE, 6));

        this.add(ModBlocks.STAR_GRASS_CROP.get(), createCropDrops(ModBlocks.STAR_GRASS_CROP.get(), ModItems.STAR_GRASS.get(),
                ModItems.STAR_GRASS_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}