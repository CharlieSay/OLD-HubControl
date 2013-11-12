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


public class DB {
    //Statement st;
    // ResultSet rs;
    //st = con.createStatement();
    //rs = st.executeQuery("SHIZZ");
    public static Connection con;
    
    public static void init(){
    String url = "jdbc:mysql://localhost:3306/ShadyController";
    String User = "minecraft";
    String Pass = "cheeseBALLS123";
    
        try{
            con = DriverManager.getConnection(url, User, Pass);
        }catch(SQLException e){
            Msg.Console("[SQL ERROR] " +ChatColor.RED+ e);
        }
    
    }
    
    
    public static void updateServer(SServer ss){
        SServer s = ShadyController.Servers.get(ss.getBungeeID());
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers WHERE id = " + ss.getID());
            String Type = r.getObject("Type", String.class);
            String Name = r.getObject("Name", String.class);
            String Status = r.getObject("Status", String.class);
            int CurPlayers = r.getObject("CurPlayers", Integer.class);
            int MaxPlayers = r.getObject("MaxPlayers", Integer.class);
            String Bungeeid = r.getObject("BungeeID", String.class);
            int Port = r.getObject("Port", Integer.class);
            s.setAll(Type,Name,Status,CurPlayers,MaxPlayers,Bungeeid,Port);
        } catch (SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
    }
    
    public static List<SServer> getAllServers(){
    ArrayList<SServer> s = new ArrayList<SServer>();
    try {
            
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers");
            while(r.next()){
                int id = r.getObject("id", Integer.class);
                String Type = r.getObject("Type", String.class);
                String Name = r.getObject("Name", String.class);
                String Status = r.getObject("Status", String.class);
                int CurPlayers = r.getObject("CurPlayers", Integer.class);
                int MaxPlayers = r.getObject("MaxPlayers", Integer.class);
                String Bungeeid = r.getObject("BungeeID", String.class);
                int Port = r.getObject("Port", Integer.class);
                SServer ss = new SServer(id,Type,Name,Status,CurPlayers,MaxPlayers,Bungeeid,Port);
                s.add(ss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    return s;
    }
    
    public static void updateStatus(SServer s,String Status){
      try {
            Statement st = con.createStatement();
            st.execute("UPDATE Servers SET Status="+Status+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateCurPlayers(SServer s,int i){
    try {
            Statement st = con.createStatement();
            st.execute("UPDATE Servers SET CurPlayers="+i+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateMaxPlayers(SServer s,int i){
    try {
            Statement st = con.createStatement();
            st.execute("UPDATE Servers SET MaxPlayers="+i+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String getBungeeID(int port){
        String ID = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT BungeeID FROM Servers WHERE Port='" + port+"'");
            ID = r.getObject("BungeeID", String.class);
        }catch(SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        } 
      return ID;
    }
    
    public static SPlayer getShadyPlayer(Player p){
        SPlayer sp = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Users WHERE Name =" + p.getName());
            String Rank = r.getObject("Rank", String.class);
            boolean Banned = r.getObject("Banned", Boolean.class);
            Date FJ = r.getObject("FirstJoin", Date.class);
            Date LJ = r.getObject("LatestJoin", Date.class);
            int c = r.getObject("Coins", Integer.class);
            sp = new SPlayer(p.getName(),Rank,Banned,FJ,LJ,c);
        }catch(SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
    return sp;
    }
    
    public static void setPlayerRank(SPlayer s,String r){
    try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET Rank="+r+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void addShadyPlayer(String N,String R,boolean B,Date FJ,Date LJ,int c){
        try {
            Statement st = con.createStatement();
            Bukkit.broadcastMessage("SQL");
            st.execute("INSERT IGNORE INTO Users VALUES ('','"+N+"','"+R+"','"+B+"','"+ShadyController.dateFormat.format(FJ)+"','"+ShadyController.dateFormat.format(LJ)+"','"+c+"')");
            
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setPlayerBanned(SPlayer s,boolean b){
    try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET Banned="+Boolean.toString(b)+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setPlayerLatestJoin(String PName,Date d){
    try {
            Statement st = con.createStatement();
            st.execute("UPDATE Users SET LatestJoin="+ShadyController.dateFormat.format(d)+" WHERE Name="+PName);
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void addPlayerCoins(SPlayer s,int c){
    try {
            Statement st1 = con.createStatement();
            ResultSet r = st1.executeQuery("SELECT * FROM Users WHERE Name =" + s.getName());
            int coins = r.getObject("Coins", Integer.class);
            int CN = coins + c;
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Users SET Coins="+CN+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int getPlayerCoins(SPlayer s){
        int coins = 0;
    try {
            Statement st1 = con.createStatement();
            ResultSet r = st1.executeQuery("SELECT * FROM Users WHERE Name =" + s.getName());
            coins = r.getObject("Coins", Integer.class);
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    return coins ;
    }
    public static void getGameStats(SPlayer s,String game){
   // try {
            //Statement st = con.createStatement();
           // st.executeQuery("Select FROM Users SET LatestJoin="+d+" WHERE Name="+s.getName());
    //} catch (SQLException ex) {
         //   Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
}