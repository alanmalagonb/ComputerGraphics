package com.ipn.computergraphics.commands.subcommands;


import com.ipn.computergraphics.utils.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public class HelpSubcommand extends Subcommand{

    @Inject
    private JavaPlugin plugin;

    protected HelpSubcommand(){
        super("help",1);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args){
        sender.sendMessage(Message.format(
                "&a&m&l=============================================\n" +
                "&bComputer Graphics\n" +
                "&bMalagon Baeza Alan Adrian\n" +
                "&b6CV1\n\n" +
                "/cg pos (1/2) : Puts position 1/2 at player's current position\n\n" +
                "/cg pos show : Shows particles inside the region inside P1 and P2\n\n" +
                "/cg pos reset : Reset the positions P1 and P2\n\n" +
                "/cg transform type x y z: Transform the region inside P1 and P2 with selected matrix (t,r,rh,s) " +
                "t:translate r: rotate (xyz axis) rh: rotateHere (player position as axis) s:scale\n\n" +
                "/cg line (b/d) MATERIAL_NAME: Create a line from P1 to P2 with selected algorithm (Bresenham/DDA)\n\n" +
                "/cg circle (b/m) MATERIAL_NAME radius axis: Create a circle in player's current position as center " +
                "with selected algorithm (Bresenham/Mid-Point) on the selected axis (xy/xz/zy)\n\n" +
                "/cg help : Help\n\n" +
                        "&a&m&l=============================================\n"));
        return true;
    }
}
