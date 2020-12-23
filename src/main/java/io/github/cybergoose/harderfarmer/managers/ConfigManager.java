package io.github.cybergoose.harderfarmer.managers;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin PLUGIN;

    public ConfigManager(JavaPlugin PLUGIN, String filePath){
        this.PLUGIN= PLUGIN;
        createConfigFile(filePath);
    }

    public void writeBoolean(){

    }

    public void writeInt(String filePath, String path, int value){
        try{
            File file= new File(PLUGIN.getDataFolder() + File.separator + filePath + "yml");
            writePath(file, path);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void writeLong(){

    }

    public void writeDouble(){

    }

    public void writeString(){

    }

    public void write(String filePath, String path, String value){
        try{
            File file= new File(PLUGIN.getDataFolder() + File.separator + filePath + ".yml");
            String[] paths= path.split("\\.");
            FileOutputStream fos= new FileOutputStream(file, true);
            String tabs = "\t";

            if(paths.length > 1){

                for (int i= 0; i < paths.length -1; i++) {
                    tabs= indent(i, "");
                    byte[] path_bytes= (tabs + paths[i] + ":\n").getBytes();
                    fos.write(path_bytes);
                }
                fos.write((tabs + "\t" + paths[paths.length -1] + ":").getBytes());
            }else fos.write((path + ":").getBytes());

            byte[] value_bytes= ("\t" + value + "\n").getBytes();
            fos.write(value_bytes);
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writePath(File file, String path) throws IOException{
        String[] paths= path.split("\\.");
        FileOutputStream fos= new FileOutputStream(file, true);
        String tabs= "";

        if(paths.length > 1){
            for(int i= 0; i  < paths.length-1; i++){
                tabs= indent(i,"");
                byte[] path_bytes= (tabs + paths[i] + ":\n").getBytes();
                fos.write(path_bytes);
            }
            fos.write((tabs + "\t" + paths[paths.length -1] + ":").getBytes());
        }else fos.write((path + ":").getBytes());
    }

//    Super crazy recursion for multiple indents
    private String indent(int loops, String indents){
        if(loops == 0) return indents;
        indents+= "\t";
        return indent(loops-1, indents);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createConfigFile(String filePath){
        File file= new File(PLUGIN.getDataFolder() + File.separator + filePath + ".yml");
        if(!file.exists()){
            try{
                file.createNewFile();
                FileOutputStream fos= new FileOutputStream(file);
                byte[] bytes= ("#\t" + file.getName() + " configuration file.\n").getBytes();
                fos.write(bytes);
                fos.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
