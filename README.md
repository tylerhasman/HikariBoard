# HikariBoard
The fastest, most lightweight scoreboard api for Spigot 1.10.2

### Examples

```java

@EventHandler
public void onScoreboardUpdate(ScoreboardEvent event){
  Player player = event.getPlayer();
  
  //Set the scoreboards title (objective display name) (optional but recommended)
  event.setScoreboardName("Test Scoreboard");
  
  //Set a line to always be at the top of the scoreboard (optional)
  event.setHeader("---------");
  
  //Set a line to always be at the bottom of the scoreboard (optional)
  event.setFooter("---------");
  
  //Write some lines to the scoreboard
  //Note: No 'id' is necessary, unlike many scoreboad plugins HikariBoard operates a little differently
  event.writeLine("Your Name");
  event.writeLint(player.getName());
  
}

```
