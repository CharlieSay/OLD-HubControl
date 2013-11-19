package uk.co.shadycast.shadycontroller.Objects;


public enum SStatus {
    Join,InGame,Restarting,Error;
    public static SStatus getStatus(String s){
        SStatus st = SStatus.Error;
        if(s.equalsIgnoreCase(SStatus.Join.toString())){
            st = SStatus.Join;
        }else if(s.equalsIgnoreCase(SStatus.InGame.toString())){
            st = SStatus.InGame;
        }else if(s.equalsIgnoreCase(SStatus.Restarting.toString())){
            st = SStatus.Restarting;
        }
        return st;
    }
}