package io.github.cybergoose.harderfarmer.managers;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin PLUGIN;

    public ConfigManager(JavaPlugin PLUGIN, String filePath){
        this.PLUGIN= PLUGIN;
        createConfigFile(filePath);
    }

    private void createConfigFile(String filePath){
        File file= new File(PLUGIN.getDataFolder() + File.separator + filePath + ".yml");
        if(!file.exists()){
            try{
                file.createNewFile();
                FileOutputStream fos= new FileOutputStream(file);
                byte[] bytes= ("#" + file.getName() + " configuration file.").getBytes();
                fos.write(bytes);
                fos.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
