package uk.co.shadycast.shadycontroller.Objects;

import java.util.Date;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class SPlayer {
    String Name;
    String Rank;
    Date FirstJoin;
    Date LatestJoin;
    int Coins;
            
    public SPlayer(String N,String R,Date FJ,Date LJ,int c){
        this.Name = N;this.Rank = R;this.FirstJoin = FJ;
        this.LatestJoin = LJ;this.Coins = c;
    }
    
    public String getName(){return this.Name;}
    public String getRank(){return this.Rank;}
    public Date getFirstJoin(){return this.FirstJoin;}
    public Date getLatestJoin(){return this.LatestJoin;}
    public int getCoins(){return this.Coins;}
    
    public void setRank(String r){this.Rank = r;DB.setPlayerRank(this, Rank);}
    public void setBanned(boolean b){DB.setPlayerBanned(this, true);}
    public void setLatestJoin(Date d){this.LatestJoin = d; DB.setPlayerLatestJoin(getName(), LatestJoin);}
    public void addCoins(int c){DB.addPlayerCoins(this, c);DB.getPlayerCoins(this);}
}