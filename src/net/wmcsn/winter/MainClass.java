package net.wmcsn.winter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.Geekpower14.*;
import com.Geekpower14.Quake.Quake;
import com.Geekpower14.Quake.Quake.*;

import net.wmcsn.winter.Mysql;

public class MainClass extends JavaPlugin implements Listener {

	public Mysql MySQL = null;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		MySQL = new Mysql(this, "212.83.161.167", 3306, "winterec_gmod", "winterec_admin", "wowhead1");
	    MySQL.openConnection();
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		int tokens = MySQL.getTokens(e.getPlayer().getName());
		if (MySQL.getTokens(e.getPlayer().getName()) == 0) {
			MySQL.setupPlayer(e.getPlayer().getName());
		}else{
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if (cmd.getName().equalsIgnoreCase("tokengive")) {
			if (args.length == 2) {
				if (sender.isOp()) {
				int tokens = MySQL.getTokens(args[0]);
				int nt = tokens + Integer.valueOf(args[1]); 
				sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "" + args[0] + " Had " + tokens + " Tokens and now has " + String.valueOf(nt) + " Tokens");
				MySQL.setTokens(args[0], tokens + Integer.valueOf(args[1]));
				}else{
					sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "You Must Be And Op to do this command!");
				}
			}else{
				sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "Correct Syntax is /tokengive <PlayerName> <Ammount>");
			}
			return true;
		}
		
		
		if (cmd.getName().equalsIgnoreCase("tokenset")) {
			if (sender.isOp()) {
				if (args.length == 2) {
					MySQL.setTokens(args[0], Integer.valueOf(args[1]));
				}else{
					sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "Correct Syntax is /tokengive <PlayerName> <Ammount>");
				}
			}else{
				sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "You Must Be And Op to do this command!");
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("token")) {
			int tokens = MySQL.getTokens(sender.getName());
			sender.sendMessage(ChatColor.GOLD + "" +ChatColor.BOLD + "You Have " + ChatColor.DARK_GREEN + "" +ChatColor.BOLD + tokens + "" + ChatColor.GOLD + "" + ChatColor.BOLD + " Tokens" );
			sender.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "Thank You For Playing Scrypted Network");
		}
		
		if (cmd.getName().equalsIgnoreCase("tokenshop")) {
			sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "To Buy An Item Do /ts <ITEMID>");
			sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "To Check you tokens do /token");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID <ID>" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "<COST> Tokens " + ChatColor.GREEN + " <ITEMNAME>");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID 1" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "20 Tokens " + ChatColor.GREEN + " Token Man' Prefxi");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID 2" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "30 Tokens " + ChatColor.GREEN + "'Token Master' Prefxi");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID 3" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "50 Tokens " + ChatColor.GREEN + " VIP Rank");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID 4" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "60 Tokens " + ChatColor.GREEN + "'Token King' Prefxi");
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ID 5" + ChatColor.GOLD + "" + ChatColor.BOLD + "-" + ChatColor.RED + "" + ChatColor.BOLD +  "120 Tokens " + ChatColor.GREEN + "MVP Rank");
		}
		
		if (cmd.getName().equalsIgnoreCase("ts")) {
			int tokens = MySQL.getTokens(sender.getName());
			if (args[0].equals("1")) {
				if (tokens >= 20) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " prefix  &6&l[&6&lToken&2&l-&6&lMan&2&l]");
				MySQL.setTokens(sender.getName(), tokens-20);
				}
			}
			if (args[0].equals("2")) {
				if (tokens >= 30) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " prefix  &6&l[&6&lToken&2&l-&6&lMaster&2&l]");
				MySQL.setTokens(sender.getName(), tokens-30);
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Congratulations You Have bought the 'Token Master' Prefix");
				}
			}
			if (args[0].equals("3")) {
				if (tokens >= 50) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " group set vip");
				MySQL.setTokens(sender.getName(), tokens-50);
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Congratulations You Have bought VIP");
				}
			}
			if (args[0].equals("4")) {
				if (tokens >= 60) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " prefix  &6&l[&6&lToken&2&l-&6&lKing&2&l]");
				MySQL.setTokens(sender.getName(), tokens-60);
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Congratulations You Have bought the 'Token King' Prefix");
				}
			}
			if (args[0].equals("5")) {
				if (tokens >= 120) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " group set mvp");
				MySQL.setTokens(sender.getName(), tokens-120);
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Congratulations You Have bought MVP");
				}
				
				}
		}
		return false; 
	}
}
