package it.omnisys.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static it.omnisys.plugin.SocialX.plugin;
import static it.omnisys.plugin.Utils.ColorUtils.color;

public class TwitchCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("socialx.command.twitch")) {

                p.sendMessage(color(plugin.getMessageConfig().getString("TwitchCommandMessage").replaceAll("%twitchLink%", plugin.getConfig().getString("TwitchChannel") )));
            } else {
                p.sendMessage(color(plugin.getMessageConfig().getString("NoPermsMessage")));
                if(plugin.getConfig().getBoolean("debug")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("socialx.alerts")) {
                            player.sendMessage(plugin.getMessageConfig().getString("AdminNotifyMessage").replaceAll("%player%", p.getName()).replaceAll("%command%", "/twitch"));
                        }
                    }
                }
            }
        } else {
            plugin.getServer().getConsoleSender().sendMessage(color(plugin.getMessageConfig().getString("ConsoleBocker")));
        }



        return true;
    }
}