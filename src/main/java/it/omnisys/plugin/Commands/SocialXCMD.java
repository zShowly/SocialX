package it.omnisys.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static it.omnisys.plugin.SocialX.plugin;
import static it.omnisys.plugin.Utils.ColorUtils.color;
import static it.omnisys.plugin.SocialX.commandlist;

public class SocialXCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0) {
                p.sendMessage(color("&8[&b&nSocialX&8] &cBy Sgattix & GX_Regent (&b&nhttps://dsc.gg/socialx&8)"));
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if(p.hasPermission("socialx.command.reload")) {
                    Bukkit.getPluginManager().disablePlugin(plugin);
                    Bukkit.getPluginManager().enablePlugin(plugin);
                    p.sendMessage(color(plugin.getMessageConfig().getString("ConfigReloadedMessage")));
                } else {
                    p.sendMessage(color(plugin.getMessageConfig().getString("NoPermsMessage")));
                }
            } else if(args.length == 1 && args[0].equalsIgnoreCase("info")) {
                if(p.hasPermission("socialx.command.info")) {
                    p.sendMessage(color("&bThis server is running SocialX v" + plugin.getDescription().getVersion() + " on \n"
                            + plugin.getServer().getBukkitVersion() + "."));
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                p.sendMessage(color("&8&m-----------------&r &b&lSOCIAL X &8&m-----------------"));
                p.sendMessage(color(" "));
                p.sendMessage(color(" &b/socialx reload &7- Reloads the configs"));
                p.sendMessage(color(" &b/socialx info &7- Sends debug informations"));
                p.sendMessage(color(" &b/store &7- Sends the store link"));
                p.sendMessage(color(" &b/tiktok &7- Sends the tiktok channel"));
                p.sendMessage(color(" &b/twitch &7- Sends the twitch channel"));
                p.sendMessage(color(" &b/website &7- Sends the website link"));
                p.sendMessage(color(" &b/youtube &7- Sends the youtube channel"));
            }
        }


        return true;
    }
}
