package uk.hubcontroller.Cmds;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.hubcontroller.Objects.SPlayer;
import uk.hubcontroller.Objects.SRank;
import uk.hubcontroller.HubController;
import uk.hubcontroller.Storage.DB;
import uk.hubcontroller.Utils.Msg;

public class Rank implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("Rank")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                SPlayer sp = HubController.getPlayer(p);
                if (sp.getRank().getRankPower() >= 8) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[0].toString()).isOnline()) {
                            Player pr = Bukkit.getPlayer(args[0].toString());
                            SPlayer spr = HubController.getPlayer(pr);
                            String r = args[1].toString();
                            spr.setRank(SRank.valueOf(r));
                        }else {
                            Msg.Player("That Player is currently offline!", p);
                        }
                    } else {
                        Msg.Player("Your Rank : " + sp.getRank(), p);
                    }
                }else {
                        Msg.Player("Your Rank : " + sp.getRank(), p);
                    }
            } else {
                Date d = new Date();
                DB.addShadyPlayer(args[0].toString(), "Player", d, d, 0);
                SPlayer spr = DB.getShadyPlayer(args[0].toString());
                String r = args[1].toString();
                spr.setRank(SRank.valueOf(r));
            }
        }
        return false;
    }
}