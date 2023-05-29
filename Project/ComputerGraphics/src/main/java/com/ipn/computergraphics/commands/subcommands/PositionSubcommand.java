package com.ipn.computergraphics.commands.subcommands;

import com.ipn.computergraphics.math.Vector3;
import com.ipn.computergraphics.utils.Message;
import com.ipn.computergraphics.models.Pair;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PositionSubcommand extends Subcommand{

    @Inject
    private JavaPlugin plugin;

    public static Map<UUID, Pair> positions = new HashMap();
    protected PositionSubcommand(){
        super("pos",2);
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        plugin.getLogger().info(args[0]);

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Pair pair = new Pair();
        if(positions.get(uuid) != null) pair = positions.get(uuid);

        if(args[0].equals("show")){
            if(pair.getPos1() != null  && pair.getPos2() == null){
                player.sendMessage(Message.format(
                        "&a&m&l=============================================\n"+
                                "&bShowing Position 1 "+pair.getPos1().toString()+"\n"+
                                "&a&m&l=============================================\n"));
                Pair finalPair = pair;
                new BukkitRunnable(){
                    private int t = 0;
                    @Override
                    public void run(){
                        if(t>=10){
                            cancel();
                        }
                        ++t;
                        player.spawnParticle(Particle.HEART,new Location(player.getWorld(), finalPair.getPos1().getX(), finalPair.getPos1().getY(), finalPair.getPos1().getZ()),0);
                    }
                }.runTaskTimer(plugin,20L,20L);
            }
            else if(pair.getPos2() != null && pair.getPos1() == null){
                player.sendMessage(Message.format(
                        "&a&m&l=============================================\n"+
                                "&bShowing Position 2 "+pair.getPos2().toString()+"\n"+
                                "&a&m&l=============================================\n"));
                Pair finalPair2 = pair;
                new BukkitRunnable(){
                    private int t = 0;
                    @Override
                    public void run(){
                        if(t>=10){
                            cancel();
                        }
                        ++t;
                        player.spawnParticle(Particle.HEART,new Location(player.getWorld(), finalPair2.getPos2().getX(), finalPair2.getPos2().getY(), finalPair2.getPos2().getZ()),0);
                    }
                }.runTaskTimer(plugin,20L,20L);
            }
            else if(pair.getPos1() != null && pair.getPos2() != null){
                player.sendMessage(Message.format(
                        "&a&m&l=============================================\n" +
                                "&bShowing Region inside Position 1 "+pair.getPos1().toString()+"\n" +
                                "&band Position 2 " + pair.getPos2().toString()+"\n"+
                                "&a&m&l=============================================\n"));
                Vector3 vi = pair.getPos1().getMinimum(pair.getPos2());
                Vector3 vj = pair.getPos1().getMaximum(pair.getPos2());
                new BukkitRunnable(){
                    private int t = 0;
                    @Override
                    public void run(){
                        if(t>=20){
                            cancel();
                        }
                        ++t;
                        for (double i = vi.getX(); i <= vj.getX(); i++) {
                            for (double j = vi.getY(); j <= vj.getY() ; j++) {
                                for (double k = vi.getZ(); k <= vj.getZ() ; k++) {
                                    //player.sendMessage(i+" "+j+" "+k);
                                    player.spawnParticle(Particle.DRAGON_BREATH,new Location(player.getWorld(),i+0.5,j+0.5,k+0.5),0);
                                }
                            }
                        }
                    }
                }.runTaskTimer(plugin,20L,20L);
            }
            return false;
        }
        if(args[0].equals("reset")){
            positions.put(uuid,null);
            player.sendMessage(Message.format(
                    "&a&m&l=============================================\n" +
                            "&bReset positions\n"+
                            "&a&m&l=============================================\n"));
            return false;
        }

        int numberPos = Integer.parseInt(args[0]);

        Location loc = player.getLocation();
        Vector3 blockVector3D = new Vector3(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
        switch (numberPos){
            case 1: pair.setPos1(blockVector3D); break;
            case 2: pair.setPos2(blockVector3D); break;
            default: break;
        }
        positions.put(uuid,pair);
        if(pair.getPos1() != null  && pair.getPos2() == null)
          player.sendMessage(Message.format(
                  "&a&m&l=============================================\n"+
                        "&bPosition 1 set to "+pair.getPos1().toString()+"\n"+
                          "&a&m&l=============================================\n"));
        else if(pair.getPos2() != null && pair.getPos1() == null)
            player.sendMessage(Message.format(
                    "&a&m&l=============================================\n" +
                            "&bPosition 2 set to "+pair.getPos2().toString()+"\n"+
                            "&a&m&l=============================================\n"));
        else if(pair.getPos1() != null && pair.getPos2() != null){
            player.sendMessage(Message.format(
                    "&a&m&l=============================================\n"+
                            "&bPosition 1 set to "+pair.getPos1().toString()+"\n"+
                            "&bPosition 2 set to "+pair.getPos2().toString()+"\n"+
                            "&a&m&l=============================================\n"));
        }
        return false;
    }
}
