package net.triplenold.wuxia.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.triplenold.wuxia.block.ModBlocks;
import net.triplenold.wuxia.block.custom.SnowyGrassCropBlock;
import net.triplenold.wuxia.block.custom.SpiritualGrassCropBlock;
import net.triplenold.wuxia.block.custom.StarGrassCropBlock;
import net.triplenold.wuxia.item.ModItems;

public class ModBlockLootTablesProvider extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.JADE_BLOCK.get());
        this.dropSelf(ModBlocks.JADE_SWORD_BLOCK.get());
        this.dropSelf(ModBlocks.GINGER_PLANT.get());

//        this.add((FlowerPotBlock)ModBlocks.POTTED_GINGER_PLANT.get(), BlockLoot::createPotFlowerItemTable);

        dropPottedContents(ModBlocks.POTTED_GINGER_PLANT.get());

        this.add(ModBlocks.JADE_ORE.get(),
                (block -> createJadeOreDrops(block)));
        this.add(ModBlocks.DEEPSLATE_JADE_ORE.get(),
                (block -> createJadeOreDrops(block)));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.SNOWY_GRASS_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SnowyGrassCropBlock.AGE, 6));

        this.add(ModBlocks.SNOWY_GRASS_CROP.get(), createCropDrops(ModBlocks.SNOWY_GRASS_CROP.get(), ModItems.SNOWY_GRASS.get(),
                ModItems.SNOWY_GRASS_SEEDS.get(), lootitemcondition$builder));

        lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.SPIRITUAL_GRASS_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SpiritualGrassCropBlock.AGE, 6));

        this.add(ModBlocks.SPIRITUAL_GRASS_CROP.get(), createCropDrops(ModBlocks.SPIRITUAL_GRASS_CROP.get(), ModItems.SPIRITUAL_GRASS.get(),
                ModItems.SPIRITUAL_GRASS_SEEDS.get(), lootitemcondition$builder));

        lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STAR_GRASS_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StarGrassCropBlock.AGE, 3));

        this.add(ModBlocks.STAR_GRASS_CROP.get(), createCropDrops(ModBlocks.STAR_GRASS_CROP.get(), ModItems.STAR_GRASS.get(),
                ModItems.STAR_GRASS_SEEDS.get(), lootitemcondition$builder));
    }


    protected static LootTable.Builder createJadeOreDrops(Block block) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.JADE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    public void dropPottedContents(Block block) {
        this.add(block, (p_236251_) -> {
            return createPotFlowerItemTable(((FlowerPotBlock)p_236251_).getContent());
        });
    }

}