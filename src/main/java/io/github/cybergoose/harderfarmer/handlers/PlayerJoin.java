/*
 *
 * Author:   CyberGoose
 * Start:    2020-12-18
 * Update:   2021-01-02
 *
 * */

package io.github.cybergoose.harderfarmer.handlers;

import io.github.cybergoose.harderfarmer.interfaces.SkillInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerJoin implements Listener, SkillInterface {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player= event.getPlayer();
        UUID uuid= player.getUniqueId();

        try{
            ResultSet resultSet=
                    dbManager.statement("SELECT * FROM skills WHERE UUID = '" + uuid + "';")
                            .executeQuery();
            if( !(resultSet.next()) ){
                dbManager.statement("INSERT INTO skills(UUID, NAME, FARMING) VALUES ('" +
                        uuid + "','" + player.getName() + "', DEFAULT);")
                        .executeUpdate();
            }else{
//                HarderFarmer.preparedStatement("UPDATE skills SET FARMING = '2' WHERE UUID = '" +
//                        uuid + "';").executeUpdate();
                String strUUID= resultSet.getString("UUID");
                String strName= resultSet.getString("NAME");
                int farmingLevel= resultSet.getInt("FARMING");
                PLUGIN.getServer().getConsoleSender().sendMessage("STRING strUUID: " + strUUID);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
