package io.github.cybergoose.harderfarmer.managers;

import org.bukkit.plugin.java.JavaPlugin;
import sun.nio.cs.UTF_8;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConfigManager {

    private final JavaPlugin PLUGIN;
    private final File FILE;
    private FileOutputStream fos;

    public ConfigManager(JavaPlugin PLUGIN, String filePath){
        this.PLUGIN= PLUGIN;
        String EXT = ".yml";
        this.FILE= new File(PLUGIN.getDataFolder() + File.separator + filePath + EXT);
        createConfigFile();
    }

    @SuppressWarnings("unused")
    public void writeBoolean(String path, boolean value){
        try{
            fos= new FileOutputStream(FILE,true);
            writePath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeBoolean(value);
            dos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void writeInt(String path, int value){
        try{
            fos= new FileOutputStream(FILE, true);
            writePath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeInt(value);
            dos.close();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void writeDouble(){

    }

    @SuppressWarnings("unused")
    public void writeString(){

    }

    @SuppressWarnings("unused")
    public void addComment(String ...comments){
        try{
            fos= new FileOutputStream(FILE, true);
            for(String comment : comments){
                byte[] bytes= ("#\t" + comment + "\n").getBytes(StandardCharsets.UTF_8);
                fos.write(bytes);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void write(String filePath, String path, String value){
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

    private void writePath(String path) throws IOException{
        String[] paths= path.split("\\.");
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
    private void createConfigFile(){
        if(!FILE.exists()){
            try{
                FILE.createNewFile();
                fos= new FileOutputStream(FILE);
                byte[] bytes= ("#\t" + FILE.getName() + " configuration file.\n").getBytes();
                fos.write(bytes);
                fos.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
