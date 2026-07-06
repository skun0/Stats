package stats;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	
	public void onEnable() {
		getLogger().info("Plugin enabled.");
		getCommand("stats").setExecutor(new Command());
		getServer().getPluginManager().registerEvents(new Event(), this);
	}

}
