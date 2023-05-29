package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Curve;
import com.ipn.computergraphics.math.Vector3;
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

public class CurveSubcommand extends Subcommand{

    @Inject
    private JavaPlugin plugin;

    protected CurveSubcommand(){
        super("curve",9);
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        // MATERIAL x y z x y z POINTS
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return false;
        Material material = Material.getMaterial(args[0]);

        Vector3 tangent1 = new Vector3(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]));
        Vector3 tangent2 = new Vector3(Double.parseDouble(args[4]),Double.parseDouble(args[5]),Double.parseDouble(args[6]));

        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                        "&bHermite Curve Created from "+pair.getPos1().toString()+" to "+pair.getPos2().toString()+"\n" +
                        "&a&m&l=============================================\n"));

        List<Vector3> ListOfPoints = Curve.hermiteCurve(pair.getPos1(),pair.getPos2(),tangent1,tangent2,Integer.parseInt(args[7]));
        for (Vector3 it : ListOfPoints) {
            Location loc = new Location(player.getWorld(), it.getX(), it.getY(), it.getZ());
            Block block = loc.getBlock();
            block.setType(material);
        }

        return false;
    }
}
