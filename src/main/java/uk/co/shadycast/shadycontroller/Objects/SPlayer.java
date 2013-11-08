package uk.co.shadycast.shadycontroller.Objects;

import java.util.Date;


public class SPlayer {
    String Name;
    String Rank;
    boolean Banned;
    Date FirstJoin;
    Date LatestJoin;
    
    public SPlayer(String N,String R,boolean B,Date FJ,Date LJ){
        this.Name = N;this.Rank = R;this.Banned = B;
        this.FirstJoin = FJ;this.LatestJoin = LJ;
    }
    
    public String getName(){return this.Name;}
    public String getRank(){return this.Rank;}
    public boolean getBanned(){return this.Banned;}
    public Date getFirstJoin(){return this.FirstJoin;}
    public Date getLatestJoin(){return this.LatestJoin;}
    
}