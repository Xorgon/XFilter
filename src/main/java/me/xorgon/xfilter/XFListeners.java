package me.xorgon.xfilter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;

/**
 * XFilter listener class.
 */
public class XFListeners implements Listener {

    private XFilter plugin = XFilter.getInstance();

    public XFListeners(XFilter plugin) {
        this.plugin = plugin;
    }

    XFManager manager = plugin.getManager();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Map<String, String> regexList = manager.getRegex();
        for (String regex : regexList.keySet()) {
            message = message.replaceAll(regex, regexList.get(regex));
            event.setMessage(message);
        }
    }
}


