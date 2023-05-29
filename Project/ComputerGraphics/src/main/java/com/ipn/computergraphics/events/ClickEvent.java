package com.ipn.computergraphics.events;

import com.ipn.computergraphics.models.Effects;
import com.ipn.computergraphics.models.GUI;
import com.ipn.computergraphics.models.ParticleData;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class ClickEvent implements Listener {

    private GUI menu;
    public ClickEvent(){
        menu = new GUI();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!event.getInventory().equals(menu.getInventory()))
            return;
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(event.getView().getType() == InventoryType.PLAYER)
            return;

        ParticleData particle = new ParticleData(player.getUniqueId());

        if(particle.hasID()){
            particle.endTask();
            particle.removeID();
        }

        Effects trails = new Effects(player);
        switch (event.getSlot()){
            case 2:
                trails.startStar();
                player.closeInventory();
                player.updateInventory();
                break;
            case 3:
                trails.startButterfly();
                player.closeInventory();
                player.updateInventory();
                break;
            case 4:
                trails.startTotem();
                player.closeInventory();
                player.updateInventory();
                break;
            case 5:
                trails.startFire();
                player.closeInventory();
                player.updateInventory();
                break;
            case 6:
                trails.startLifeSaver();
                player.closeInventory();
                player.updateInventory();
                break;
            default:
                break;
        }
    }
}
