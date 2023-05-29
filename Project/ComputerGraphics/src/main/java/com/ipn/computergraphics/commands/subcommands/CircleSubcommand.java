package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Circle;
import com.ipn.computergraphics.math.Vector2;
import com.ipn.computergraphics.utils.Message;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CircleSubcommand extends Subcommand{
    @Inject
    private JavaPlugin plugin;

    protected CircleSubcommand(){
        super("circle",5);
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        String algorithm = args[0];
        Material material = Material.getMaterial(args[1]);
        int radius = Integer.parseInt(args[2]);
        String axis = args[3];

        List<Vector2> ListOfPoints = new ArrayList<>();
        Location loc = player.getLocation();

        if (algorithm.equalsIgnoreCase("b")) {
            sender.sendMessage(Message.format(
                    "&a&m&l=============================================\n" +
                            "&bBresenham Circle Created in "+axis+" axis with radius "+radius+"\n" +
                            "&bwith origin in "+loc+"\n"+
                            "&a&m&l=============================================\n"));
            switch (axis) {
                case "xy", "yx":
                    ListOfPoints = Circle.BresenhamCircle2D(loc.getBlockX(), loc.getBlockY(), radius);
                    break;
                case "xz", "zx":
                    ListOfPoints = Circle.BresenhamCircle2D(loc.getBlockX(), loc.getBlockZ(), radius);
                    break;
                case "yz", "zy":
                    ListOfPoints = Circle.BresenhamCircle2D(loc.getBlockZ(), loc.getBlockY(), radius);
                    break;
            }
        }else if(algorithm.equalsIgnoreCase("m")){
            sender.sendMessage(Message.format(
                    "&a&m&l=============================================\n"+
                            "&bMid-Point Circle Created in "+axis+" axis with radius "+radius+"\n" +
                            "with origin in "+loc+"\n"+
                            "&a&m&l=============================================\n"));
            switch (axis){
                case "xy","yx":
                    ListOfPoints = Circle.midPointCircle2D(loc.getBlockX(),loc.getBlockY(),radius);
                    break;
                case "xz","zx":
                    ListOfPoints = Circle.midPointCircle2D(loc.getBlockX(),loc.getBlockZ(),radius);
                    break;
                case "yz","zy":
                    ListOfPoints = Circle.midPointCircle2D(loc.getBlockZ(),loc.getBlockY(),radius);
                    break;
            }
        }

        for (Vector2 it : ListOfPoints) {
            Location location = null;
            switch (axis){
                case "xy","yx":
                    location = new Location(player.getWorld(),it.getX(),it.getY(),loc.getZ());
                    break;
                case "xz","zx":
                    location = new Location(player.getWorld(),it.getX(),loc.getY(),it.getY());
                    break;
                case "yz","zy":
                    location = new Location(player.getWorld(),loc.getX(),it.getY(),it.getX());
                    break;
            }
            Block block = location.getBlock();
            block.setType(material);
        }

        return false;
    }
}
