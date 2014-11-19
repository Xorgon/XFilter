package me.xorgon.xfilter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * XFilter main class.
 */
public class XFilter extends JavaPlugin {
    private XFManager manager;
    private static XFilter instance;
    private FileConfiguration config;

    public XFilter() {
        instance = this;
    }

    @Override
    public void onEnable() {
        manager = new XFManager(instance);
        manager.load();
        Bukkit.getPluginManager().registerEvents(new XFListeners(this), this);
        getCommand("xfilter").setExecutor(new XFCommand());
    }

    public void onDisable() {
        manager.save();
    }

    public XFManager getManager() {
        return manager;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public static XFilter getInstance() {
        return instance;
    }
}
