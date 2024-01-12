package de.expertofclients.hideandseek.commands.HaSConfig.Inventare;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EffectInventory {
    private static Inventory inv;

    public static void openEffectInv(Player p){
        createInv();
        p.openInventory(inv);
    }

    public static void createInv(){
        inv = Bukkit.createInventory(null, 9*6, "§bEffect-Einstellung");
    }

}
