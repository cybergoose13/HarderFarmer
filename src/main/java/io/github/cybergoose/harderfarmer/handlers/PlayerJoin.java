package io.github.cybergoose.harderfarmer.handlers;

import io.github.cybergoose.harderfarmer.HarderFarmer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player= event.getPlayer();
        UUID uuid= player.getUniqueId();
        try{
            if( !(HarderFarmer.preparedStatement("SELECT * FROM skills WHERE UUID = '" + uuid + "';")
                    .executeQuery().next()) ){
                HarderFarmer.preparedStatement("INSERT INTO skills(UUID, NAME, FARMING) VALUES ('" +
                        uuid + "','" + player.getName() + "', DEFAULT);").executeUpdate();
            }else{
                HarderFarmer.preparedStatement("UPDATE skills SET FARMING = '2' WHERE UUID = '" +
                        uuid + "';").executeUpdate();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
