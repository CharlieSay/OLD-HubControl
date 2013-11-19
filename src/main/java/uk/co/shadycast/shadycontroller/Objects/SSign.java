package uk.co.shadycast.shadycontroller.Objects;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import uk.co.shadycast.shadycontroller.Storage.Config;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.pluginUtils;

public class SSign {

    SServer server;
    Location loc;
    Sign s;
    
    public SSign(SServer s, Location l) {
        this.server = s;        
        this.loc = l;
        this.s = (Sign) l.getBlock().getState();
        SaveSign();
    }
    
    public SServer getSServer() {
        return this.server;
    }

    public Location getLocation() {
        return this.loc;
    }
    
    public void updateSign() {
        if (server.getStatus() == SStatus.Join) {
            s.setLine(0, ChatColor.BLUE + "[" + server.getStatus().toString() + "]");
        } else if (server.getStatus() == SStatus.InGame) {
            s.setLine(0, ChatColor.RED + "[" + server.getStatus().toString() + "]");
        } else if (server.getStatus() == SStatus.Restarting) {
            s.setLine(0, ChatColor.RED + "[" + server.getStatus().toString() + "]");
        } else if (server.getStatus() == SStatus.Error) {
            s.setLine(0, ChatColor.RED + "[" + server.getStatus().toString() + "]");
        }
        s.setLine(1, ChatColor.YELLOW + server.getName());
        s.setLine(2, ChatColor.AQUA + Integer.toString(server.getCurPlayers()) + " / " + Integer.toString(server.getMaxPlayers()));
        s.update();
    }

    private void SaveSign() {
        FileConfiguration config = pluginUtils.getConfig();
        List<String> ls;
        if (config.isSet("ShadyController.Signs")) {
            ls = (List<String>) config.getList("ShadyController.Signs");
            ls.add(Config.locationToString(loc) + "," + server.getBungeeID());
        } else {
            ls = new ArrayList<String>();
            ls.add(Config.locationToString(loc) + "," + server.getBungeeID());
        }
        config.set("ShadyController.Signs", ls);
        pluginUtils.getPlugin().saveConfig();
    }
}