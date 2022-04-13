package it.omnisys.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static it.omnisys.plugin.SocialX.plugin;
import static it.omnisys.plugin.Utils.ColorUtils.color;

public class TiktokCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(plugin.getConfig().getBoolean("tiktok")) {
                if (p.hasPermission("socialx.command.tiktok")) {

                    p.sendMessage(color(plugin.getMessageConfig().getString("TiktokCommandMessage").replaceAll("%tiktokLink%", plugin.getConfig().getString("TikTokChannel"))));
                } else {
                    p.sendMessage(color(plugin.getMessageConfig().getString("NoPermsMessage")));

                    if (plugin.getConfig().getBoolean("debug")) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("socialx.alerts")) {
                                player.sendMessage(color(plugin.getMessageConfig().getString("AdminNotifyMessage").replaceAll("%player%", p.getName()).replaceAll("%command%", "/tiktok")));
                            }
                        }
                    }
                }
            } else {
                p.sendMessage(color(plugin.getMessageConfig().getString("CommandNotEnabled")));
            }
        } else if(sender instanceof ConsoleCommandSender) {
            plugin.getServer().getConsoleSender().sendMessage(color(plugin.getMessageConfig().getString("ConsoleBocker")));
        }



        return true;
    }
}