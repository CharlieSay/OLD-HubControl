package uk.co.shadycast.shadycontroller.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SServer;


public class Utils {

    
    public void SendTo(Player p,SServer s){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(s.getBungeeID()); // Target Server
        } catch (IOException e) {
            // Can never happen
        }
        p.sendPluginMessage(pluginUtils.getPlugin(), "BungeeCord", b.toByteArray());
    }
}