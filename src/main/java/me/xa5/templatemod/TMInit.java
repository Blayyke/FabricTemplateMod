package me.xa5.templatemod;

import me.xa5.modconfig.FabricModConfig;
import me.xa5.templatemod.blocks.BlockTest;
import me.xa5.templatemod.items.ItemTest;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class TMInit implements ModInitializer {
    public static final String MOD_ID = "templatemod";
    private static TMInit instance;
    private Logger logger = LogManager.getFormatterLogger(getClass().getSimpleName());
    private FabricModConfig config = new FabricModConfig();

    private ItemGroup group = FabricItemGroupBuilder
            .create(newIdentifier("tab"))
            .icon(() -> new ItemStack(TMObjects.BLOCKS.testBlock))
            .build();

    public static Identifier newIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static TMInit getInstance() {
        return instance;
    }

    @Override
    public void onInitialize() {
        instance = this;

        try {
            config.loadConfig(new File(config.getConfigFolder(), MOD_ID + ".conf"), this::readConfig);
        } catch (IOException e) {
            getLogger().fatal("Caught exception during reading/loading of config file!");
            throw new RuntimeException(e);
        }

        registerBlocks();
        registerItems();
        registerEntities();
        registerGameRules();
        config.saveConfig();
    }

    private void registerGameRules() {
        GameRules.getKeys().put("doWitherSpawning", new GameRules.Key("true", GameRules.Type.BOOLEAN));
    }

    private void readConfig() {
    }

    private void registerEntities() {
    }

    private void registerItems() {
        TMObjects.register(new ItemTest());
    }

    private void registerBlocks() {
        TMObjects.register(new BlockTest());
    }

    public ItemGroup getModGroup() {
        return group;
    }

    public Logger getLogger() {
        return logger;
    }

    public FabricModConfig getConfig() {
        return config;
    }
}