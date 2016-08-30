package me.bowser123467.hikariboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ScoreboardEvent extends PlayerEvent {

	public static final HandlerList handlers = new HandlerList();
	
	private List<ScoreboardLine> lines;
	
	private String scoreboardName;
	private String header, footer;
	
	protected ScoreboardEvent(Player player, String scoreboardName) {
		super(player);
		this.scoreboardName = scoreboardName;
		lines = new ArrayList<>();
		header = "";
		footer = "";
	}
	
	@Override
	public String toString() {
		return lines.toString();
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getFooter() {
		return footer;
	}
	
	public void setScoreboardName(String scoreboardName) {
		this.scoreboardName = scoreboardName;
	}
	
	public String getScoreboardName() {
		return scoreboardName;
	}
	
	public void writeLine(String text){
		if(lines.size() >= ChatColor.values().length){
			System.out.println("Cannot write anymore lines, exceeds "+ChatColor.values().length);
			return;
		}
		lines.add(new ScoreboardLine(text));
	}
	
	public void insertLine(int position, String text){
		if(lines.size() >= ChatColor.values().length){
			System.out.println("Cannot insert anymore lines, exceeds "+ChatColor.values().length);
			return;
		}
		
		lines.add(position, new ScoreboardLine(text));
	}
	
	public ScoreboardLine getLine(int index){
		return lines.get(index);
	}
	
	public List<ScoreboardLine> getLines(){
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
        return handlers;
    }

}
