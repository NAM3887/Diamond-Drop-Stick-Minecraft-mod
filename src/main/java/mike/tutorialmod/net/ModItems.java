package mike.tutorialmod.net;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

// https://docs.fabricmc.net/1.21.1/develop/items/first-item
public class ModItems {
    // create item id and registry keys for the new Items
    private static Item register(String name, Function<Item.Properties, Item> itemFactory) {
        ResourceLocation itemId = ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, itemId);
        Item item = itemFactory.apply(new Item.Properties().setId(itemKey));
        return Registry.register(BuiltInRegistries.ITEM, itemId, item);
    }
    // create new items
    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new);
    public static final Item LIGHTNING_STICK = register("lightning_stick", LightningStick::new);
    public static final Item DIAMOND_DROP_STICK = register("diamond_drop_stick", DiamondDropStick::new);

    // called by TutorialMod.onInitialize()
    // this adds both registered items to the Ingredients tab in the Creative inventory.
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register(entries -> entries.accept(SUSPICIOUS_SUBSTANCE));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register(entries -> entries.accept(LIGHTNING_STICK));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register(entries -> entries.accept(DIAMOND_DROP_STICK)); }
}
