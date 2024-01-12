package de.expertofclients.hideandseek.commands;

import de.expertofclients.hideandseek.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Party implements CommandExecutor {
    private int taskID;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
           taskID =  Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
               int countdown = 5;
               @Override
                public void run() {
                    if(countdown <= 0){
                       Bukkit.broadcastMessage("Die Party Beginnt");
                       Bukkit.getScheduler().cancelTask(taskID);
                    }
                    Bukkit.broadcastMessage("Die part beginnt in " + countdown);
                    countdown--;
                }
            },0, 20*10);
        }
    return false;
    }
}
