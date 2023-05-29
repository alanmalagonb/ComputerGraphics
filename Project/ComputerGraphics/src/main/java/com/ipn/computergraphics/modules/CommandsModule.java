package com.ipn.computergraphics.modules;

import com.ipn.computergraphics.commands.MainCommand;
import com.ipn.computergraphics.commands.subcommands.*;
import team.unnamed.inject.Binder;
import team.unnamed.inject.Module;

public class CommandsModule implements Module {


    @Override
    public void configure(Binder binder) {
        binder.bind(MainCommand.class).singleton();
        binder.bind(HelpSubcommand.class).singleton();
        binder.bind(PositionSubcommand.class).singleton();
        binder.bind(TransformSubcommand.class).singleton();
        binder.bind(LineSubcommand.class).singleton();
        binder.bind(CircleSubcommand.class).singleton();
        binder.bind(CurveSubcommand.class).singleton();
        binder.bind(ParticleSubcommand.class).singleton();
    }
}
