package uk.co.shadycast.shadycontroller.Cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.DB;
import uk.co.shadycast.shadycontroller.Utils.Msg;


public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        SPlayer sp = ShadyController.getPlayer(p);
        if(cmd.getLabel().equalsIgnoreCase("kick")){
            if (DB.getRankPower(sp.getRank()) >= 7) {
                if(args.length == 2){
                    String kp = args[0];
                    String r = args[1];
                    if(Bukkit.getPlayer(kp).isOnline()){
                        SPlayer ksp = ShadyController.getPlayer(p);
                        if(DB.getRankPower(sp.getRank()) > DB.getRankPower(ksp.getRank())){
                            Bukkit.getPlayer(kp).kickPlayer(r);
                            //TODO Log Kick
                        }else{
                            Msg.Player("You Don't have permmission to kick that person", p);
                        }
                    }else{
                        Msg.Player("That player is not online", p);
                    }
                }else{
                  Msg.Player("Correct usage /kick <player> <reason>", p);
                }
            }    
         }
    return false;
    }

}