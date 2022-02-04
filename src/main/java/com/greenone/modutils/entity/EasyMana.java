package com.greenone.modutils.entity;

import com.greenone.modutils.ModUtils;
import com.greenone.modutils.network.NetworkHandler;
import com.greenone.modutils.network.Sync;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EasyMana implements IMana {
    private float maxMana = 10;
    private float mana = maxMana;

    public static Capability<IMana> MANA = null;

    private final LazyOptional<IMana> instance = LazyOptional.of(() -> this);

    public void registerCapability(AttachCapabilitiesEvent event){
        if (!((event.getObject()) instanceof Player))
            event.addCapability(new ResourceLocation(ModUtils.MOD_ID, "easy_mana"), new EasyMana());
    }

    @Override
    public float getMana() {
        return mana;
    }

    @Override
    public void setMana(float amount) {
        mana = amount;
    }

    @Override
    public float getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMaxMana(float amount) {
        maxMana = amount;
    }

    @Override
    public void fillMana() {
        mana = maxMana;
    }

    @Override
    public boolean consumeMana(float amount) {
        if(mana >= amount){
            mana -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void sync(Player player) {
        if (!player.getCommandSenderWorld().isClientSide()){
            NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                    new Sync(player, (CompoundTag) this.serializeNBT()));
        }
    }

    @Override
    public void readFromNBT(CompoundTag tag, IMana instance) {
        instance.setMana(tag.getFloat("mana"));
        instance.setMaxMana(tag.getFloat("maxMana"));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap != MANA){
            return LazyOptional.empty();
        }
        return this.instance.cast();
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("mana", this.getMana());
        nbt.putFloat("maxMana", this.getMaxMana());

        return nbt;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        CompoundTag tag = (CompoundTag) nbt;

        this.setMana(tag.getFloat("mana"));
        this.setMaxMana(tag.getFloat("maxMana"));
    }
}
