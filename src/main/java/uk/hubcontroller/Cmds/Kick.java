package uk.hubcontroller.Cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.hubcontroller.Objects.SPlayer;
import uk.hubcontroller.HubController;
import uk.hubcontroller.Utils.Msg;


public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        SPlayer sp = HubController.getPlayer(p);
        if(cmd.getLabel().equalsIgnoreCase("kick")){
            if (sp.getRank().getRankPower() >= 7) {
                if(args.length == 1){
                    String kp = args[0];
                    if(Bukkit.getPlayer(kp).isOnline()){
                        SPlayer ksp = HubController.getPlayer(Bukkit.getPlayer(kp));
                        if(sp.getRank().getRankPower() > ksp.getRank().getRankPower()){
                            Bukkit.getPlayer(kp).kickPlayer("You were kicked from the server!");
                            //TODO Log Kick
                        }else{
                            Msg.Player("You Don't have permmission to kick that person", p);
                        }
                    }else{
                        Msg.Player("That player is not online", p);
                    }
                }else{
                  Msg.Player("Correct usage /kick <player>", p);
                }
            }    
         }
    return false;
    }

}