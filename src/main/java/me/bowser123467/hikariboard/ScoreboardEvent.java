package me.bowser123467.hikariboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ScoreboardEvent extends PlayerEvent {

	public static final HandlerList handlers = new HandlerList();
	
	private List<ScoreboardLine> lines;
	
	private String scoreboardName;
	private String header, footer;
	
	public ScoreboardEvent(Player player, String scoreboardName) {
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
		if(scoreboardName.length() > 32){
			scoreboardName = scoreboardName.substring(0, 32);
		}
		this.scoreboardName = scoreboardName;
	}
	
	public String getScoreboardName() {
		return scoreboardName;
	}
	
	public void writeLine(String text){
		if(lines.size() >= ChatColor.values().length){
			throw new IllegalStateException("Cannot write anymore lines.");
		}
		lines.add(new ScoreboardLine(text));
	}
	
	public void insertLine(int position, String text){
		if(lines.size() >= ChatColor.values().length){
			throw new IllegalStateException("Cannot write anymore lines.");
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
