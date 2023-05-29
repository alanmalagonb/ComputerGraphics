package com.ipn.computergraphics.models;

import com.ipn.computergraphics.CGPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Effects {

    private CGPlugin plugin;
    private int taskID;
    private final Player player;

    public Effects(Player player) {
        this.player = player;
    }

    // Helix-like shape that oscillates along the y-axis while it moves along a circular path in the x-z plane
    public void startTotem() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CGPlugin.getPlugin(CGPlugin.class), new Runnable() {
            double t = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }
                t += Math.PI / 16;

                loc = player.getLocation();

                first = loc.clone().add(Math.cos(t), Math.sin(t) + 1, Math.sin(t));
                second = loc.clone().add(Math.cos(t + Math.PI), Math.sin(t) + 1, Math.sin(t + Math.PI));

                player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                player.getWorld().spawnParticle(Particle.TOTEM, second, 0);

            }
        }, 0, 1);
    }

    public void startButterfly(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CGPlugin.getPlugin(CGPlugin.class), new Runnable() {

            Location loc;
            ParticleData particle = new ParticleData(player.getUniqueId());
            @Override
            public void run() {
                if(!particle.hasID()){
                    particle.setID(taskID);
                }

                loc = player.getLocation();
                loc.add(0,1,0);

                butterflyCurve(loc);
            }
        },0,5);
    }

    public void startStar(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CGPlugin.getPlugin(CGPlugin.class), new Runnable() {

            Location loc;
            ParticleData particle = new ParticleData(player.getUniqueId());
            @Override
            public void run() {
                if(!particle.hasID()){
                    particle.setID(taskID);
                }

                loc = player.getLocation();
                loc.add(0,3,0);

                hypotrochoid(loc);
            }
        },0,1);
    }

    public void startFire(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CGPlugin.getPlugin(CGPlugin.class), new Runnable() {

            Location loc;
            ParticleData particle = new ParticleData(player.getUniqueId());
            @Override
            public void run() {
                if(!particle.hasID()){
                    particle.setID(taskID);
                }

                loc = player.getLocation();
                loc.add(0,1,0);

                helix(loc);

            }
        },0,1);
    }

    public void startLifeSaver(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CGPlugin.getPlugin(CGPlugin.class), new Runnable() {
            double t = 0;
            Location loc;
            ParticleData particle = new ParticleData(player.getUniqueId());
            @Override
            public void run() {
                if(!particle.hasID()){
                    particle.setID(taskID);
                }
                t+= Math.PI/16;

                loc = player.getLocation();
                loc.add(0,1,0);

                torus(loc);

            }
        },0,5);
    }

    public static void butterflyCurve(Location origin) {
        Vector directionMovement = origin.getDirection();
        Vector perpendicular = directionMovement.crossProduct(new Vector(0,1,0)).normalize();
        for (double t = -Math.PI; t <= Math.PI; t += 0.01) {
            double scale = 1.5;
            double x = origin.getX() + perpendicular.getX() * Math.sin(t) * (Math.exp(Math.cos(t)) - scale * Math.cos(4 * t) - Math.pow(Math.sin(t / 12), 5));
            double y = origin.getY() + Math.cos(t) * (Math.exp(Math.cos(t)) - scale * Math.cos(4 * t) - Math.pow(Math.sin(t / 12), 5));
            double z = origin.getZ() + perpendicular.getZ() * Math.sin(t) * (Math.exp(Math.cos(t)) - scale * Math.cos(4 * t) - Math.pow(Math.sin(t / 12), 5));
            Location particleLocation = new Location(origin.getWorld(), x, y, z);
            origin.getWorld().spawnParticle(Particle.DRAGON_BREATH, particleLocation,0);
        }
    }

    public static void hypotrochoid(Location origin) {
        double R = 0.5; // radius
        double r = 0.3; // radius
        double d = 0.5; // diameter

        Vector directionMovement = origin.getDirection();
        Vector perpendicular = directionMovement.crossProduct(new Vector(0,1,0)).normalize();

        for (double t = 0; t <= 100; t += 1) {
            double x = origin.getX() + perpendicular.getX() * ( (R - r) * Math.cos(t) + d * Math.cos(((R-r)*t)/r));
            double y = origin.getY() + (R - r) * Math.sin(t) - d * Math.sin(((R-r)*t)/r);
            double z = origin.getZ() + perpendicular.getZ() * ( (R - r) * Math.cos(t) + d * Math.cos(((R-r)*t)/r));

            Location particleLocation = new Location(origin.getWorld(), x, y, z);

            int red=255, green=234, blue=0;
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1.0F);
            origin.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1,dustOptions);
        }
    }

    public static void helix(Location origin) {
        double rings = 10;
        double height = Math.PI/2;
        for (double t = -height; t <= height; t += 0.01) {
            double radius = 1;
            double x = origin.getX() + radius * Math.cos(rings * t);
            double y = origin.getY() + t;
            double z = origin.getZ() + radius * Math.sin(rings * t);
            Location particleLocation = new Location(origin.getWorld(), x, y, z);
            origin.getWorld().spawnParticle(Particle.FLAME, particleLocation, 0,255,255,102);
        }
    }

    public static void torus(Location origin) {
        double R = 0.5; // radius of torus
        double r = 0.035; // radius of cross-section
        double step = 0.25; // step size for parameterization
        int colorIndex = 1,counter =0;

        for (double u = 0; u < 2 * Math.PI; u += step) {
            for (double v = 0; v < 2 * Math.PI; v += step) {
                double x = origin.getX() + (R + r * Math.cos(v)) * Math.cos(u);
                double y = origin.getY() + r * Math.sin(v);
                double z = origin.getZ() + (R + r * Math.cos(v)) * Math.sin(u);

                Location particleLocation = new Location(origin.getWorld(), x, y, z);

                int red, green, blue;

                if (colorIndex == 1) {
                    red = 255;
                    green = 0;
                    blue = 0;

                } else {
                    red = 255;
                    green = 255;
                    blue = 255;
                }
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1.0F);
                origin.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1,dustOptions);
            }
            if(counter == 1){
                if(colorIndex == 1)
                    colorIndex = 0;
                else
                    colorIndex = 1;
                counter=0;
            }
            counter++;
        }
    }
}
