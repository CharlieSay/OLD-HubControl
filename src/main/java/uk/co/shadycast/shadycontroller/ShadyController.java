package uk.co.shadycast.shadycontroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.shadycast.shadycontroller.Events.Chat;
import uk.co.shadycast.shadycontroller.Events.JoinLeave;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Storage.DB;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.pluginUtils;

public class ShadyController extends JavaPlugin {

    public static HashMap<String, SServer> Servers;
    public static HashMap<String, SPlayer> Players;
    public static String thisBungeeID;
    public static DateFormat dateFormat;

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        //If empty config stop load
        pluginUtils.setPlugin(this);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DB.init();
        Servers = new HashMap<String, SServer>();
        ArrayList<SServer> S = new ArrayList<SServer>(DB.getAllServers());
        for (SServer s : S) {
            Servers.put(s.getBungeeID(), s);
        }
        Players = new HashMap<String, SPlayer>();
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        int port = getServer().getPort();
        thisBungeeID = DB.getBungeeID(port);
        Msg.Console("This Server = " + thisBungeeID);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    /**
     * Returns a Shady player usable for DB access and updating as well as rank
     * checking
     *
     * @param p Any Player logged on to the server already
     * @return SPlayer An Shady Player
     */
    public static SPlayer getPlayer(Player p) {
        return Players.get(p.getName());
    }

    /**
     * Returns all Shady Server's usable for DB access and to get info from
     *
     * @return List<SServer> A list of all registered servers on the network
     */
    public static List<SServer> getAllServers() {
        return DB.getAllServers();
    }

    /**
     * Returns a Shady Server usable for DB access and to get info from
     *
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee
     * config
     * @return SServer A registered servers on the network
     */
    public static SServer getServer(String BungeeID) {
        return Servers.get(BungeeID);
    }

    /**
     * Returns the current Shady Server usable for DB access and to get info
     * from
     *
     * @return SServer The current registered server on the network
     */
    public static SServer getThisServer() {
        return Servers.get(thisBungeeID);
    }

    /**
     * Updates the specified Shady Servers Max player value
     *
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee
     * config
     * @param PlayerCount The Value for Max Players for the Shady Server
     */
    public static void updateMaxPlayers(String BungeeID, int PlayerCount) {
        DB.updateMaxPlayers(getServer(BungeeID), PlayerCount);
    }

    /**
     * Increases the specified Shady Servers Current player value
     *
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee
     * config
     * @param PlayerCount The Value To Increase The Current Players on the
     * Specified SServer by
     */
    private static void increaseCurPlayers(String BungeeID, int PlayerCount) {
        DB.increaseCurPlayers(getServer(BungeeID), PlayerCount);
    }

    /**
     * Gets the Stats for the specified gamemode from the database
     *
     * @param SPlayer The Player you wish to get stats for
     * @param game The name of the game you want stats for
     */
    private static void getGameStats(SPlayer SPlayer, String game) {
        DB.getGameStats(SPlayer, game);
    }

    /**
     * Sets the Stats for the specified gamemode from the database
     *
     * @param SPlayer The Player you wish to set stats for
     * @param game The name of the game you want to set stats for
     * @param stats The stats you want to set
     */
    private static void getGameStats(SPlayer SPlayer, String game, String stats) {
        DB.setGameStats(SPlayer, game, stats);
    }
}
