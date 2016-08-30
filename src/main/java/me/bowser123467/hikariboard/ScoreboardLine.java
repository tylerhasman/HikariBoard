package me.bowser123467.hikariboard;

import org.bukkit.ChatColor;

public class ScoreboardLine {

	private String text;
	
	protected ScoreboardLine(String text) {
		setText(text);
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return text;
	}

	private void setText(String text) {
		if(text.length() > 32){
			throw new IllegalArgumentException("id must be less than 33 characters long.");
		}
		this.text = text;
	}
	
	public String getPrefix(){
		if(text.length() <= 16){
			return text;
		}
		return text.substring(0, 16);
	}
	
	public String getPrefixFinalColor(){
		ChatColor color = ChatColor.WHITE;
		boolean bold = false;
		boolean underlined = false;
		boolean italic = false;
		boolean strikethrough = false;
		String prefix = getPrefix();
		
		boolean next = false;
		
		for(char c : prefix.toCharArray()){
			if(next){
				next = false;
				ChatColor co = ChatColor.getByChar(c);
				if(co == ChatColor.BOLD){
					bold = true;
				}else if(co == ChatColor.ITALIC){
					italic = true;
				}else if(co == ChatColor.UNDERLINE){
					underlined = true;
				}else if(co == ChatColor.RESET){
					color = ChatColor.WHITE;
					bold = false;
					underlined = false;
					italic = false;
					strikethrough = false;
				}else if(co == ChatColor.STRIKETHROUGH){
					strikethrough = true;
				}else{
					color = co;
					bold = false;
					underlined = false;
					italic = false;
					strikethrough = false;
				}
			}
			if(c == ChatColor.COLOR_CHAR){
				next = true;
			}
		}
		
		String co = color.toString();
		if(bold){
			co += ChatColor.BOLD;
		}
		if(italic){
			co += ChatColor.ITALIC;
		}
		if(underlined){
			co += ChatColor.UNDERLINE;
		}
		if(strikethrough){
			co += ChatColor.STRIKETHROUGH;
		}
		
		return co;
	}
	
	public String getSuffix(){
		if(text.length() <= 16){
			return "";
		}
		return text.substring(16);
	}
	
	public String getText() {
		return text;
	}
	
}
