package com.greenone.modutils.enums;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.MaterialColor;

public interface IWood {
    MaterialColor innerColor = null;
    MaterialColor outerColor = null;
    AbstractTreeGrower grower = null;

    default MaterialColor getInnerColor(){
        return innerColor;
    }
    default MaterialColor getOuterColor(){
        return outerColor;
    }
    default AbstractTreeGrower getGrower(){
        return grower;
    }
    String tagName();
}
