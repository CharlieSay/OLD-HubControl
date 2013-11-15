package uk.co.shadycast.shadycontroller.Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.Utils;

public class DB {
    //Statement st;
    // ResultSet rs;
    //st = con.createStatement();
    //rs = st.executeQuery("SHIZZ");

    public static Connection con;

    public static void init() {
        String url = "jdbc:mysql://localhost:3306/ShadyController";
        String User = "minecraft";
        String Pass = "cheeseBALLS123";

        try {
            con = DriverManager.getConnection(url, User, Pass);
        } catch (SQLException e) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + e);
        }

    }

    public static void updateServer(SServer ss) {
        SServer s = ShadyController.Servers.get(ss.getBungeeID());
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers WHERE BungeeID = '" + ss.getBungeeID() + "'");
            if (r.next()) {
                String Type = r.getObject("Type", String.class);
                String Name = r.getObject("Name", String.class);
                String Status = r.getObject("Status", String.class);
                int CurPlayers = r.getObject("CurPlayers", Integer.class);
                int MaxPlayers = r.getObject("MaxPlayers", Integer.class);
                String Bungeeid = r.getObject("BungeeID", String.class);
                int Port = r.getObject("Port", Integer.class);
                s.setAll(Type, Name, Status, CurPlayers, MaxPlayers, Bungeeid, Port);
            }
        } catch (SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
    }

    public static List<SServer> getAllServers() {
        ArrayList<SServer> s = new ArrayList<SServer>();
        try {

            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers");
            while (r.next()) {
                String Type = r.getString(2);
                String Name = r.getString(3);
                String Status = r.getString(4);
                int CurPlayers = r.getInt(5);
                int MaxPlayers = r.getInt(6);
                String Bungeeid = r.getString(7);
                int Port = r.getInt(8);
                SServer ss = new SServer(Type, Name, Status, CurPlayers, MaxPlayers, Bungeeid, Port);
                s.add(ss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static void updateStatus(SServer s, String Status) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE Servers SET Status= '" + Status + "' WHERE BungeeID='" + s.getBungeeID() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void increaseCurPlayers(SServer s, int i) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE Servers SET CurPlayers = CurPlayers + " + i + " WHERE BungeeID= '" + s.getBungeeID() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void decreaseCurPlayers(SServer s, int i) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE Servers SET CurPlayers = CurPlayers - " + i + " WHERE BungeeID= '" + s.getBungeeID() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateMaxPlayers(SServer s, int i) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE Servers SET MaxPlayers= " + i + " WHERE BungeeID= '" + s.getBungeeID() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getBungeeID(int port) {
        String ID = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers WHERE Port=" + port);
            if (r.next()) {
                ID = r.getString("BungeeID");
            }
        } catch (SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
        return ID;
    }

    public static SPlayer getShadyPlayer(Player p) {
        SPlayer sp = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Users WHERE Name = '" + p.getName() + "'");
            if (r.next()) {
                String Rank = r.getString(3);
                int Banned = r.getInt(4);
                Date FJ = r.getDate(5);
                Date LJ = r.getDate(6);
                int c = r.getInt(7);
                sp = new SPlayer(p.getName(), Rank, Utils.sqlToJava(Banned), FJ, LJ, c);
            }
        } catch (SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
        return sp;
    }

    public static void setPlayerRank(SPlayer s, String r) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET Rank=" + r + " WHERE Name=" + s.getName());
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addShadyPlayer(String N, String R, boolean B, Date FJ, Date LJ, int c) {
        try {
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();

            ResultSet rs = st2.executeQuery("SELECT * FROM Users WHERE Name = '" + N + "'");
            if (!rs.next()) {
                Msg.All("Welcome " + N + " to the server for the first time everyone!");
                st.execute("INSERT INTO Users (Name,Rank,Banned,FirstJoin,LatestJoin,Coins) VALUES ('" + N + "','" + R + "','" + Utils.javaToSql(B) + "','" + ShadyController.dateFormat.format(FJ) + "','" + ShadyController.dateFormat.format(LJ) + "','" + c + "')");
            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setPlayerBanned(SPlayer s, boolean b) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET Banned=" + Boolean.toString(b) + " WHERE Name=" + s.getName());
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setPlayerLatestJoin(String PName, Date d) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET LatestJoin='" + ShadyController.dateFormat.format(d) + "' WHERE Name='" + PName + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addPlayerCoins(SPlayer s, int c) {
        try {
            Statement st1 = con.createStatement();
            ResultSet r = st1.executeQuery("SELECT * FROM Users WHERE Name =" + s.getName());
            int coins = r.getObject("Coins", Integer.class);
            int CN = coins + c;
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Users SET Coins=" + CN + " WHERE Name=" + s.getName());
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getPlayerCoins(SPlayer s) {
        int coins = 0;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Users WHERE Name =" + s.getName());
            if (r.next()) {
                coins = r.getObject("Coins", Integer.class);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coins;
    }

    public static String getGameStats(SPlayer s, String game) {
        String stats = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("Select " + game + " FROM Users WHERE Name=" + s.getName());
            if (r.next()) {
                if (!r.getString(game).isEmpty()) {
                    stats = r.getString(game);
                } else {
                    stats = null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stats;
    }

    public static void setGameStats(SPlayer s, String game,String stats) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET "+game+"='" + stats + "' WHERE Name='" + s.getName() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}