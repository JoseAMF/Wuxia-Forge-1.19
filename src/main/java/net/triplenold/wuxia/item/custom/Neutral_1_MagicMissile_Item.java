package net.triplenold.wuxia.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.triplenold.wuxia.entity.custom.TestMissileProjectileEntity;
import net.triplenold.wuxia.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Neutral_1_MagicMissile_Item extends Item {

    private int speed = 1;

    public Neutral_1_MagicMissile_Item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

            if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
                player.level.playSound(null, player, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1f, 1f);

                double x = player.getX() + player.getLookAngle().x;
                double y = player.getY() + player.getLookAngle().y;
                double z = player.getZ() + player.getLookAngle().z;

                TestMissileProjectileEntity magic_missile = new TestMissileProjectileEntity(player, player.level);
                magic_missile.setItem(ModItems.TEST_MISSILE_PROJECTILE.get().getDefaultInstance());
//                magic_missile.setPos(x, y+1.5, z);
                magic_missile.setDeltaMovement(player.getLookAngle().x*speed, player.getLookAngle().y*speed, player.getLookAngle().z*speed);
                player.level.addFreshEntity(magic_missile);

//                magic_missile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

                player.getCooldowns();
            }

        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Right click to shoot!").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }


        super.appendHoverText(stack, level, components, flag);
    }
}