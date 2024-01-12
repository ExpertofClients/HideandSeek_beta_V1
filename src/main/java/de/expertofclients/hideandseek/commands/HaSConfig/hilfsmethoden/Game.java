package de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Game {
    /**
     * Lege hier Statisch einen Seeker fest
     */
    private static Player Seeker;

    /**
     * Statische Methode ob das Spiel gerade läuft
     */
    private static boolean running;

    // private static ArrayList<String> Effects  = new ArrayList<>();

    /**
     * Setze hier die Spielzeit die Gespielt werden soll
     */
    private static double time = 0;

    /**
     * Public ArrayList von Versteckern
     * Diese Wird befüllt mit allen Versteckern wenn kein Verstecker mehr übrig ist wird StopGame ausgelöst
     */
    public static ArrayList<Player> allHider = new ArrayList<>();


    /**
     * Hole den Seeker
     * @return
     */
    public static Player getSeeker() {
        return Seeker;
    }

    /**
     * Setze hier die Variable wer der 1 Seeker ist
     * @param seeker
     */
    public static void setSeeker(Player seeker) {
        Seeker = seeker;
    }

    /**
     * Frage hier ab ob das Spiel läuft
     * es darf nur 1 Spiel laufen
     * @return
     */
    public static boolean isRunning() {
       return running;
    }

    /**
     * Setze hier das Spiel auf run
     * @param run
     */
    public static void setRunning(boolean run) {
        running = run;
    }

    /*
    public static ArrayList<String> getEffects() {
        return Effects;
    }

    public static void setEffects(ArrayList<String> effects) {
        Effects = effects;
    }
    */

    /**
     * Hole hier die Festgelegte Spielzeit
     * @return
     */
    public  static double getTime() {
        return time;
    }

    /**
     * Setzte hier die Spielzeit
     * @param zeit
     */
    public static void setTime(double zeit) {
        time = zeit;
    }


    /**
     * Adde hier Spieler zu den Versteckern
     * Der Übergebe spieler wird den Versteckern zugeordnet
     * @param p
     */
    public static void addSeeker(Player p){
       allHider.add(p);
    }

    /**
     * Entferne hier einen Verstecker
     * Dieser wird entfernt wenn er gekillt wird!
     * @param p
     */
    public static void removeSeeker(Player p){
            allHider.remove(p);
    }

    /**
     * Update hier die Spieler
     * Eine Forschleife der Spieler
     * @param player
     */
    public static void updateAllPlayer(Player player){
        for(Player p : player.getWorld().getPlayers()){
                updatePlayer(p);
        }
    }

    /**
     * Update hier einen Übergeben Spieler
     * Der Spieler wird in den Adventure Modus gesetzt
     * Bekomme 1 Herz
     * und Sätigung
     * @param player
     */
    private static void updatePlayer(Player player){
        player.setGameMode(GameMode.ADVENTURE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 5));
        player.setHealthScale(2);

    }


    /**
     * Stopgame Methode diese Stoptdas Game
     * Färbt alle Spieler normal
     * @param player
     */
    public static void stopGame(Player player){
        Game.setRunning(false);
        ChangeColorPlayer.changeNametoDefault(player);
        for (Player p: player.getWorld().getPlayers()){
            p.sendTitle("Game vorbei", "Seeker hat gewonnen");
            p.setHealthScale(20);
        }
    }


    public static void gameStart(Player player){
        if(Game.isRunning()){
            player.sendMessage("§4Das Spiel läuft bereits");
            return;
        }
        Game.setRunning(true);
        for(Player p :  player.getWorld().getPlayers() ){
            if(p == Game.getSeeker()){
                ChangeColorPlayer.makeSeeker(p);
            }else{
                ChangeColorPlayer.makeHider(p);
                Game.addSeeker(p);
            }
            Game.updateAllPlayer(p);
        }

    }


}
