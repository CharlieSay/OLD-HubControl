package uk.hubcontroller.Objects;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.hubcontroller.Storage.DB;


public class SPlayer {
    String Name;
    SRank Rank;
    Date FirstJoin;
    Date LatestJoin;
    int Coins;
            
    public SPlayer(String N,SRank R,Date FJ,Date LJ,int c){
        this.Name = N;this.Rank = R;this.FirstJoin = FJ;
        this.LatestJoin = LJ;this.Coins = c;
    }
    
    public String getName(){return this.Name;}
    public SRank getRank(){return this.Rank;}
    public Date getFirstJoin(){return this.FirstJoin;}
    public Date getLatestJoin(){return this.LatestJoin;}
    public int getCoins(){return this.Coins;}
    public Player getPlayer(){return Bukkit.getPlayer(getName());}
    
    public void setRank(SRank r){this.Rank = r;DB.setPlayerRank(this, Rank);}
    public void setBanned(boolean b){DB.setPlayerBanned(this, true);}
    public void setLatestJoin(Date d){this.LatestJoin = d; DB.setPlayerLatestJoin(getName(), LatestJoin);}
    public void addCoins(int c){DB.addPlayerCoins(this, c);DB.getPlayerCoins(this);}
}