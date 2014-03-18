package uk.hubcontroller.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Msg {

    public static void Console(String s){pluginUtils.getServer().getLogger().info("[The Hub] " + s);}
    public static void Player(String s,Player p){p.sendMessage("[The Hub] " + s);}
    public static void Spam(Player p){p.sendMessage(ChatColor.BOLD + "#~~~~~~~~~~~~~~~~~~~~~~~~~#");p.sendMessage("[The Hub] " + ChatColor.RED + "Please Do Not Spam!");p.sendMessage(ChatColor.BOLD + "#~~~~~~~~~~~~~~~~~~~~~~~~~#");}

    public static void All(String s){Bukkit.broadcastMessage("[The Hub] " + s);}
}