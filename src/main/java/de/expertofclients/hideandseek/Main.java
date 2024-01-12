package de.expertofclients.hideandseek;

import de.expertofclients.hideandseek.commands.HaSConfig.HasMain;
import de.expertofclients.hideandseek.commands.HaSConfig.Inventare.AllPlayerInv;
import de.expertofclients.hideandseek.commands.HaSConfig.Inventare.createTime;
import de.expertofclients.hideandseek.commands.StopGame;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.ChangeColorPlayer;
import de.expertofclients.hideandseek.listener.DeathListener;
import de.expertofclients.hideandseek.listener.NoBlockBrake;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
    getCommand("stopHideandSeek").setExecutor(new StopGame());
    getCommand("HasConfig").setExecutor(new HasMain());

        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new NoBlockBrake(), this);
        pl.registerEvents(new HasMain(), this);
        pl.registerEvents(new AllPlayerInv(), this);
        pl.registerEvents(new createTime(), this);
        pl.registerEvents(new DeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(Player p: Bukkit.getOnlinePlayers()){
            ChangeColorPlayer.makeNormalPlayer(p);
        }
    }


    public static Main getPlugin(){
        return plugin;
    }
}
