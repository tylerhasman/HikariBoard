package me.bowser123467.hikariboard;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HikariBoard extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		ScoreboardManager man = new ScoreboardManager();
		
		int updateRate = getConfig().getInt("update_rate", 1);
		
		File config = new File(getDataFolder(), "config.yml");
		
		if(!config.exists()){
			saveDefaultConfig();
		}
		
		Bukkit.getScheduler().runTaskTimer(this, man, 0, updateRate);
		
		ConsoleCommandSender sender = Bukkit.getConsoleSender();

		sender.sendMessage(ChatColor.GOLD+"---------------");
		sender.sendMessage(ChatColor.GOLD+"- "+ChatColor.YELLOW+" HikariBoard");
		sender.sendMessage(ChatColor.GOLD+"- "+ChatColor.YELLOW+"Version "+getDescription().getVersion());
		sender.sendMessage(ChatColor.GOLD+"- "+ChatColor.YELLOW+"Update Rate set to "+updateRate);
		sender.sendMessage(ChatColor.GOLD+"- "+ChatColor.AQUA+"Created by: https://www.spigotmc.org/members/bowser123467.475/");
		sender.sendMessage(ChatColor.GOLD+"---------------");
		
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent event){
		Player pl = event.getPlayer();
		
		if(getConfig().getBoolean("force_unique_scoreboard", true)){
			pl.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
		
	}
	
}
