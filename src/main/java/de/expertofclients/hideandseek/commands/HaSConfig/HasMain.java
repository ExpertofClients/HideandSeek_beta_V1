package de.expertofclients.hideandseek.commands.HaSConfig;

import de.expertofclients.hideandseek.commands.HaSConfig.Inventare.AllPlayerInv;
import de.expertofclients.hideandseek.commands.HaSConfig.Inventare.RandomPlayerPick;
import de.expertofclients.hideandseek.commands.HaSConfig.Inventare.createTime;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.CheckConfig;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class HasMain implements CommandExecutor, Listener {
    private static Inventory inventory;
    private  static Player inventoryPlayer;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("*")) {
                inventoryPlayer = player;
                openTimerSettings(player);
            }
        }

        return false;
    }

    /**
     * Öffnet das TimerSettings Inventar
     * Und baut das Inventar auf
     * @param p
     */
    public static void openTimerSettings(Player p) {
        inventory = Bukkit.createInventory(null, 9 * 6, "§bTimerSettings");
        inventory.setItem(10, getTimeIcon());
        /*
        inventory.setItem(13, getSichtbarkeitIcon());
        inventory.setItem(16, getWeapons());
        inventory.setItem(29, getAllplayerIcon(p));
         */
        inventory.setItem(33, getRandomIcon());
        inventory.setItem(53, getPlaySkull());
        setglassRand(inventory);
        p.openInventory(inventory);


    }

   /**
        * Setzt die Ränder des Inventars
        * @param inv
        */

    private static void setglassRand(Inventory inv) {
        // Fülle die erste Reihe (Index 0 bis 8)
        for (int i = 0; i <= 8; i++) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        // Fülle die rechte Spalte (Index 9, 18, 27, 36, 45)
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        // Fülle die linke Spalte (Index 8, 17, 26, 35, 44)
        for (int i = 8; i < 44; i += 9) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        // Fülle die unterste Reihe (Index 45 bis 53)
        for (int i = 36; i <= 44; i++) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
    }

    /**
     * Erstellt das Icon für Random Spieler zu Picken
     * Wenn ein Spieler ausgewählt wird, wird ein Sound abgespielt
     * @return
     */
    private static @NotNull ItemStack getRandomIcon() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bRandom Spieler auswählen");
        item.setItemMeta(meta);

        return item;
    }

    //Späterere Release
    /*
    private static ItemStack getAllplayerIcon(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(p.getName().toString());
        skullMeta.setDisplayName("§bSpieler auswählen");
        item.setItemMeta(skullMeta);

        return item;

    }

    private static ItemStack getWeapons() {
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bWeapons / Kit auswahl DEAKTIVIERT");
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getSichtbarkeitIcon() {
        ItemStack item = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bSichtbarkeit des Suchers DEAKTIVIERT");
        item.setItemMeta(meta);
        return item;
    }
*/

    private static ItemStack getTimeIcon() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bZeit stellen");

        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getPlaySkull() {
        ItemStack skull = new ItemStack(Material.DIAMOND);
        ItemMeta meta = skull.getItemMeta();
        meta.setDisplayName("§4Start");
        skull.setItemMeta(meta);
        return skull;
    }

    /**
     * Handelt die Klicks im Inventar
     * @param event
     */
    @EventHandler
    public void handleInventory(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().equals((inventory))) {
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (clickedItem != null) {
                switch (clickedItem.getType()) {
                    case CLOCK:
                        // Code für CLOCK
                        createTime.openClockInv(player);
                        break;

                   // case PLAYER_HEAD:
                     //   AllPlayerInv.openPlayerInventory(player);
                      //  break;
                    case FIREWORK_ROCKET:
                        RandomPlayerPick.randompick(player);
                        inventoryPlayer.playSound(inventoryPlayer.getLocation(), "minecraft:block.note_block.pling", 1, 1);
                        break;

                    /* Späterere Release
                    case GLASS_BOTTLE:
                        EffectInventory.openEffectInv(player);
*/
                    case DIAMOND:
                        if(CheckConfig.checkConfigs(player)){
                            Game.gameStart(player);
                            Game.setRunning(true);
                        }

                        break;

                }
            }
            event.setCancelled(true); // Verhindert, dass das Item bewegt wird.
        }
    }

}
