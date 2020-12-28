/*
*
* Title:    HarderFarmer(name subject to change)
* Author:   CyberGoose
* Start:    2020-12-18
* Update:   2020-12-23
* Version:  1.0
*
* */

package io.github.cybergoose.harderfarmer;

import io.github.cybergoose.harderfarmer.handlers.FarmingDrop;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class HarderFarmer extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        this.getServer().getPluginManager().registerEvents(new FarmingDrop(), this);
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + this.getServer().getName() + " is enabled.");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + this.getServer().getName() + " is disabled.");
    }

    public void loadConfig(){
        this.getConfig().options().copyDefaults(true);

//        this.getConfig().set("HECTOR", "this is a hector");
//        ConfigManager configManager= new ConfigManager(this, "test");
//        configManager.addComment("Its", "Me");
        this.saveConfig();
    }
}
