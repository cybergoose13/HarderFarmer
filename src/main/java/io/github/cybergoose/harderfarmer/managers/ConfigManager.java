/*
 *
 * Title:    HarderFarmer(name subject to change)
 * Author:   CyberGoose
 * Start:    2020-12-18
 * Update:   2020-12-25
 *
 * STATUS:  UNUSED, INCOMPLETE
 *
 * */

package io.github.cybergoose.harderfarmer.managers;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConfigManager {

    private final File FILE;
    private FileOutputStream fos;

    public ConfigManager(JavaPlugin PLUGIN, String filePath){
        String EXT = ".yml";
        this.FILE= new File(PLUGIN.getDataFolder() + File.separator + filePath + EXT);
        createConfigFile();
    }   //  End of ConfigManager

    @SuppressWarnings("unused")
    public void setBoolean(String path, boolean value){
        try{
            fos= new FileOutputStream(FILE,true);
            setPath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeBoolean(value);
            dos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }   //  End of setBoolean

    @SuppressWarnings("unused")
    public void setInt(String path, int value){
        try{
            fos= new FileOutputStream(FILE, true);
            setPath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeInt(value);
            dos.close();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }   //  End of setInt

    @SuppressWarnings("unused")
    public void setDouble(String path, double value){
        try{
            fos= new FileOutputStream(FILE, true);
            setPath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeDouble(value);
            dos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }   //  End of setDouble

    @SuppressWarnings("unused")
    public void setString(String path, String value){
        try{
            fos= new FileOutputStream(FILE, true);
            setPath(path);
            DataOutputStream dos= new DataOutputStream(fos);
            dos.writeChars(value);
            dos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }   //  End of setString

    private void setPath(String path) throws IOException{
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
    }   //  End of setPath

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
    }   //  End of addComment

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
    }   //  End of createConfigFile

    //    Super crazy recursion for multiple indents
    //    Possibly change to forloop
    private String indent(int loops, String indents){
        if(loops == 0) return indents;
        indents+= "\t";
        return indent(loops-1, indents);
    }   //  End of indent
}
