package me.driftay.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Iterator;
import java.util.List;

public class WordsFilter implements Listener {
    private Main main;

    public WordsFilter(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        List<String> list = this.main.getConfig().getStringList("Muted-Words");
        Iterator stuff = list.iterator();

        while (stuff.hasNext()) {
            String s = (String) stuff.next();
            String stars = this.main.getConfig().getString("Replace-Symbol");
            for (int i = 0; i < s.length(); i++) {
                stars += this.main.getConfig().getString("Replace-Symbol");
            }
            e.setMessage(message.replace(s, stars));
            if (message.toLowerCase().contains(s.toLowerCase()) && !p.hasPermission(this.main.getConfig().getString("ChatFilter.bypass-permission"))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("ChatFilter.Message1")));
                e.setMessage(message.replace(s, stars));
                e.setCancelled(false);
                break;
            }
        }
    }
}
