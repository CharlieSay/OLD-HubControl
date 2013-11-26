package uk.co.shadycast.shadycontroller.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
import uk.co.shadycast.shadycontroller.Objects.SStatus;
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
       if(evt.getBlock().getType() == Material.SIGN || evt.getBlock().getType() == Material.WALL_SIGN || evt.getBlock().getType() == Material.SIGN_POST){
           Location l = evt.getBlock().getLocation();
       if(!ShadyController.Signs.isEmpty()){
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
    }
    public static ArrayList<String> cd;
    @EventHandler
    public void Click(PlayerInteractEvent evt) {
        if (ShadyController.signsActive) {
            if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (evt.getClickedBlock().getType().equals(Material.WALL_SIGN) || evt.getClickedBlock().getType().equals(Material.SIGN)  || evt.getClickedBlock().getType() == Material.SIGN_POST) {
                    Location l = evt.getClickedBlock().getLocation();
                    if (ShadyController.Signs.containsKey(l)) {
                        if(!cd.contains(evt.getPlayer().getName())){
                        Player p = evt.getPlayer();
                        coolDown(p.getName());
                        SSign s = ShadyController.Signs.get(l);
                        if(!s.getSServer().getStatus().equals(SStatus.InGame)){
                            if(!s.getSServer().getStatus().equals(SStatus.Restarting)){
                                if(s.getSServer().getStatus().equals(SStatus.Full)){
                                    ShadyController.sendPlayer(p, s.getSServer());
                                }else{
                                    Msg.Player(ChatColor.RED + "This server is full donate to be able to join full games!", p); 
                                }
                            }else{
                               Msg.Player(ChatColor.RED + "Please wait, this server is restarting!", p); 
                            }
                        }else{
                            Msg.Player(ChatColor.RED + "Please wait, this game is in session!", p);
                        }
                    }else{
                            Msg.Spam(evt.getPlayer());
                        }
                 }
                }
            }
        }
    }
    public void coolDown(final String s) {
        cd.add(s);
        pluginUtils.getServer().getScheduler().runTaskLater(pluginUtils.getPlugin(), new Runnable() {
            public void run() {
                cd.remove(s);
            }
        }, 40L);
    }
}