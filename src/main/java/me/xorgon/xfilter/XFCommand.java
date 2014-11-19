package me.xorgon.xfilter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * XFilter commands class.
 */
public class XFCommand implements CommandExecutor {
    XFilter plugin = XFilter.getInstance();
    XFManager manager = plugin.getManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        xFilter(sender, args);
        return true;
    }

    private boolean xFilter(CommandSender sender, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("setCharacter")) {
                if (sender instanceof Player && sender.hasPermission("xf.setchar") || sender.hasPermission("xf.*")) {
                    if (args.length == 2) {
                        String character = args[1];
                        manager.setCharacter(character);
                        sender.sendMessage(ChatColor.GREEN + "Replacement character set to: '" + ChatColor.RED + character + ChatColor.GREEN + "'");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter setCharacter <Replacement character>");
                    }
                }
            }
            if (args[0].equalsIgnoreCase("addWord")) {
                if (sender instanceof Player && sender.hasPermission("xf.add") || sender.hasPermission("xf.*")) {
                    if (args.length == 2) {
                        manager.addWord(args[1]);
                        sender.sendMessage(ChatColor.GREEN + "Word added.");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter addWord <word>");
                    }
                }
            }
            if (args[0].equalsIgnoreCase("removeWord")) {
                if (sender instanceof Player && sender.hasPermission("xf.remove") || sender.hasPermission("xf.*")) {
                    if (args.length == 2) {
                        if (manager.removeWord(args[1])) {
                            sender.sendMessage(ChatColor.GREEN + "Word removed.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "That word isn't on the list.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter removeWord <word>");
                    }
                }
            }
            if (args[0].equalsIgnoreCase("addFilter")) {
                if (sender instanceof Player && sender.hasPermission("xf.add") || sender.hasPermission("xf.*")) {
                    if (args.length == 3) {
                        manager.addFilter(args[1], Integer.parseInt(args[2]));
                        sender.sendMessage(ChatColor.GREEN + "Filter added.");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter addFilter <filter> <number of replacement characters>");
                    }
                }
            }
            if (args[0].equalsIgnoreCase("removeFilter")) {
                if (sender instanceof Player && sender.hasPermission("xf.remove") || sender.hasPermission("xf.*")) {
                    if (args.length == 2) {
                        if (manager.removeFilter(args[1])) {
                            sender.sendMessage(ChatColor.GREEN + "Filter removed.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "That Filter isn't on the list.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter removeFilter <filter>");
                    }
                }
            }
            if (args[0].equalsIgnoreCase("list")){
                if (sender instanceof Player && sender.hasPermission("xf.list") || sender.hasPermission("xf.*")) {
                    String words = ChatColor.GREEN + "Filtered words: " + ChatColor.RED;
                    for (String word : manager.getWords()) {
                        words = words.concat(word + " ");
                    }
                    String filters = ChatColor.GREEN + "Custom Filters: " + ChatColor.RED;
                    for (String filter : manager.getFilters().keySet()) {
                        filters = filters.concat(filter + " ");
                    }
                    sender.sendMessage(words);
                    sender.sendMessage(filters);
                }
            }
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Correct usage: /xfilter <addWord:removeWord:addFilter:removeFilter:list:setCharacter>");
            return true;
        }
    }
}
