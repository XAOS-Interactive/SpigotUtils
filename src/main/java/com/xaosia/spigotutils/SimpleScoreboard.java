package com.xaosia.spigotutils;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.collect.Maps;

public class SimpleScoreboard {
	private Scoreboard scoreboard;
	private Objective obj;
	private Map<String, Integer> lines;
	private String title;
	private Boolean visible;
	
	public SimpleScoreboard(String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.lines = Maps.newLinkedHashMap();
		this.obj = null;
		this.visible = true;
	}
	


	public void blankLine() {
		add(" ");
	}

	public void addAll(List<String> lines) {
		for(String line : lines){
			add(line, null);
		}
	}
	
	public void add(String text) {
		add(text, null);
	}

	public void add(String text, Integer score) {
		String textCut = text.length() >= 40 ? text.substring(0, 39) : text;
		textCut = fixDuplicates(textCut);
		lines.put(textCut, score);
	}

	private String fixDuplicates(String text) {
		while (lines.containsKey(text))
			text += "§r";
		if (text.length() >= 40)
			text = text.substring(0, 39);
		return text;
	}
	


	private void build() {
		if(obj == null){
			obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
			obj.setDisplayName(title);
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
	}
	
	public void destroy(){
		if(obj != null){
			obj.setDisplaySlot(null);
			obj.unregister();
			obj = null;
		}
	}
	
	public void clear(){
		for (Map.Entry<String, Integer> entry : lines.entrySet()) {
			scoreboard.resetScores(entry.getKey());
		}
		lines.clear();
	}
	
	public void draw(){
		if(obj == null){
			build();
		}

		int index = lines.size();
		
		for (Map.Entry<String, Integer> entry : lines.entrySet()) {
			Integer score = entry.getValue() != null ? entry.getValue() : index;
			obj.getScore(entry.getKey()).setScore(score);
			index -= 1;
		}
	}
	
	public void send(Player player){
		player.setScoreboard(scoreboard);
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public Scoreboard getBukkitScoreboard(){
		return scoreboard;
	}

	
	
}
