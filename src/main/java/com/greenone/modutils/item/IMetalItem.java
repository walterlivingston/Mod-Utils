package com.greenone.modutils.item;

import com.greenone.modutils.enums.IMetal;

public interface IMetalItem {
    IMetal metal = null;

    default boolean hasEffect(){
        return metal!=null ? metal.hasEffect() : false;
    }
}
