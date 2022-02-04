package com.greenone.modutils.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class GenUtils {

    public static Vec3 getLookingAt(Player player, int distance) {
        Vec3 output;
        Level world = player.level;
        float f = player.xRotO; // Pitch
        float f1 = player.yRotO; // Yaw
        Vec3 vec3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 *((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 *((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f *((float) Math.PI / 180F));
        float f5 = Mth.sin(-f *((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec3d1 = vec3d.add((double) f6*distance, (double) f5 * distance, (double) f7 * distance);
        BlockHitResult trace = world.clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        output = trace.getLocation();
        return output;
    }

    public static boolean isItemInInventory(Player player, Item item) {
        for(ItemStack i : player.inventoryMenu.getItems()){
            if(i != null && i.getItem()==item){
                return true;
            }
        }
        return false;
    }
}
