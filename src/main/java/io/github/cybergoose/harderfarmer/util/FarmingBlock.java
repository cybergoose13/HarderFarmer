package io.github.cybergoose.harderfarmer.util;

import org.bukkit.Material;

public enum FarmingBlock {
    WHEAT(Material.WHEAT),
    CARROT(Material.CARROTS),
    POTATO(Material.POTATOES),
    PUMPKIN(Material.PUMPKIN),
    MELON(Material.MELON),
    BERRIES(Material.SWEET_BERRY_BUSH), //  only catches when broken
    BEET(Material.BEETROOTS);

    public Material type;

    FarmingBlock(Material type){
        this.type= type;
    }

    public static boolean contains(Material material){
        for(FarmingBlock farmingBlock :  FarmingBlock.values()){
            if(material == farmingBlock.type){
                return true;
            }
        }
        return false;
    }
}
