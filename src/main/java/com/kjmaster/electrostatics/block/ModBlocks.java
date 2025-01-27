package com.kjmaster.electrostatics.block;

import com.kjmaster.electrostatics.Electrostatics;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockStaticGenerator staticGenerator = new BlockStaticGenerator("electrostatic_generator",
            Material.IRON, Electrostatics.electrostaticTab, 1.0F, 10F, "pickaxe", 1);
    public static BlockLeatherCarpet staticLeatherCarpet = new BlockLeatherCarpet();
    public static BlockJumpGenerator staticJumpGenerator = new BlockJumpGenerator();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(staticGenerator, staticLeatherCarpet, staticJumpGenerator);
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(staticGenerator.createItemBlock(), staticLeatherCarpet.createItemBlock(), staticJumpGenerator.createItemBlock());
    }

    public static void registerModels() {
        staticGenerator.registerItemModel(Item.getItemFromBlock(staticGenerator));
        staticLeatherCarpet.registerItemModel(Item.getItemFromBlock(staticLeatherCarpet));
        staticJumpGenerator.registerItemModel(Item.getItemFromBlock(staticJumpGenerator));
    }
}
