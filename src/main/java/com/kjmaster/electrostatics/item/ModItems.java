package com.kjmaster.electrostatics.item;

import com.kjmaster.electrostatics.Electrostatics;
import com.kjmaster.kjlib.common.items.ItemBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase rubber = new ItemBase("rubber", Electrostatics.electrostaticTab, 64);
    private static ItemTreeTap treeTap = new ItemTreeTap(Item.ToolMaterial.WOOD, ItemTreeTap.effectiveBlocks, "tree_tap", 1, Electrostatics.electrostaticTab);
    private static ItemRubberGlove rubberGlove = new ItemRubberGlove(Item.ToolMaterial.WOOD, ItemRubberGlove.effectiveBlocks, "rubber_glove", 1, Electrostatics.electrostaticTab);
    static ItemBase resin = new ItemBase("resin", Electrostatics.electrostaticTab, 64);
    public static ItemArmor rubberBoots = new ItemArmor(Electrostatics.rubberArmorMaterial, EntityEquipmentSlot.FEET, "rubber_boots");
    public static ItemArmor glassBoots = new ItemArmor(Electrostatics.glassArmorMaterial, EntityEquipmentSlot.FEET, "glass_boots");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                treeTap,
                rubberGlove,
                resin,
                rubber,
                rubberBoots,
                glassBoots
        );
            GameRegistry.addSmelting(ModItems.resin, new ItemStack(ModItems.rubber, 1), 0.4F);
    }

    public static void registerModels() {
        treeTap.registerItemModel();
        rubberGlove.registerItemModel();
        Electrostatics.proxy.registerItemRenderer(resin, 0, "resin");
        Electrostatics.proxy.registerItemRenderer(rubber, 0, "rubber");
        rubberBoots.registerItemModel();
        glassBoots.registerItemModel();
    }
}

