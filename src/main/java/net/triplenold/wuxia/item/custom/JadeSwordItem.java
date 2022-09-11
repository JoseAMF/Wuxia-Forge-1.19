package net.triplenold.wuxia.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.triplenold.wuxia.util.IActivatableItem;

public class JadeSwordItem extends Item implements IActivatableItem {

    private Boolean _isActive = false;

    public JadeSwordItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            this.setActive(!this.isActive());
        }
        return super.use(level, player, hand);
    }

    @Override
    public void setActive(Boolean isActive) {
        this._isActive = isActive;
    }

    @Override
    public Boolean isActive() {
        return _isActive;
    }
}
