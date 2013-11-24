package uk.co.shadycast.shadycontroller.Cmds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.DB;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.Utils;

public class Ban implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        SPlayer sp = ShadyController.getPlayer(p);
         if (cmd.getLabel().equalsIgnoreCase("Ban")) {
           if (DB.getRankPower(sp.getRank()) >= 7) {
                if (args.length > 3) {
                    if (Bukkit.getPlayer(args[0].toString()).isOnline()) {
                        Player bp = Bukkit.getPlayer(args[0].toString());
                        SPlayer bsp = ShadyController.getPlayer(bp);
                        if (Utils.isInt(args[1].toString())) {
                            int length = Integer.parseInt(args[1]);
                            if (DB.getRankPower(sp.getRank()) > DB.getRankPower(bsp.getRank())) {
                                int rl = args.length - 2;
                                String Reason = "";
                                for(int i=2; i<args.length; i++){
                                  Reason += args[i] + " ";
                                }
                                Date BF = new Date();
                                Date BU = new Date();
                                Calendar c = Calendar.getInstance();
                                c.setTime(BU);
                                c.add(Calendar.DATE, length);
                                BU = c.getTime();
                                DB.newBan(bp.getName(),p.getName(),Reason,BF,BU);
                                bp.kickPlayer(string);
                            } else {
                                Msg.Player("You don't have perms to ban that person!", p);
                            }
                        }
                    } else {
                        Msg.Player("That Player Isn't Online!", p);
                    }
                } else {
                    Msg.Player("Correct Usage = /Ban <Player> <Days> <Reason>", p);
                }
            } 
        }
        return false;
    }
}