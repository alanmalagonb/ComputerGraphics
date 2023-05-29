package com.ipn.computergraphics.utils;

import org.bukkit.ChatColor;

public class Message {
    public static String format(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
