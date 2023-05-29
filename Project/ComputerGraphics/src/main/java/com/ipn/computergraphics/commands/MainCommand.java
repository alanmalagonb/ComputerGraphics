package com.ipn.computergraphics.commands;

import com.ipn.computergraphics.CGPlugin;
import com.ipn.computergraphics.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainCommand implements TabExecutor {
    @Inject
    private CGPlugin plugin;
    @Inject
    private HelpSubcommand helpSubcommand;
    @Inject
    private PositionSubcommand positionSubcommand;
    @Inject
    private TransformSubcommand transformSubcommand;

    @Inject
    private LineSubcommand lineSubcommand;
    @Inject
    private CircleSubcommand circleSubcommand;

    @Inject
    private CurveSubcommand curveSubcommand;

    @Inject
    private ParticleSubcommand particleSubcommand;

    private final List<Subcommand> subcommands = new ArrayList<>();

    public void start(){
        Objects.requireNonNull(plugin.getCommand("cg")).setExecutor(this);
        subcommands.add(helpSubcommand);
        subcommands.add(positionSubcommand);
        subcommands.add(transformSubcommand);
        subcommands.add(lineSubcommand);
        subcommands.add(circleSubcommand);
        subcommands.add(curveSubcommand);
        subcommands.add(particleSubcommand);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            helpSubcommand.execute(sender, args);
            return true;
        }

        String name = args[0];
        Subcommand subCommand = subcommands.stream().filter(subC ->
                subC.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (subCommand == null || !subCommand.argsLengthMatches(args.length)) {
            helpSubcommand.execute(sender, args);
            return true;
        }

        if (subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length))){
            helpSubcommand.execute(sender, args);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        for (Subcommand subcommand : subcommands) {
            if (args.length == 1) list.add(subcommand.getName());
        }
        return list;
    }
}
