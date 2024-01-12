package de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden;

import org.bukkit.entity.Player;

public class ChangeColorPlayer {
    public static void makeSeeker(Player p){
        p.setDisplayName("§4[Seeker] " + p.getName());
        p.setPlayerListName("§4[Seeker] " + p.getName());
        p.sendTitle("§aDu Bist §4Seeker", "Viel Spaß");
        Game.setRunning(true);
    }

    public static void makeHider(Player p){
        p.setDisplayName("§a[Hider] " + p.getName());
        p.setPlayerListName("§a[Hider] " + p.getName());
        p.sendTitle("§aDu bist [Hider]", "Viel Spaß");
    }

    public static void changeNametoDefault(Player player){
        for(Player p : player.getWorld().getPlayers()){
            ChangeColorPlayer.makeNormalPlayer(p);
        }
    }

    public static void makeNormalPlayer(Player p){
            p.setDisplayName(p.getName());
            p.setPlayerListName(p.getName());
    }


}
