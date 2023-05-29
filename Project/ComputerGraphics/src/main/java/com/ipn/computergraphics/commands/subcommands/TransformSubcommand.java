package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Transform;
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
import java.util.UUID;
public class TransformSubcommand extends Subcommand{
    @Inject
    private JavaPlugin plugin;

    protected TransformSubcommand() {
        super("transform",5);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        String type = args[0];
        Vector3 vector3 = new Vector3(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]));
        switch (type){
            case "t": translate(sender, vector3); break;
            case "r": rotate(sender,vector3); break;
            case "rh": rotateHere(sender,vector3); break;
            case "s": scale(sender,vector3); break;
            default: break;
        }
        return false;
    }

    private void scale(CommandSender sender, Vector3 vector3) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return;

        Vector3 vi = pair.getPos1().getMinimum(pair.getPos2());
        Vector3 vj = pair.getPos1().getMaximum(pair.getPos2());

        Transform scale = new Transform();
        scale.scaling(vector3);

        Transform translateO = new Transform();
        translateO.translation(new Vector3(-vi.getX(),-vi.getY(),-vi.getZ()));
        Transform translateP = new Transform();
        translateP.translation(vi);

        for (double i = vi.getX(); i <= vj.getX(); i++) {
            for (double j = vi.getY(); j <= vj.getY() ; j++) {
                for (double k = vi.getZ(); k <= vj.getZ() ; k++) {
                    Location loc = new Location(player.getWorld(),i,j,k);
                    Block block = loc.getBlock();
                    Material material = block.getType();
                    block.setType(Material.AIR);
                    Vector3 scalingPoint = translateP.multiply(scale.multiply(translateO.multiply(new Vector3(i,j,k))));
                    player.sendMessage(material + " "+scalingPoint .toString());
                    Location newLoc = new Location(player.getWorld(),scalingPoint.getX(),scalingPoint.getY(),scalingPoint.getZ());
                    newLoc.getBlock().setType(material);
                }
            }
        }
        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                        "&bRegion from "+vi+" to "+vj+" scaled \n"+
                        "&bx:"+vector3.getX()+", y:"+vector3.getY()+" and z:"+vector3.getZ()+"\n"+
                        "&a&m&l=============================================\n"));
    }

    private void rotateHere(CommandSender sender, Vector3 vector3) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return;

        Vector3 vi = pair.getPos1().getMinimum(pair.getPos2());
        Vector3 vj = pair.getPos1().getMaximum(pair.getPos2());

        Transform transformX = new Transform();
        transformX.rotation(vector3.getX(),"x");
        Transform transformY = new Transform();
        transformY.rotation(vector3.getY(),"y");
        Transform transformZ = new Transform();
        transformZ.rotation(vector3.getZ(),"z");

        Location playerLoc = player.getLocation();
        Vector3 h = new Vector3(playerLoc.getX(), playerLoc.getY(), playerLoc.getZ());

        Transform translateO = new Transform();
        translateO.translation(new Vector3(-h.getX(),-h.getY(),-h.getZ()));


        Transform translateP = new Transform();
        translateP.translation(h);

        for (double i = vi.getX(); i <= vj.getX(); i++) {
            for (double j = vi.getY(); j <= vj.getY() ; j++) {
                for (double k = vi.getZ(); k <= vj.getZ() ; k++) {
                    Location loc = new Location(player.getWorld(),i,j,k);
                    Block block = loc.getBlock();
                    Material material = block.getType();
                    block.setType(Material.AIR);
                    Vector3 rotationHere = translateP.multiply(transformZ.multiply(transformY.multiply(transformX.multiply(translateO.multiply(new Vector3(i,j,k))))));
                    player.sendMessage(material + " "+rotationHere.toString());
                    Location newLoc = new Location(player.getWorld(),rotationHere.getX(),rotationHere.getY(),rotationHere.getZ());
                    newLoc.getBlock().setType(material);
                }
            }
        }
        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                        "&bRegion from "+vi+" to "+vj+" rotated in "+playerLoc+" axis\n"+
                        "&bx:"+vector3.getX()+"°, y:"+vector3.getY()+"° and z:"+vector3.getZ()+"°\n"+
                        "&a&m&l=============================================\n"));
    }

    private void rotate(CommandSender sender, Vector3 vector3) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return;

        Vector3 vi = pair.getPos1().getMinimum(pair.getPos2());
        Vector3 vj = pair.getPos1().getMaximum(pair.getPos2());

        Transform transformX = new Transform();
        transformX.rotation(vector3.getX(),"x");
        Transform transformY = new Transform();
        transformY.rotation(vector3.getY(),"y");
        Transform transformZ = new Transform();
        transformZ.rotation(vector3.getZ(),"z");


        for (double i = vi.getX(); i <= vj.getX(); i++) {
            for (double j = vi.getY(); j <= vj.getY() ; j++) {
                for (double k = vi.getZ(); k <= vj.getZ() ; k++) {
                    Location loc = new Location(player.getWorld(),i,j,k);
                    Block block = loc.getBlock();
                    if(block.getType().isAir()) continue;
                    Material material = block.getType();
                    block.setType(Material.AIR);
                    Vector3 rotation = transformZ.multiply(transformY.multiply(transformX.multiply(new Vector3(i,j,k))));
                    player.sendMessage(material + " "+rotation.toString());
                    Location newLoc = new Location(player.getWorld(),rotation.getX(),rotation.getY(),rotation.getZ());
                    newLoc.getBlock().setType(material);
                }
            }
        }
        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                        "&bRegion from "+vi+" to "+vj+" rotated in origin axis\n"+
                        "&bx:"+vector3.getX()+"°, y:"+vector3.getY()+"° and z:"+vector3.getZ()+"°\n"+
                        "&a&m&l=============================================\n"));
    }

    private void translate(CommandSender sender, Vector3 vector3) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(PositionSubcommand.positions.get(uuid) != null) pair = PositionSubcommand.positions.get(uuid);
        if(pair.getPos1() == null || pair.getPos2() == null) return;

        Vector3 vi = pair.getPos1().getMinimum(pair.getPos2());
        Vector3 vj = pair.getPos1().getMaximum(pair.getPos2());

        Transform transform = new Transform();
        transform.translation(vector3);

        for (double i = vi.getX(); i <= vj.getX(); i++) {
            for (double j = vi.getY(); j <= vj.getY() ; j++) {
                for (double k = vi.getZ(); k <= vj.getZ() ; k++) {
                    Location loc = new Location(player.getWorld(),i,j,k);
                    Block block = loc.getBlock();
                    if(block.getType().isAir()) continue;
                    Material material = block.getType();
                    block.setType(Material.AIR);
                    Vector3 translation = transform.multiply(new Vector3(i,j,k));
                    player.sendMessage(material + " "+translation.toString());
                    Location newLoc = new Location(player.getWorld(),translation.getX(),translation.getY(),translation.getZ());
                    newLoc.getBlock().setType(material);

                }
            }
        }
        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                        "&bRegion from "+vi+" to "+vj+" translated\n"+
                        "&bx:"+vector3.getX()+", y:"+vector3.getY()+" and z:"+vector3.getZ()+"\n"+
                        "&a&m&l=============================================\n"));
    }
}
