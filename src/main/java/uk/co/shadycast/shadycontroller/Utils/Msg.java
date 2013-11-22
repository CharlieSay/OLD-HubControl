package uk.co.shadycast.shadycontroller.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Msg {

    public static void Console(String s){pluginUtils.getServer().getLogger().info("[Shady Controller] " + s);}
    public static void Player(String s,Player p){p.sendMessage("[Shady Controller] " + s);}
        public static void Spam(Player p){p.sendMessage(ChatColor.BOLD + "#~~~~~~~~~~~~~~~~~~~~~~~~~#");p.sendMessage("[Shady Controller] " + ChatColor.RED + "Please Do Not Spam!");p.sendMessage(ChatColor.BOLD + "#~~~~~~~~~~~~~~~~~~~~~~~~~#");}

    public static void All(String s){Bukkit.broadcastMessage("[Shady Controller] " + s);}
}