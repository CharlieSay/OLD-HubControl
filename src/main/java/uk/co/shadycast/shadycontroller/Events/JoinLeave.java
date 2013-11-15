package uk.co.shadycast.shadycontroller.Events;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class JoinLeave implements Listener{
    
    @EventHandler
    public void Join(PlayerJoinEvent evt){
        Player p = evt.getPlayer();
        Date d = new Date();
        SPlayer sp;
        DB.addShadyPlayer(p.getName(), "Player", false, d, d, 0);
        sp = DB.getShadyPlayer(evt.getPlayer());
        ShadyController.Players.put(p.getName(), sp);
        DB.setPlayerLatestJoin(sp.getName(), d);
        DB.increaseCurPlayers(ShadyController.getThisServer(), 1);
    }
    
    @EventHandler
    public void Leave(PlayerQuitEvent evt){
        Player p = evt.getPlayer();
        SPlayer sp = ShadyController.getPlayer(p);
            if(ShadyController.Players.containsKey(sp.getName()))ShadyController.Players.remove(sp.getName());
         ShadyController.getThisServer().decreaseCurPlayers(1);
    } 
}