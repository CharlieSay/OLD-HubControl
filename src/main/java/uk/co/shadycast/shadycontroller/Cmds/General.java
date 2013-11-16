package uk.co.shadycast.shadycontroller.Cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.ShadyController;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class General implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        SPlayer sp = ShadyController.getPlayer(p);
        if (DB.getRankPower(sp.getRank()) >= 7) {
            if (cmd.getLabel().equalsIgnoreCase("Ban")) {
                
            }
        }
        return false;
    }

}