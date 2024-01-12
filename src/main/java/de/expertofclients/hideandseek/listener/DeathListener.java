package de.expertofclients.hideandseek.listener;

import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.ChangeColorPlayer;

import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.CheckConfig;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    /**
     * This method is called when a player dies.
     * If the game is running, the player becomes a seeker.
     * The player is removed from the hider list.
     * The hider list is checked.
     * @param event
     */
    @EventHandler
    public static void handleDeathonRun(PlayerDeathEvent event){
        if (Game.isRunning()){
            event.setDeathMessage("§4Du bist nun SEEKER");
            Player player = event.getEntity().getPlayer();
            ChangeColorPlayer.makeSeeker(player);
            Game.removeSeeker(player);
            CheckConfig.checkHiderplayer();
        }



    }
}
