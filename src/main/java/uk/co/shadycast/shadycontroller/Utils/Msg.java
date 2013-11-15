package uk.co.shadycast.shadycontroller.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class Msg {

    public static void Console(String s){pluginUtils.getServer().getLogger().info("[Shady Controller] " + s);}
    public static void Player(String s,Player p){p.sendMessage("[Shady Controller] " + s);}
    public static void All(String s){Bukkit.broadcastMessage("[Shady Controller] " + s);}
}