package de.expertofclients.hideandseek.commands.HaSConfig.Inventare;

import de.expertofclients.hideandseek.commands.HaSConfig.HasMain;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class AllPlayerInv implements Listener {
private static Inventory inventory;
    public static void openPlayerInventory(Player p){
        createPlayerInventory();
        getAllPlayerinWorld(p);
        p.openInventory(inventory);

    }

    private static void createPlayerInventory() {
         inventory = Bukkit.createInventory(null, 9*6, "§5Alle Spieler");
    }

    //Baue hier eine Inventar mit allen Spielern deren Köpfen wenn diese Angeklickt werden wird dieser Als Hider Makiert
    private static void getAllPlayerinWorld( Player player) {
        for (Player p : player.getWorld().getPlayers()) {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setDisplayName("§b" + p.getName());
            item.setItemMeta(skullMeta);
            // Füge das erstellte Item zum Inventar hinzu
            inventory.addItem(item);
        }
    }

    //Handle hier den Geklickten SPieler und mache ihn Zum Hider
    // Gebe den Spieler dann zurück und speicher diesen in der Static Variable

// Fehler ist in der equaals methode
    @EventHandler
    private static void handleInv(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(inventory) ) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                //HIer den Spieler der GEklickt wurde soll es werdne
                Player p = (Player) event.getWhoClicked();
                p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_3, 1 ,2);
                Game.setSeeker(p);
                HasMain.openTimerSettings(p);
            }

        }

    }



}
