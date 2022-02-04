package com.greenone.modutils.enums;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;

public interface IMetal {
    Type type = null;
    Tier tier = null;
    ArmorMaterial armorMat = null;

    default Type getType(){
        return type;
    }
    default Tier getTier(){
        return tier;
    };
    default ArmorMaterial getArmor(){
        return armorMat;
    };
    default boolean hasEffect(){
        return type==Type.BLESSED;
    }
    default boolean generateOre(){
        return type==Type.PURE;
    };
    default boolean canEnchant(){
        return type!=Type.VANILLA;
    }
    default boolean isVanilla(){
        return type==Type.VANILLA;
    }
    String tagName();

    enum Type {
        PURE,
        ALLOY,
        VANILLA,
        BLESSED;
    }
}
