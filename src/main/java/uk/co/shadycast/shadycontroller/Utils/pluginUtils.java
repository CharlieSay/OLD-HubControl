package uk.co.shadycast.shadycontroller.Utils;



import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import uk.co.shadycast.shadycontroller.ShadyController;



public class pluginUtils {
    
    private static ShadyController plugin;
 
   public static void setPlugin(ShadyController plugin){
      pluginUtils.plugin = plugin;
   }
 public static ShadyController getPlugin(){
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
