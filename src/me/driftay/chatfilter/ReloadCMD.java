package me.driftay.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCMD implements CommandExecutor {
    private Main main;

    public ReloadCMD(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName ().equalsIgnoreCase ("chatfilter")) {
            if (!(sender.hasPermission (this.main.getConfig().getString("ChatFilter.bypass-permission")))) {
                sender.sendMessage ((ChatColor.translateAlternateColorCodes ('&', this.main.getConfig ().getString ("ChatFilter.no-perms"))));
            }
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase ("reload") && sender.hasPermission (this.main.getConfig().getString("ChatFilter.bypass-permission"))) {
                this.main.reloadConfig ();
                sender.sendMessage (ChatColor.translateAlternateColorCodes ('&', this.main.getConfig ().getString ("ChatFilter.reloaded")));
            }
        } else {
            sender.sendMessage (ChatColor.translateAlternateColorCodes ('&', this.main.getConfig ().getString ("ChatFilter.wrong-argument")));
        }
        return true;
    }
}


