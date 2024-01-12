package de.expertofclients.hideandseek.commands.HaSConfig.Inventare;


import de.expertofclients.hideandseek.commands.HaSConfig.HasMain;
import de.expertofclients.hideandseek.commands.HaSConfig.hilfsmethoden.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class createTime implements Listener {
    private static double time = Game.getTime();
    private static Inventory inventory;

    public static void openClockInv(Player p){
        createInv();
        buildInv();
        p.openInventory(inventory);

    }
    private static void createInv(){
        inventory = Bukkit.createInventory(null, 9*3, "§bZeiteinstellen");
    }

    private static void buildInv(){
        //Erstelle hier einen schwarzen Rand
        ItemStack blackglas = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = blackglas.getItemMeta();
        meta.setDisplayName(" ");
        blackglas.setItemMeta(meta);
        for(int i = 0; i < 9; i++){
            inventory.setItem(i, blackglas);
        }
        for(int i = 18; i< 24; i++ ){
            inventory.setItem(i, blackglas);
        }

        inventory.setItem(9, blackglas);
        inventory.setItem(17, blackglas);

        inventory.setItem(25, blackglas);

        //Erstell hier Infotext
        ItemStack infopaper = new ItemStack(Material.PAPER);
        meta = infopaper.getItemMeta();
        meta.setDisplayName("§4Zeit Menü");

        ArrayList<String> list = new ArrayList<>();
        list.add("+ Button um Zeit um 1min zu erhöhen");
        list.add("- Button drücken um Zeit 1 min zu veringern");

        meta.setLore(list);
        infopaper.setItemMeta(meta);

        inventory.setItem(13, infopaper);

        //+ und - button
        ItemStack redminus = new ItemStack(Material.RED_WOOL);
        meta = redminus.getItemMeta();
        meta.setDisplayName("§4-");
        redminus.setItemMeta(meta);

        ItemStack greenplus = new ItemStack(Material.GREEN_WOOL);
        meta = greenplus.getItemMeta();
        meta.setDisplayName("§a+");
        greenplus.setItemMeta(meta);

        inventory.setItem(11, redminus);
        inventory.setItem(15, greenplus);

        // setze hier countime
        updatecountButton();



        //setze hier speicher item
        ItemStack speichern = new ItemStack(Material.GOLDEN_APPLE);
        meta = speichern.getItemMeta();
        meta.setDisplayName("§aSpeichern");
        speichern.setItemMeta(meta);
        inventory.setItem(26, speichern);

    }

    private static void updatecountButton(){
        ItemStack count = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = count.getItemMeta();
        meta.setDisplayName("§b" + Game.getTime() + " Mintuten ");
        count.setItemMeta(meta);
        inventory.setItem(24, count);
        System.out.println("test");
    }


    @EventHandler
    private static void handleClick(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(inventory)){
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null){
                switch (event.getCurrentItem().getType()){
                    case RED_WOOL:
                        if(time == 0){
                            player.sendMessage("§bZeit darf nicht kleiner 1 Minute sein sein");
                            player.closeInventory();
                            return;
                        }else{
                            time = time - 1;
                            Game.setTime(time);
                            updatecountButton();
                        }
                        break;
                    case GREEN_WOOL:
                        time++;
                        Game.setTime(time);
                        updatecountButton();
                        break;

                    case GOLDEN_APPLE:
                        Game.setTime(time);
                        player.sendMessage("hi");
                        HasMain.openTimerSettings(player);
                }
            }
            event.setCancelled(true);
        }
    }
}
