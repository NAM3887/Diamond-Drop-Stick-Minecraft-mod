package mike.tutorialmod.net;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

// #region item
public class DiamondDropStick extends Item {
	public DiamondDropStick(Properties properties) {
		super(properties);
	}
	// #endregion item

	// #region use
	@Override
	public @NotNull InteractionResult use(Level level, Player user, InteractionHand hand) {
		// This is to prevent desync.
		if (level.isClientSide()) {
			return InteractionResult.PASS;
		}
		// Aim from the player's eyes so pitch matters as well as horizontal direction.
		Vec3 DropPos = user.getEyePosition().add(user.getLookAngle().scale(3));
		// spawn diamond item entity in front of the player and toss it forward.
		ItemEntity diamond = new ItemEntity(level, DropPos.x, DropPos.y, DropPos.z,
				new ItemStack(Items.DIAMOND)
		);
		diamond.setDeltaMovement(user.getLookAngle().scale(0.4));
		level.addFreshEntity(diamond);
		return InteractionResult.SUCCESS;
	}
	// #endregion use

	// #region custom_tooltip
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("itemTooltip.TutorialMod.DiamondDropStick").withStyle(ChatFormatting.GOLD));
	}
	// #endregion custom_tooltip
	// #region item
}
// #endregion item
