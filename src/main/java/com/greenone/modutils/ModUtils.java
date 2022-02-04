package com.greenone.modutils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(ModUtils.MOD_ID)
public class ModUtils {
    public static final String MOD_ID = "modutils";

    public ModUtils() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
