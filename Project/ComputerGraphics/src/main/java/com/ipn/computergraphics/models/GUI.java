package com.ipn.computergraphics.models;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {
    private static Inventory INV;

    public void register(){
        Inventory inv = Bukkit.createInventory(null,9, ChatColor.AQUA+"Particles");

        ItemStack item1 = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.DARK_AQUA+"Star (Hypotrochoid)");
        item1.setItemMeta(meta1);
        inv.setItem(2,item1);

        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+"Butterfly Wings (Butterfly Curve)");
        item.setItemMeta(meta);
        inv.setItem(3,item);

        ItemStack item3 = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.GREEN+"Totem Trails (Helix-like)");
        item3.setItemMeta(meta3);
        inv.setItem(4,item3);

        ItemStack item2 = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW+"Fire Trail (Helix)");
        item2.setItemMeta(meta2);
        inv.setItem(5,item2);

        ItemStack item4 = new ItemStack(Material.MUSIC_DISC_CAT);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.DARK_PURPLE+"Lifesaver (Torus)");
        item4.setItemMeta(meta4);
        inv.setItem(6,item4);

        setInventory(inv);
    }

    public Inventory getInventory(){
        return INV;
    }

    private void setInventory(Inventory inv){
        INV = inv;
    }

    public void openInventory(Player player){
        player.openInventory(INV);
    }
}
