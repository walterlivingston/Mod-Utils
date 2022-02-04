package com.greenone.modutils.network;

import com.greenone.modutils.ModUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID(){ return ID++; }

    public static void registerMessages(){
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ModUtils.MOD_ID, "modutils"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(), Sync.class, Sync::toBytes, Sync::new, Sync::handle);
    }
}
