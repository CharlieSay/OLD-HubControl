package uk.co.shadycast.shadycontroller.Events;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import uk.co.shadycast.shadycontroller.Objects.SSign;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.Config;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.pluginUtils;

public class SignEvents implements Listener {

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
                    if (ShadyController.signsActive) {
                        ShadyController.Signs.put(evt.getBlock().getLocation(), ssign);
                    } else {
                        ShadyController.Signs = new HashMap<Location, SSign>();
                        ShadyController.Signs.put(evt.getBlock().getLocation(), ssign);
                    }
                    ssign.SaveSign();
                } else {
                    Msg.Player("That server does not exsist", p);
                }
            } else {
                Msg.Player("You have to put the server name on the 2nd line", p);
            }
        }

    }

    @EventHandler
    public void BBreak(BlockBreakEvent evt) {
        Location l = evt.getBlock().getLocation();
       if(evt.getBlock() instanceof Sign){
        if (ShadyController.Signs.containsKey(l)) {
            FileConfiguration config = pluginUtils.getConfig();
            List<String> ls;
            SSign s = ShadyController.Signs.get(l);

            if (config.isSet("ShadyController.Signs")) {
                ls = (List<String>) config.getList("ShadyController.Signs");
                ls.remove(Config.locationToString(l) + "," + s.getSServer().getBungeeID());
                config.set("ShadyController.Signs", ls);
                pluginUtils.getPlugin().saveConfig();
                ShadyController.Signs.remove(l);
                Msg.Player("Sign Removed", evt.getPlayer());
            }
        }
       }
    }

    @EventHandler
    public void Click(PlayerInteractEvent evt) {
        if (ShadyController.signsActive) {
            if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (evt.getClickedBlock().getType().equals(Material.WALL_SIGN) || evt.getClickedBlock().getType().equals(Material.SIGN)  || evt.getClickedBlock().getType() == Material.SIGN_POST) {
                    Location l = evt.getClickedBlock().getLocation();
                    if (ShadyController.Signs.containsKey(l)) {
                        Player p = evt.getPlayer();
                        SSign s = ShadyController.Signs.get(l);
                        ShadyController.sendPlayer(p, s.getSServer());
                    }
                }
            }
        }
    }
}