package de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden;

import de.expertofclients.hideandseek.Exeption.CheckExeption;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckConfig {
    private static Player p;
    private static boolean ready = false;


    /**
     * Rufe hier alle Checkmethoden auf
     * Wenn alles Gecheckt wurde wird ready zurückgeben sonst nicht da sonst der Code weiter ausgeführt wird
     * @param player
     * @return
     */
    public static boolean checkConfigs(Player player){
        p = player;
        try{
            checkPlayerinWorld(player);
            checkSeeker();
            checkPlayTime();
            ready = true;
        }catch (CheckExeption e){
            player.sendMessage(e.getFehler());
        }
        return ready;
    }


    /**
     * Schaue ob genug Spieler in der Welt sind
     * @param p
     */
    public static boolean checkPlayerinWorld(Player p) throws CheckExeption {
        int spielerinWorld = p.getWorld().getPlayers().size();
        if(spielerinWorld <= 1){
            p.closeInventory();
            throw new CheckExeption("§4Fehler es müssen  §amind. 2 Spieler §4 online sein.");
        }
        return true;
    }

    /**
     * Schaue hier ob ein Spieler ausgewählt ist.
     */
    private static void checkSeeker() throws CheckExeption {
        if(Game.getSeeker() == null){
            p.closeInventory();
            throw new CheckExeption("§4Es ist ein Seeker ausgewählt");
        }
    }

    /**
     * Check hier Auf die Spielzeit die eingestellt wurde
     */
    private static void checkPlayTime() throws CheckExeption {
    if(Game.getTime() <= 0){
        p.closeInventory();
        throw new CheckExeption("§4Zeit darf nicht auf 0 Mintuten eingestellt sein");
    }

    }


    /**
     * Checke die Anzahl der Seeker
     */

    public static void checkHiderplayer(){
        if(Game.allHider.size() == 0){
            Game.stopGame(Game.getSeeker());
        }
    }
}
