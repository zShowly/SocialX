package it.omnisys.plugin.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.commands.PlaceholderCommand;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static it.omnisys.plugin.SocialX.plugin;
import static it.omnisys.plugin.Utils.ColorUtils.color;

public class YouTubeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("socialx.command.youtube")) {
                p.sendMessage(color(plugin.getMessageConfig().getString("YoutubeCommandMessage").replaceAll("%youtubeLink%", plugin.getConfig().getString("YoutubeChannel") )));
            } else {
                p.sendMessage(color(plugin.getMessageConfig().getString("NoPermsMessage")));
                if(plugin.getConfig().getBoolean("debug")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("socialx.alerts")) {
                            player.sendMessage(plugin.getMessageConfig().getString("AdminNotifyMessage").replaceAll("%player%", p.getName()).replaceAll("%command%", "/youtube"));
                        }
                    }
                }
            }
        } else {
            plugin.getServer().getConsoleSender().sendMessage(color(plugin.getMessageConfig().getString("ConsoleBlocker")));
        }
        return true;
    }
}