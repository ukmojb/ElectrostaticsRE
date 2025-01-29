package com.kjmaster.electrostatics.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean isTreeTrapEnabled;
    public static int ElectrostaticRfPerTick;
    public static int JumpRfPerTick;
    public static int PetRfPerTime;
    public static int CoolingTickPerTime;
    public static boolean pettingBurn;
    public static int pettingBurnTime;
    public static int damage;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    private static void syncConfig() {
        String category;
        category = "Electrostatic generator";
        config.addCustomCategoryComment(category, "Settings for the electrostatic generator");
        ElectrostaticRfPerTick = config.getInt("ElectrostaticRfPerTick", category, 10, 0, 10000,
                "Set the amount of rf per tick the electrostatic generator produces.");
        category = "Jump generator";
        config.addCustomCategoryComment(category, "Settings for the jump generator");
        JumpRfPerTick = config.getInt("JumpRfPerTick", category, 10, 0, 10000,
                "Set the amount of rf per tick the jump generator produces.");
        category = "Pet cat";
        config.addCustomCategoryComment(category, "Settings for the pet cat");
        PetRfPerTime = config.getInt("PetRfPerTime", category, 10, 0, 10000,
                "Set the amount of rf per time pet cat produces.");
        CoolingTickPerTime = config.getInt("CoolingTickPerTime", category, 0, 0, 10000,
                "Set a cooling time after pet cat(The unit is tick)");
        pettingBurn = config.getBoolean("pettingBurn", category, true,
                "If true, petting a cat too quickly can make the cat burn up");
        pettingBurnTime = config.getInt("pettingBurnTime", category, 50, 0, 10000,
                "Set the amount of times a cat becomes inflamed when handled too quickly");
        category = "Tree tap";
        config.addCustomCategoryComment(category, "Settings for the tree tap");
        isTreeTrapEnabled = config.getBoolean("isTreeTapEnabled", category, true,
                "Set this to false to disable the tree taps functionality" +
                        " and force users to get rubber from another mod.");
        damage = config.getInt("damage", category, 30, 1, 256,
                "Set the amount of damage a tree tap takes when used to tap resin");
        config.save();
    }
}
