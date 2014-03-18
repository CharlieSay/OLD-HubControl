package uk.hubcontroller.Objects;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import uk.hubcontroller.Storage.Config;
import uk.hubcontroller.Utils.pluginUtils;

public class SSign {

    SServer server;
    Location loc;
    Sign s;
    
    public SSign(SServer s, Location l) {
        this.server = s;        
        this.loc = l;
        this.s = (Sign) l.getBlock().getState();
    }
    
    public SServer getSServer() {
        return this.server;
    }

    public Location getLocation() {
        return this.loc;
    }
    
    public void updateSign() {
        if (server.getStatus() == SStatus.Join) {
            s.setLine(3, " ");
            s.setLine(0, ChatColor.BLUE + "[" + server.getStatus().toString() + "]");
            s.setLine(1, ChatColor.GREEN + "" +ChatColor.BOLD + server.getName());
            s.setLine(2, ChatColor.AQUA + Integer.toString(server.getCurPlayers()) + " / " + Integer.toString(server.getMaxPlayers()));
        } else if (server.getStatus() == SStatus.InGame) {
            s.setLine(3, " ");
            s.setLine(0, ChatColor.YELLOW + "[" + server.getStatus().toString() + "]");
            s.setLine(1, ChatColor.GREEN + "" +ChatColor.BOLD + server.getName());
            s.setLine(2, ChatColor.AQUA + Integer.toString(server.getCurPlayers()) + " / " + Integer.toString(server.getMaxPlayers()));
        } else if (server.getStatus() == SStatus.Full) {
            s.setLine(3, " ");
            s.setLine(0, ChatColor.GOLD + "[" + server.getStatus().toString() + "]");
            s.setLine(1, ChatColor.GREEN + "" +ChatColor.BOLD + server.getName());
            s.setLine(2, ChatColor.AQUA + Integer.toString(server.getCurPlayers()) + " / " + Integer.toString(server.getMaxPlayers()));
        } else if (server.getStatus() == SStatus.Restarting) {
            s.setLine(2, ChatColor.RED + "" +ChatColor.BOLD + "RESTARTING!");
            s.setLine(1, ChatColor.DARK_RED + "################");
            s.setLine(3, ChatColor.DARK_RED + "################");
            s.setLine(0, " ");
        } else if (server.getStatus() == SStatus.Error) {
            s.setLine(3, " ");
            s.setLine(0, ChatColor.RED + "[" + server.getStatus().toString() + "]");
            s.setLine(1, ChatColor.GREEN + "" +ChatColor.BOLD + server.getName());
            s.setLine(2, ChatColor.AQUA + Integer.toString(server.getCurPlayers()) + " / " + Integer.toString(server.getMaxPlayers()));
        }
        s.update();
    }

    public void SaveSign() {
        FileConfiguration config = pluginUtils.getConfig();
        List<String> ls;
        if (config.isSet("TheHub.Signs")) {
            ls = (List<String>) config.getList("TheHub.Signs");
            ls.add(Config.locationToString(loc) + "," + server.getBungeeID());
        } else {
            ls = new ArrayList<String>();
            ls.add(Config.locationToString(loc) + "," + server.getBungeeID());
        }
        config.set("TheHub.Signs", ls);
        pluginUtils.getPlugin().saveConfig();
    }
}