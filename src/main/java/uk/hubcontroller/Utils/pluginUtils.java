package uk.hubcontroller.Utils;



import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import uk.co.shadycast.shadycontroller.HubController;



public class pluginUtils {
    
    private static HubController plugin;
 
   public static void setPlugin(HubController plugin){
      pluginUtils.plugin = plugin;
   }
 public static HubController getPlugin(){
      return plugin;
   }
   public static Server getServer(){
      return plugin.getServer();
   }
 
   public static FileConfiguration getConfig(){
      return plugin.getConfig();
   }
 
   public static void saveConfig(){
      plugin.saveConfig();
   }
    
    
    
}
