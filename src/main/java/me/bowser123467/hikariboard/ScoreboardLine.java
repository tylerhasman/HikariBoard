package me.bowser123467.hikariboard;

import org.bukkit.ChatColor;

public class ScoreboardLine {

	private String text;
	
	private int cutPosition;
	
	public ScoreboardLine(String text) {
		cutPosition = 16;
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
			throw new IllegalArgumentException("text must be less than 33 characters long.");
		}
		this.text = text;
		if(text.length() > 16){
			String prefix = getPrefix();
			if(prefix.endsWith(String.valueOf(ChatColor.COLOR_CHAR))){
				cutPosition = 15;
				if(getSuffix().length() > 16){
					throw new IllegalArgumentException("text must be less than 32 characters long. This is because you have a color character in the middle.");
				}
			}else{
				cutPosition = 16;
			}
		}
	}
	
	public String getPrefix(){
		if(text.length() <= 16){
			return text;
		}
		return text.substring(0, cutPosition);
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
		
		return text.substring(cutPosition);
	}
	
	public String getText() {
		return text;
	}
	
}
