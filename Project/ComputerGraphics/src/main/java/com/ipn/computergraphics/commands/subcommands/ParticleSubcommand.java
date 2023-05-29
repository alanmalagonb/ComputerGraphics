package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Curve;
import com.ipn.computergraphics.math.Vector3;
import com.ipn.computergraphics.models.GUI;
import com.ipn.computergraphics.utils.Message;
import com.ipn.computergraphics.models.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class ParticleSubcommand extends Subcommand{

    @Inject
    private JavaPlugin plugin;

    protected ParticleSubcommand(){
        super("particles",1);
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        GUI menu = new GUI();
        menu.openInventory(player);

        return false;
    }
}