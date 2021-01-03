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
import io.github.cybergoose.harderfarmer.handlers.PlayerJoin;
import io.github.cybergoose.harderfarmer.managers.DBManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class HarderFarmer extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        this.getServer().getPluginManager().registerEvents(new FarmingDrop(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + this.getServer().getName() + " is enabled.");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + this.getServer().getName() + " is disabled.");
    }

    private void loadConfig(){

        this.getConfig().options().copyDefaults(true);
        DBManager dbManager= new DBManager(
                this.getConfig().getString("database.host"),
                this.getConfig().getString("database.dbname"),
                this.getConfig().getString("database.user"),
                this.getConfig().getString("database.pswd"),
                this.getConfig().getInt("database.port")
        );

        try{
            dbManager.dbConnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        this.saveConfig();
    }
}
