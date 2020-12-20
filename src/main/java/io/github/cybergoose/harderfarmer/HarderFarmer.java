package io.github.cybergoose.harderfarmer;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class HarderFarmer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + this.getServer().getName() + " is enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
