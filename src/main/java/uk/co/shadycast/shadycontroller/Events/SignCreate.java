package uk.co.shadycast.shadycontroller.Events;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import uk.co.shadycast.shadycontroller.Objects.SSign;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Utils.Msg;

public class SignCreate implements Listener {

    @EventHandler
    public void SignChange(SignChangeEvent evt) {
        Player p = evt.getPlayer();
        if (evt.getLine(0).equalsIgnoreCase("[SSign]")) {
            if (!evt.getLine(1).isEmpty()) {
                if (ShadyController.serverExsists(evt.getLine(1))) {
                    Msg.Player("Creating Sign!", p);
                    SServer ss;
                    ss = ShadyController.getServer(evt.getLine(1));
                    SSign ssign = new SSign(ss, evt.getBlock().getLocation());
                    if(ShadyController.signsActive){
                        ShadyController.Signs.put(evt.getBlock().getLocation(), ssign);
                    }else{
                        ShadyController.Signs = new HashMap<Location,SSign>();
                        ShadyController.Signs.put(evt.getBlock().getLocation(), ssign);
                    }
                } else {
                    Msg.Player("That server does not exsist", p);
                }
            } else {
                Msg.Player("You have to put the server name on the 2nd line", p);
            }
        }

    }
}