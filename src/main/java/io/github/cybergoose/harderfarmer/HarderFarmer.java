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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class HarderFarmer extends JavaPlugin {

    private Connection connection;
    private String host, database, username, password;
    private int port;
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

    private void loadConfig(){

        this.getConfig().options().copyDefaults(true);
        this.host= this.getConfig().getString("database.host");
        this.database= this.getConfig().getString("database.dbname");
        this.username= this.getConfig().getString("database.user");
        this.password= this.getConfig().getString("database.pswd");
        this.port= this.getConfig().getInt("database.port");

        try{
            dbconnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        this.saveConfig();
    }

    private void dbconnect() throws SQLException {
        if(connection != null && !connection.isClosed()){
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" +
                this.port + "/" + this.database,
                this.username, this.password);
        this.getServer().getConsoleSender().sendMessage("Connection Successful");
    }
}
