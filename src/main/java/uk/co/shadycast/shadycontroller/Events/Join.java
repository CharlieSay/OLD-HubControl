package uk.co.shadycast.shadycontroller.Events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class Join implements Listener{
    
    @EventHandler
    public void Join(PlayerJoinEvent evt){
        SPlayer sp = DB.GetShadyPlayer(evt.getPlayer());
    }
}
