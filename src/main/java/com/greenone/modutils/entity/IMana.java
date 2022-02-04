package com.greenone.modutils.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMana extends INBTSerializable<Tag>, ICapabilityProvider {
    float getMana();
    void setMana(float amount);
    float getMaxMana();
    void setMaxMana(float amount);
    void fillMana();
    boolean consumeMana(float amount);
    void sync(Player player);
    void readFromNBT(CompoundTag tag, IMana instance);
}
