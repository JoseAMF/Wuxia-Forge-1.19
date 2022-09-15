package net.triplenold.wuxia.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.triplenold.wuxia.cultivation.PlayerCultivationProvider;
import net.triplenold.wuxia.networking.ModNetworking;
import net.triplenold.wuxia.networking.packet.SetPlayerFlightC2SPacket;
import net.triplenold.wuxia.util.IActivatableItem;

public class JadeSwordItem extends Item implements IActivatableItem {

    private Boolean _isActive = false;

    public JadeSwordItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(playerCultivation -> {
                if(playerCultivation.getQi() > 0)this.setActive(!this.isActive());
            });

        }
        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity player, int p_41407_, boolean p_41408_) {
        super.inventoryTick(stack, level, player, p_41407_, p_41408_);
        if(!level.isClientSide() && level instanceof ServerLevel &&
                player instanceof LivingEntity livingEntity && livingEntity.tickCount % 20 == 0) {
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(playerCultivation -> {
                if (isActive()) {
                    if (playerCultivation.getQi() < 3) {
                        setActive(false);
                        return;
                    }

                    playerCultivation.addQi(-3);

                }
            });
        }
    }


    @Override
    public void setActive(Boolean isActive) {
        this._isActive = isActive;
        ModNetworking.sendToServer(new SetPlayerFlightC2SPacket(_isActive));
    }

    @Override
    public Boolean isActive() {
        return _isActive;
    }


}
