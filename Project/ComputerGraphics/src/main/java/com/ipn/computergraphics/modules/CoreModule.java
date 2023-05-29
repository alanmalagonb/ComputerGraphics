package com.ipn.computergraphics.modules;

import com.ipn.computergraphics.CGPlugin;
import com.ipn.computergraphics.commands.subcommands.ParticleSubcommand;
import com.ipn.computergraphics.models.Effects;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Binder;
import team.unnamed.inject.Module;
public class CoreModule implements Module{

    private final CGPlugin plugin;

    public CoreModule(CGPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(JavaPlugin.class).toInstance(plugin);
        binder.bind(CGPlugin.class).toInstance(plugin);

        binder.install(new CommandsModule());
    }
}
