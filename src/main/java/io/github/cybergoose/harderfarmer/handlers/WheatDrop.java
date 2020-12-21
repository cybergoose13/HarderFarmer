package io.github.cybergoose.harderfarmer.handlers;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class WheatDrop implements Listener {

    @EventHandler
    public void wheatDrop(BlockBreakEvent blockBreakEvent){
        Material material = blockBreakEvent.getBlock().getType();
        if(material == Material.WHEAT) {
            Collection<ItemStack> drops = blockBreakEvent.getBlock().getDrops();
            for( ItemStack item : drops){
                blockBreakEvent.getPlayer().sendMessage(item.getType().toString());
            }

        }
    }
}
