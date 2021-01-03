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
import java.util.Objects;

public class HarderFarmer extends JavaPlugin {

    public static DBManager dbManager;

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
        this.saveConfig();

        dbManager= new DBManager(
                Objects.requireNonNull((this.getConfig().getString("database.host"))),
                Objects.requireNonNull(this.getConfig().getString("database.dbname")),
                Objects.requireNonNull(this.getConfig().getString("database.user")),
                Objects.requireNonNull(this.getConfig().getString("database.pswd")),
                this.getConfig().getInt("database.port")
        );

//        try{
//            dbManager.dbConnect();
//        }catch (SQLException exception){
//            exception.printStackTrace();
//        }
    }
}
