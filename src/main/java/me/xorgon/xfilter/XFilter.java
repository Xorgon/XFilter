package me.xorgon.xfilter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;

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

        if (manager.getCollectStats()) {
            Bukkit.broadcastMessage("Attempting to load MCStats.");
            try {
                MetricsLite metrics = new MetricsLite(this);
                metrics.start();
            } catch (IOException e) {
                // Failed to submit the stats :-(
                Bukkit.broadcastMessage("Failed to load MCStats.");
            }
        }
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