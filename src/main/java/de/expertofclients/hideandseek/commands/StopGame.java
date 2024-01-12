package de.expertofclients.hideandseek.commands;

import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.ChangeColorPlayer;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopGame implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(!player.hasPermission("*")){
                player.sendMessage("Du hast keine Rechte");
                return false;
            }
            if(!Game.isRunning()){
                player.sendMessage("Es läuft kein Spiel");
                return false;
            }
           Game.stopGame(player);
        }else{
            commandSender.sendMessage("Es Dürfen nur Spieler den Befehl ausführen");
        }
        return false;
    }


}

