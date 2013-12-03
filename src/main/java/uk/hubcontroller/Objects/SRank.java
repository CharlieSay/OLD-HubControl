package uk.hubcontroller.Objects;


public enum SRank { 
    Owner(10,10),
    Manager(9,9),
    Admin(8,8),
    Dev(8,8),
    Mod(7,7),
    VIP(0,6),
    DonatorP(0,5),
    Donator(0,4),
    Player(0,0);
    
    private final int RP;
    private final int JP; 
    
    
    SRank(int rp,int jp){this.RP = rp; this.JP = jp;}
    
    public int getJoinPower(){return this.JP;}
    public int getRankPower(){return this.RP;}
}