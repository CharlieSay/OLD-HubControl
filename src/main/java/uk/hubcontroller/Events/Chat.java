package uk.hubcontroller.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import uk.hubcontroller.Objects.SPlayer;
import uk.hubcontroller.Objects.SRank;
import uk.hubcontroller.HubController;


public class Chat implements Listener{
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent evt){
        if(HubController.ChatActive){
        Player p = evt.getPlayer();
        SPlayer sp = HubController.getPlayer(p);
        String msg = evt.getMessage();
        
        SRank R = sp.getRank();
        
        if(R == SRank.Owner){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_RED + ChatColor.BOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.WHITE + " : " + ChatColor.WHITE + msg);
        }else if(R == SRank.Manager){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_RED + ChatColor.BOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_RED + p.getName() + ChatColor.WHITE + " : " + ChatColor.WHITE + msg);
        }else if(R == SRank.Dev){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GREEN + R + ChatColor.WHITE + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R == SRank.Admin){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GREEN + R + ChatColor.WHITE + "] " 
                          + ChatColor.GREEN + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R == SRank.Mod){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.GOLD + R + ChatColor.WHITE + "] " 
                          + ChatColor.GOLD + p.getName() + ChatColor.WHITE + " : " + ChatColor.DARK_GREEN + msg);
        }else if(R == SRank.VIP){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.YELLOW + R + ChatColor.WHITE + "] " 
                          + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + " : " + ChatColor.GOLD + msg);
        }else if(R == SRank.DonatorP){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.DARK_AQUA + "Donator +" + ChatColor.WHITE + "] " 
                          + ChatColor.DARK_AQUA + p.getName() + ChatColor.WHITE + " : " + ChatColor.AQUA + msg);
        }else if(R == SRank.Donator){
            evt.setFormat(ChatColor.WHITE + "["+ChatColor.BLUE + R + ChatColor.WHITE + "] " 
                          + ChatColor.BLUE + p.getName() + ChatColor.WHITE + " : " + ChatColor.AQUA + msg);
        }else if(R == SRank.Player){
            evt.setFormat(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " : " + ChatColor.GRAY + msg);
        }
      }
    }

}