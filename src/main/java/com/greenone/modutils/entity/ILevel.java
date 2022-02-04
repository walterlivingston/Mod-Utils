package com.greenone.modutils.entity;

import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public interface ILevel extends INBTSerializable<Tag>, ICapabilityProvider {
    int getMaxLevel();
    void setMaxLevel(int level);
    int getLevel();
    void setLevel(int level);
    float getExperience();
    void setExperience(float amount);
    boolean addExperience(Player player, float amount);
}
