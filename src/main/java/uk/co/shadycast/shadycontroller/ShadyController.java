package uk.co.shadycast.shadycontroller;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.shadycast.shadycontroller.Cmds.Ban;
import uk.co.shadycast.shadycontroller.Cmds.CmdStopAll;
import uk.co.shadycast.shadycontroller.Cmds.Gamemode;
import uk.co.shadycast.shadycontroller.Cmds.Kick;
import uk.co.shadycast.shadycontroller.Cmds.Rank;
import uk.co.shadycast.shadycontroller.Events.Chat;
import uk.co.shadycast.shadycontroller.Events.JoinLeave;
import uk.co.shadycast.shadycontroller.Events.SignEvents;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Objects.SSign;
import uk.co.shadycast.shadycontroller.Storage.Config;
import uk.co.shadycast.shadycontroller.Storage.DB;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.pluginUtils;

public class ShadyController extends JavaPlugin {

    public static HashMap<String, SServer> Servers;
    public static HashMap<String, SPlayer> Players;
    public static HashMap<Location, SSign> Signs;
    public static String thisBungeeID;
    public static DateFormat dateFormat;
    public static DateFormat banDateFormat;
    public static boolean signsActive;

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        pluginUtils.setPlugin(this);
        Msg.Console("Plugin Set");
        Msg.Console("InIt");
        FileConfiguration config = this.getConfig();
        Msg.Console("Saving Config");
        this.saveDefaultConfig();
        Msg.Console("InIt Date Format");
        banDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Msg.Console("InIt Database");
        DB.init();
        Msg.Console("InIt Servers");
        Servers = new HashMap<String, SServer>();
        ArrayList<SServer> S = new ArrayList<SServer>(DB.getAllServers());
        for (SServer s : S) {
            Servers.put(s.getBungeeID(), s);
        }
        Msg.Console("InIt Players");
        Players = new HashMap<String, SPlayer>();
        Msg.Console("Config import");
        if (pluginUtils.getConfig().isSet("ShadyController.Signs")) {
            Signs = new HashMap<Location, SSign>(Config.importSigns());
            Msg.Console("Importing Signs");
            signsActive = true;
        } else {
            signsActive = false;
        }
        Msg.Console("Register Events");
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new SignEvents(), this);
        Msg.Console("InIt Servers");
        int port = getServer().getPort();
        thisBungeeID = DB.getBungeeID(port);
        Msg.Console("This Server = " + thisBungeeID);
        Msg.Console("Outgoing Plugin Channel");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        //Our Cmds
        Msg.Console("Register Commands");
        getCommand("Ban").setExecutor(new Ban());
        getCommand("Rank").setExecutor(new Rank());
        getCommand("Gamemode").setExecutor(new Gamemode());
        getCommand("Kick").setExecutor(new Kick());
        //Override
        getCommand("?").setExecutor(new CmdStopAll());
        getCommand("help").setExecutor(new CmdStopAll());
        getCommand("me").setExecutor(new CmdStopAll());
        //getCommand("pl").setExecutor(new CmdStopAll());
        //TODO Fix getCommand("plugins").setExecutor(new CmdStopAll());
        // Msg.All(Boolean.toString(DB.serverExsists(thisBungeeID)));
        // Msg.All(Boolean.toString(DB.serverExsists("asd")));
        Msg.Console("Prep Signs Loop");
        if (signsActive) {
            Msg.Console("Starting Sign Loop");
            signLoop();
        }
    }

    public void signLoop() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (SSign s : Signs.values()) {
                    DB.updateServer(s.getSServer());
                    s.updateSign();
                }
            }
        }, 0L, 100L);
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
     * Gets the Stats for the specified gamemode from the database
     *
     * @param SPlayer The Player you wish to get stats for
     * @param game The name of the game you want stats for
     */
    public static String getGameStats(SPlayer SPlayer, String game) {
        return DB.getGameStats(SPlayer, game);
    }

    /**
     * Sets the Stats for the specified gamemode from the database
     *
     * @param SPlayer The Player you wish to set stats for
     * @param game The name of the game you want to set stats for
     * @param stats The stats you want to set
     */
    public static void setGameStats(SPlayer SPlayer, String game, String stats) {
        DB.setGameStats(SPlayer, game, stats);
    }

    /**
     * Gets the Join Power for a specified SPlayer
     *
     * @param SP The specified SPlayer
     */
    public static int getJoinPower(SPlayer SP) {
       return DB.getJoinPower(SP.getRank());
    }

    /**
     * Gets the Rank Power for a specified SPlayer
     *
     * @param SP The specified SPlayer
     */
    public static int getRankPower(SPlayer SP) {
        return DB.getRankPower(SP.getRank());
    }

    /**
     * Checks if the specified server is currently loaded in the ShadyController
     *
     * @param BungeeID the ID for the server
     */
    public static boolean serverExsists(String BungeeID) {
        boolean b = false;
        if (Servers.containsKey(BungeeID)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }
    /**
     * Sends a player to the specified SServer
     *
     * @param P the Player to send
     * @param s the Target SServer
     */
    public static void sendPlayer(Player p,SServer s) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        
        try {
            out.writeUTF("Connect");
            out.writeUTF(s.getBungeeID()); 
        } catch (IOException e) {
            // Can never happen
        }
        p.sendPluginMessage(pluginUtils.getPlugin(), "BungeeCord", b.toByteArray());
    }
    /**
     * Sends a player to the Hub
     *
     * @param P the Player to send
     * @param s the Target SServer
     */
    public static void sendPlayerToHub(Player p) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        
        try {
            out.writeUTF("Connect");
            out.writeUTF("Hub1"); // Change When Multi-hub happens
        } catch (IOException e) {
            // Can never happen
        }
        p.sendPluginMessage(pluginUtils.getPlugin(), "BungeeCord", b.toByteArray());
    }
}
