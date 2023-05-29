package com.ipn.computergraphics.commands.subcommands;

import org.bukkit.command.CommandSender;

public abstract class Subcommand {

    private final String name;
    private final int argsLength;

    protected Subcommand(String name, int argsLength) {
        this.name = name;
        this.argsLength = argsLength;
    }

    protected Subcommand(String name, String permission, int argsLength) {
        this(name, argsLength);
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public String getName() {
        return this.name;
    }

    public boolean argsLengthMatches(int args) {
        return this.argsLength == args;
    }
}