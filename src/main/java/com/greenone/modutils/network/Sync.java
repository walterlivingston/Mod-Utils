package com.greenone.modutils.network;

import com.greenone.modutils.entity.EasyMana;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class Sync extends AbstractPacket{
    private UUID entityId;
    private final CompoundTag nbt;

    public Sync(FriendlyByteBuf buf){
        this.entityId = buf.readUUID();
        this.nbt = buf.readNbt();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf){
        buf.writeUUID(this.entityId);
        buf.writeNbt(this.nbt);
    }

    public Sync(Player player, CompoundTag nbtIn){
        this.entityId = player.getUUID();
        this.nbt = nbtIn;
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            Minecraft.getInstance().level.getPlayerByUUID(this.entityId).getCapability(EasyMana.MANA).ifPresent((c) -> {
                new EasyMana().readFromNBT(this.nbt, c);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
