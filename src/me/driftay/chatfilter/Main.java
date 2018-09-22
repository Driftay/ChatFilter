package me.driftay.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    ConsoleCommandSender Console = Bukkit.getConsoleSender();

    public void onEnable() {
        this.Console.sendMessage("ChatFilter Enabled");
        this.Console.sendMessage("Author: Driftay");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new WordsFilter(this),this);
        this.getCommand("chatfilter").setExecutor(new ReloadCMD(this));
    }
}
