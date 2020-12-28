package io.github.cybergoose.harderfarmer.util;

import org.bukkit.Material;

public enum FarmingBlock {
    WHEAT(Material.WHEAT),
    CARROT(Material.CARROT),
    POTATO(Material.POTATO),
    PUMPKIN(Material.PUMPKIN),
    MELON(Material.MELON),
    KELP(Material.KELP_PLANT),
    BERRIES(Material.SWEET_BERRY_BUSH),
    BEET(Material.BEETROOT);

    public Material type;

    FarmingBlock(Material type){
        this.type= type;
    }

}
