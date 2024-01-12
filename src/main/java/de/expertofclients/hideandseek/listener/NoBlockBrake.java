package de.expertofclients.hideandseek.listener;

import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class NoBlockBrake implements Listener {

    @EventHandler
    public void handleBlockBrake(BlockBreakEvent event){
        if(Game.isRunning()){
            event.setCancelled(true);
            event.getPlayer().sendMessage("§4Das Spiel Läuft es dürfen keine Blöcke abgebaut werden!");
        }

    }


}
