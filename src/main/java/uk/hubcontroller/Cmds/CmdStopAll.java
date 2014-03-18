package uk.hubcontroller.Cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.hubcontroller.Utils.Msg;


public class CmdStopAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("?") || 
           cmd.getLabel().equalsIgnoreCase("help") || 
           cmd.getLabel().equalsIgnoreCase("me") || 
           cmd.getLabel().equalsIgnoreCase("pl") || 
           cmd.getLabel().equalsIgnoreCase("plugins")){
            Msg.Player(ChatColor.RED + "Sorry thats a no no", p);
        }
    return false;
    }

}