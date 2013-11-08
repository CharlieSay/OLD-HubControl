package uk.co.shadycast.shadycontroller.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class Join implements Listener{
    
    @EventHandler
    public void login(PlayerLoginEvent evt){
        SServer s = ShadyController.getThisServer();
        int ps = s.getCurPlayers();
        int cp = ps++;
        s.setCurPlayers(cp);
    }
    
    @EventHandler
    public void Join(PlayerJoinEvent evt){
        Player p = evt.getPlayer();
        SPlayer sp = DB.GetShadyPlayer(evt.getPlayer());
        ShadyController.Players.put(p.getName(), sp);
        
    }
}
