package com.kjmaster.electrostatics.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean isTreeTrapEnabled;
    public static int ElectrostaticRfPerTick;
    public static int JumpRfPerTick;
    public static int detectionAround;
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
        config.addCustomCategoryComment(category, "Settings for the electrostatic generator");
        JumpRfPerTick = config.getInt("JumpRfPerTick", category, 10, 0, 10000,
                "Set the amount of rf per tick the jump generator produces.");
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
