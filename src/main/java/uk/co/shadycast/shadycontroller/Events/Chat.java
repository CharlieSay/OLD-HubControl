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
        if(ShadyController.ChatActive){
        Player p = evt.getPlayer();
        SPlayer sp = ShadyController.getPlayer(p);
        String msg = evt.getMessage();
        
        String R = sp.getRank();
        if(R.equalsIgnoreCase("Owner")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_RED + ChatColor.BOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.WHITE + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Manager")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_RED + ChatColor.BOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.WHITE + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Dev")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GREEN + R + ChatColor.WHITE + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("Admin")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GREEN + R + ChatColor.WHITE + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("Mod")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.GOLD + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R.equalsIgnoreCase("VIP")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.YELLOW + R + ChatColor.WHITE + "] " 
                          + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + " : " + ChatColor.GOLD + msg);
        }else if(R.equalsIgnoreCase("DonatorP")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.AQUA + "Donator +" + ChatColor.WHITE + "] " 
                          + ChatColor.AQUA + p.getName() + ChatColor.WHITE + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Donator")){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_PURPLE + R + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_PURPLE + p.getName() + ChatColor.WHITE + " : " + ChatColor.BLUE + msg);
        }else if(R.equalsIgnoreCase("Player")){
            evt.setFormat(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " : " + ChatColor.GRAY + msg);
        }
      }
    }

}