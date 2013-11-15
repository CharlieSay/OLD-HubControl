package uk.co.shadycast.shadycontroller.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.ShadyController;


public class Chat implements Listener{
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent evt){
        Player p = evt.getPlayer();
        SPlayer sp = ShadyController.getPlayer(p);
        String msg = evt.getMessage();
        
        String R = sp.getRank();
        if(R.equalsIgnoreCase("Owner")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.DARK_RED + R + ChatColor.BLACK + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.BLACK + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Manager")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.DARK_RED + R + ChatColor.BLACK + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.BLACK + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Dev")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.GREEN + R + ChatColor.BLACK + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.BLACK + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("Admin")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.GREEN + R + ChatColor.BLACK + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.BLACK + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("Mod")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.GOLD + R + ChatColor.BLACK + "] " 
                          + ChatColor.GOLD + p.getName() + ChatColor.BLACK + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("VIP")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.YELLOW + R + ChatColor.BLACK + "] " 
                          + ChatColor.YELLOW + p.getName() + ChatColor.BLACK + " : " + ChatColor.GOLD + msg);
        }else if(R.equalsIgnoreCase("DonatorP")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.AQUA + "Donator +" + ChatColor.BLACK + "] " 
                          + ChatColor.AQUA + p.getName() + ChatColor.BLACK + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Donator")){
            evt.setFormat(ChatColor.BLACK + "["+ChatColor.DARK_PURPLE + R + ChatColor.BLACK + "] " 
                          + ChatColor.DARK_PURPLE + p.getName() + ChatColor.BLACK + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Player")){
            evt.setFormat(ChatColor.GRAY + p.getName() + ChatColor.BLACK + " : " + ChatColor.GRAY + msg);
        }
    }

}