package mike.tutorialmod.net;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

// #region item
public class LightningStick extends Item {
	public LightningStick(Properties properties) {
		super(properties);
	}
	// #endregion item

	// #region use
	@Override
	public InteractionResult use(Level level, Player user, InteractionHand hand) {
		// Ensure we don't spawn the lightning only on the client.
		// This is to prevent desync.
		if (level.isClientSide()) {
			return InteractionResult.PASS;
		}

		// Aim from the player's eyes so pitch matters as well as horizontal direction.
		Vec3 strikePos = user.getEyePosition().add(user.getLookAngle().scale(10.0));

		// Spawn the lightning bolt.
		LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
		lightningBolt.setPos(strikePos);
		level.addFreshEntity(lightningBolt);

		return InteractionResult.SUCCESS;
	}
	// #endregion use

	// #region custom_tooltip
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("itemTooltip.TutorialMod.lightning_stick").withStyle(ChatFormatting.GOLD));
	}
	// #endregion custom_tooltip
	// #region item
}
// #endregion item
