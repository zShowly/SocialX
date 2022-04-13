package it.omnisys.plugin;

import it.omnisys.plugin.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static it.omnisys.plugin.Utils.ColorUtils.color;

public final class SocialX extends JavaPlugin {

    public static SocialX plugin;

    private File messageConfigFile;
    private FileConfiguration messageConfig;

    HashMap<String, String> commandlist = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        
            plugin = this;

            saveDefaultConfig();
            getConfig().options().copyDefaults(true);


            getServer().getConsoleSender().sendMessage(color(
                    "    \n&b_____            _       __   _  __    \n" +
                    "   / ___/____  _____(_)___ _/ /  | |/ /   &aRunning Version &8" + getDescription().getVersion() + "\n" +
                    "   \\__ \\/ __ \\/ ___/ / __ `/ /   |   /  &aPlugin By &8" + getDescription().getAuthors() + "\n" +
                    "  ___/ / /_/ / /__/ / /_/ / /   /   |\n" +
                    " /____/\\____/\\___/_/\\__,_/_/   /_/|_|\n&r"));

            createMessageConfig();

            getCommand("youtube").setExecutor(new YouTubeCMD());
            commandlist.put("youtube", "Sends the youtube channel to the sender player");

            getCommand("twitch").setExecutor(new TwitchCMD());
            commandlist.put("twitch", "Sends the twitch channel to the sender player");

            getCommand("website").setExecutor(new WebsiteCMD());
            commandlist.put("website", "Sends the website link to the sender player");

            getCommand("tiktok").setExecutor(new TiktokCMD());
            commandlist.put("tiktok", "Sends the tiktok channel to the sender player");

            getCommand("store").setExecutor(new StoreCMD());
            commandlist.put("store", "Sends the store link to the sender player");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(color(
                "    &b_____            _       __   _  __    \n" +
                "   / ___/____  _____(_)___ _/ /  | |/ /   &cDisabled " + getDescription().getVersion() +"\n" +
                "   \\__ \\/ __ \\/ ___/ / __ `/ /   |   /  &aPlugin By &8" +   getDescription().getAuthors() + "\n" +
                "  ___/ / /_/ / /__/ / /_/ / /   /   |\n" +
                " /____/\\____/\\___/_/\\__,_/_/   /_/|_|\n&r"));
    }


    public FileConfiguration getMessageConfig() {
        return this.messageConfig;
    }

    private void createMessageConfig() {
        messageConfigFile = new File(getDataFolder(), "messages.yml");
        if (!messageConfigFile.exists()) {
            messageConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messageConfig = new YamlConfiguration();
        try {
            messageConfig.load(messageConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
