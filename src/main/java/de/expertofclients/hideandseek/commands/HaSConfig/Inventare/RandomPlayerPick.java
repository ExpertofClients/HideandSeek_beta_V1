package de.expertofclients.hideandseek.commands.HaSConfig.Inventare;

import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RandomPlayerPick {
    public static void randompick(Player player) {
        int random = (int) (Math.random() * player.getWorld().getPlayers().size()) + 1;
        int count = 1;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (count == random) {
                Game.setSeeker(p);
            }
            count++;
        }
    }

}
