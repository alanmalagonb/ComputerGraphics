package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Line;
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


public class LineSubcommand extends Subcommand{

    @Inject
    private JavaPlugin plugin;

    protected LineSubcommand(){
        super("line",3);
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return false;
        String algorithm = args[0];
        Material material = Material.getMaterial(args[1]);

        if(algorithm.equals("b")) {
            sender.sendMessage(Message.format(
                    "&a&m&l=============================================\n" +
                            "&bBresenham Line Created from "+pair.getPos1().toString()+" to "+pair.getPos2().toString()+"\n" +
                            "&a&m&l=============================================\n"));
            List<List<Double>> ListOfPoints = Line.BresenhamLine3D(pair.getPos1(), pair.getPos2());
            for (List<Double> it : ListOfPoints) {
                Location loc = new Location(player.getWorld(), it.get(0), it.get(1), it.get(2));
                Block block = loc.getBlock();
                block.setType(material);
            }
        }else if(algorithm.equals("d")){
            sender.sendMessage(Message.format(
                    "&a&m&l=============================================\n" +
                            "&bDDA Line Created from "+pair.getPos1().toString()+" to "+pair.getPos2().toString()+"\n" +
                            "&a&m&l=============================================\n"));
            List<Vector3> points = Line.DDALine3D(pair.getPos1(),pair.getPos2());
            for(Vector3 v: points){
                Location loc = new Location(player.getWorld(), v.getX(), v.getY(), v.getZ());
                Block block = loc.getBlock();
                block.setType(material);
            }
        }

        return false;
    }
}
