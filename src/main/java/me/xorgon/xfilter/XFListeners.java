package me.xorgon.xfilter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<Pattern, String> regexList = manager.getRegex();
        // TODO: Optimize this.
        List<Pattern> sorted = regexList.keySet().stream().sorted(Comparator.comparingInt(o -> -o.pattern().length())).collect(Collectors.toList());
        for (Pattern regex : sorted) {
            message = regex.matcher(message).replaceAll(regexList.get(regex));
        }
        event.setMessage(message);
    }
}


