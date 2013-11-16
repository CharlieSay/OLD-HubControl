package uk.co.shadycast.shadycontroller.Cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Utils.Msg;


public class CmdStopAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("?") || 
           cmd.getLabel().equalsIgnoreCase("help") || 
           cmd.getLabel().equalsIgnoreCase("me") || 
           cmd.getLabel().equalsIgnoreCase("pl") || 
           cmd.getLabel().equalsIgnoreCase("plugin") || 
           cmd.getLabel().equalsIgnoreCase("plugins") ||
           cmd.getLabel().equalsIgnoreCase("stop") ||
           cmd.getLabel().equalsIgnoreCase("reload") ||
           cmd.getLabel().equalsIgnoreCase("rl")){
            Msg.Player(ChatColor.RED + "Sorry thats a no no", p);
        }
    return false;
    }

}