package io.github.cybergoose.harderfarmer.interfaces;

import io.github.cybergoose.harderfarmer.HarderFarmer;
import io.github.cybergoose.harderfarmer.managers.DBManager;
import org.bukkit.plugin.java.JavaPlugin;

public interface SkillInterface {
    JavaPlugin PLUGIN= HarderFarmer.getProvidingPlugin(HarderFarmer.class);
    DBManager dbManager= HarderFarmer.dbManager;
}
