package io.github.cybergoose.harderfarmer;

import io.github.cybergoose.harderfarmer.handlers.WheatDrop;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class HarderFarmer extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        this.getServer().getPluginManager().registerEvents(new WheatDrop(), this);
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + this.getServer().getName() + " is enabled.");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + this.getServer().getName() + " is disabled.");
    }

    public void loadConfig(){
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }
}
