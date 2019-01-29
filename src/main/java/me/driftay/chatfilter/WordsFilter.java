package me.driftay.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Iterator;

public class WordsFilter implements Listener {
    private Main main;

    public WordsFilter(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        String message = e.getMessage();
        FileConfiguration config = this.main.getConfig();

        boolean sendWarning = false;
        boolean bypassPerm = p.hasPermission(config.getString("ChatFilter.bypass-permission"));

        char star = config.getString("Replace-Symbol").charAt(0);
        Iterator<String> stuff = config.getStringList("Muted-Words").iterator();
        while (stuff.hasNext()) {
            String str = stuff.next();
            if (message.toLowerCase().contains(str.toLowerCase()) && !bypassPerm) {
                message = message.replace(str, new String(new char[str.length()]).replace('\0', star));
                if (!sendWarning) {
                    sendWarning = true;
                }
            }
        }

        if (sendWarning)
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("ChatFilter.Warning-Message")));
        e.setMessage(message);

    }
}
