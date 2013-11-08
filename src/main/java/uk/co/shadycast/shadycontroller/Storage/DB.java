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
    
    
    public static void UpdateServer(SServer ss){
        SServer s = ShadyController.Servers.get(ss.getID());
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Servers WHERE id = " + ss.getID());
            String Type = r.getObject("Type", String.class);
            String Name = r.getObject("Name", String.class);
            String Status = r.getObject("Status", String.class);
            int CurPlayers = r.getObject("CurPlayers", Integer.class);
            int MaxPlayers = r.getObject("MaxPlayers", Integer.class);
            String Bungeeid = r.getObject("BungeeID", String.class);
            s.setAll(Type,Name,Status,CurPlayers,MaxPlayers,Bungeeid);
        } catch (SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
    }
    
    public static List<SServer> GetAllServers(){
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
                SServer ss = new SServer(id,Type,Name,Status,CurPlayers,MaxPlayers,Bungeeid);
                s.add(ss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    return s;
    }
    
    public static void UpdateStatus(SServer s,String Status){
      try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Servers SET Status="+Status+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void UpdateCurPlayers(SServer s,int i){
    try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Servers SET CurPlayers="+i+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void UpdateMaxPlayers(SServer s,int i){
    try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Servers SET MaxPlayers="+i+" WHERE id="+s.getID());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SPlayer GetShadyPlayer(Player p){
        SPlayer sp = null;
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM Users WHERE Name =" + p.getName());
            String Rank = r.getObject("Rank", String.class);
            boolean Banned = r.getObject("Banned", Boolean.class);
            Date FJ = r.getObject("FirstJoin", Date.class);
            Date LJ = r.getObject("LatestJoin", Date.class);
            sp = new SPlayer(p.getName(),Rank,Banned,FJ,LJ);
        }catch(SQLException ex) {
            Msg.Console("[SQL ERROR] " + ChatColor.RED + ex);
        }
    return sp;
    }
    
    public static void SetPlayerRank(SPlayer s,String r){
    try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Users SET Rank="+r+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void SetPlayerBanned(SPlayer s,boolean b){
    try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Users SET Banned="+Boolean.toString(b)+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void SetPlayerLatestJoin(SPlayer s,Date d){
    try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE Users SET LatestJoin="+d+" WHERE Name="+s.getName());
    } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}