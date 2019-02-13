package me.xa5.templatemod;

import net.fabricmc.api.ClientModInitializer;

public class TMInitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
    }
}