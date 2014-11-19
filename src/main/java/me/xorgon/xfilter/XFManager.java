package me.xorgon.xfilter;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * XFilter words management class.
 */
public class XFManager {
    private final XFilter plugin;
    private List<String> words;
    private Map<String,Integer> filters;
    private Map<String,String> regex;
    private YamlConfiguration config;
    private String character;
    File file;

    public XFManager(XFilter plugin) {
        this.plugin = plugin;
        words = new ArrayList<>();
        filters = new HashMap<>();
        regex = new HashMap<>();
        file = new File(plugin.getDataFolder(), "filter.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadWords(){
        for (String word : words) {
            String[] split = word.split("");
            String wordRegex = "";
            for (String s : split) {
                if (!(s.equals(""))){
                    wordRegex = wordRegex.concat(s).concat("+");
                }
            }
            String replacement = "";
            for (int n = 1; n <= word.length(); n++) {
                replacement = replacement.concat(character);
            }
            regex.put(wordRegex, replacement);
        }
    }

    public void loadFilters(){
        for (String filter : filters.keySet()){
            String replacement = "";
            for (int n = 1; n <= filters.get(filter); n++) {
                replacement = replacement.concat(character);
            }
            regex.put(filter, replacement);
        }
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
        regex = new HashMap<>();
        loadWords();
        loadFilters();
    }

    public void setWords(List<String> filter) {
        this.words = filter;
    }

    public void addWord(String word){
        if (!words.contains(word)){
            words.add(word);
            regex = new HashMap<>();
            loadWords();
            loadFilters();
        }
    }

    public boolean removeWord(String word){
        if (words.contains(word)){
            words.remove(word);
            regex = new HashMap<>();
            loadWords();
            loadFilters();
            return true;
        } else {
            return false;
        }
    }

    public void addFilter(String filter, Integer integer){
        if (!filters.containsKey(filter)){
            filters.put(filter, integer);
            regex = new HashMap<>();
            loadWords();
            loadFilters();
        }
    }

    public boolean removeFilter(String filter){
        if (filters.containsKey(filter)){
            filters.remove(filter);
            regex = new HashMap<>();
            loadWords();
            loadFilters();
            return true;
        } else {
            return false;
        }
    }

    public List<String> getWords() {
        return words;
    }

    public Map<String, Integer> getFilters() {
        return filters;
    }

    public Map<String, String> getRegex() {
        return regex;
    }

    public void load(){
        character = config.getString("character");
        words = config.getStringList("words");
        loadWords();
        Map<String, Object> tempFilters = config.getConfigurationSection("filters").getValues(false);
        for (String s : tempFilters.keySet()) {
            filters.put(s,(Integer)tempFilters.get(s));
        }
        loadFilters();
    }

    public void save(){
        config = new YamlConfiguration();
        config.set("character", character);
        config.set("words", words);
        config.createSection("filters", filters);
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
