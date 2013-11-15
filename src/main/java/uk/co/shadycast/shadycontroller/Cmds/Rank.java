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


public class Rank implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
    Player p = (Player) sender;
    SPlayer sp = ShadyController.getPlayer(p);
    // /Rank <User> <Rank>
    Msg.All(sp.getRank());
    if(DB.getRankPower(sp.getRank()) >= 8){
        if (cmd.getLabel().equalsIgnoreCase("Rank")) {
            if(args.length == 2 ){
                Player pr = Bukkit.getPlayer(args[0].toString());
                SPlayer spr = ShadyController.getPlayer(pr);
                String r = args[1].toString();
                spr.setRank(r);
            }else{
                Msg.Player("Correct usage = /Rank User Rank", p);
            }
        }
    }else{
        Msg.Player("You don't have perms to do that!", p);
    }
    return false;
    }

}