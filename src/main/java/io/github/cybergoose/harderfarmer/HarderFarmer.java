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
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HarderFarmer extends JavaPlugin {

    private static Connection connection;
    private String host, database, username, password;
    private int port;

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
        this.host= this.getConfig().getString("database.host");
        this.database= this.getConfig().getString("database.dbname");
        this.username= this.getConfig().getString("database.user");
        this.password= this.getConfig().getString("database.pswd");
        this.port= this.getConfig().getInt("database.port");

        try{
            dbConnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        this.saveConfig();
    }

    private void dbConnect() throws SQLException {
        if(connection != null && !connection.isClosed()){
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" +
                this.port + "/" + this.database,
                this.username, this.password);
    }

    public static PreparedStatement preparedStatement(String query){
        PreparedStatement ps= null;
        try{
            ps= connection.prepareStatement(query);
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return ps;
    }
}
