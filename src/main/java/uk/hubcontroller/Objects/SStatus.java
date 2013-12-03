package uk.hubcontroller.Objects;


public enum SStatus {
    Join,InGame,Full,Restarting,Error;
    public static SStatus getStatus(String s){
        SStatus st = SStatus.Error;
        if(s.equalsIgnoreCase(SStatus.Join.toString())){
            st = SStatus.Join;
        }else if(s.equalsIgnoreCase(SStatus.InGame.toString())){
            st = SStatus.InGame;
        }else if(s.equalsIgnoreCase(SStatus.Restarting.toString())){
            st = SStatus.Restarting;
        }else if(s.equalsIgnoreCase(SStatus.Full.toString())){
            st = SStatus.Full;
        }
        return st;
    }
}