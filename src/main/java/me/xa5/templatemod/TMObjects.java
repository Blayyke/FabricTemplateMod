package me.xa5.templatemod;

import me.xa5.templatemod.blocks.BlockTest;
import me.xa5.templatemod.items.ItemTest;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Logger;

public class TMObjects {
    public static final Blocks BLOCKS = new Blocks();
    public static final Items ITEMS = new Items();

    public static void register(Registrable reg) {
        Logger logger = TMInit.getInstance().getLogger();

        if (reg instanceof XBlock) {
            XBlock block = (XBlock) reg;
            if (block.shouldRegisterItemBlock()) {
                block.registerItemBlock();
            }

            Registry.register(Registry.BLOCK, TMInit.newIdentifier(block.getRegistryName()), block);
        } else if (reg instanceof XItem) {
            XItem item = (XItem) reg;
            Registry.register(Registry.ITEM, TMInit.newIdentifier(item.getRegistryName()), item);
        }

        logger.info("Registered registrable object " + reg.getRegistryName() + ".");
    }

    public static class Blocks {
        public BlockTest testBlock;
    }

    public static class Items {
        public ItemTest testItem;
    }
}