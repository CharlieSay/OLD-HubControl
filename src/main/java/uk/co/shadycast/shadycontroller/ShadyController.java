package uk.co.shadycast.shadycontroller;

import uk.co.shadycast.shadycontroller.Objects.SServer;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class ShadyController extends JavaPlugin{
    public static HashMap<Integer,SServer> Servers;
    public static HashMap<String,SPlayer> Players;
    
    @Override
    public void onDisable(){
        
    }
    
    @Override
    public void onEnable(){
        //If empty config stop load
        Servers = new HashMap<Integer,SServer>();
        Players = new HashMap<String,SPlayer>();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        DB.init();
    }
    
    /**
     * Returns a Shady player usable for DB access and updating as well as rank checking
     * @param p Any Player logged on to the server already
     * @return SPlayer An Shady Player 
     */
    public static SPlayer getSPlayer(Player p){return Players.get(p.getDisplayName());}
    //public static SServer getSServer ();
    public static List<SServer> GetAllServers(){return DB.GetAllServers();}
}