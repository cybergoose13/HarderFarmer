package io.github.cybergoose.harderfarmer.handlers;

import io.github.cybergoose.harderfarmer.util.FarmingBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class FarmingDrop implements Listener {

    @EventHandler
    public void farmingDrop(BlockBreakEvent blockBreakEvent){

        Material material = blockBreakEvent.getBlock().getType();
        if(!FarmingBlock.contains(material)) return;

        Player player = blockBreakEvent.getPlayer();
        player.sendMessage("Block is contained.");

    }
}
