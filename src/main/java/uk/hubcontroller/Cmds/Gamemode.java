package uk.hubcontroller.Cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.HubController;
import uk.co.shadycast.shadycontroller.Storage.DB;
import uk.co.shadycast.shadycontroller.Utils.Msg;
import uk.co.shadycast.shadycontroller.Utils.Utils;


public class Gamemode /*implements CommandExecutor {
 * @Override
 * public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
 * Player p = (Player) sender;
 * SPlayer sp = HubController.getPlayer(p);
 * if(cmd.getLabel().equalsIgnoreCase("Gamemode") || cmd.getLabel().equalsIgnoreCase("gm")){
 * if (DB.getRankPower(sp.getRank()) >= 8) {
 * if(args.length == 1){
 * if(Utils.isInt(args[0])){
 * int gm = Integer.parseInt(args[0]);
 * if(gm == 0){
 * p.setGameMode(GameMode.SURVIVAL);
 * }else if(gm == 1){
 * p.setGameMode(GameMode.CREATIVE);
 * }else if(gm == 2){
 * p.setGameMode(GameMode.ADVENTURE);
 * }else{
 * Msg.Player("Correct usage /Gamemode <0|1|2>", p);
 * }
 * }else{
 * Msg.Player("Correct usage /Gamemode <0|1|2>", p);
 * }
 * }else{
 * Msg.Player("Correct usage /Gamemode <0|1|2>", p);
 * }
 * } else {
 * Msg.Player("Thats a no no", p);
 * }
 * }
 * return false;
 * }*/
{
}