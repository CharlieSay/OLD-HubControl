package uk.co.shadycast.shadycontroller.Events;

import java.util.Date;
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
        //SServer s = ShadyController.getThisServer();
        //int ps = s.getCurPlayers();
        //int cp = ps++;
        //s.setCurPlayers(cp);
        
        Player p = evt.getPlayer();
        Date d = new Date();
        
        DB.addShadyPlayer(p.getName(), "Player", false, d, d, 0);
        SPlayer sp = DB.getShadyPlayer(evt.getPlayer());
        ShadyController.Players.put(p.getName(), sp);
    }
    
    @EventHandler
    public void Leave(PlayerQuitEvent evt){
        Player p = evt.getPlayer();
        Date d = new Date();
        SPlayer sp = ShadyController.getPlayer(p);
        DB.setPlayerLatestJoin(sp.getName(), d);
        ShadyController.Players.remove(sp.getName());
        //SServer s = ShadyController.getThisServer();
        //int ps = s.getCurPlayers();
        //int cp = ps--;
       // s.setCurPlayers(cp);
    } 
}