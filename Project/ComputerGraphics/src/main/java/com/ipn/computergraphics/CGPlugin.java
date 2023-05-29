package com.ipn.computergraphics;

import com.ipn.computergraphics.commands.MainCommand;
import com.ipn.computergraphics.events.ClickEvent;
import com.ipn.computergraphics.events.Quit;
import com.ipn.computergraphics.models.GUI;
import com.ipn.computergraphics.modules.CoreModule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

import javax.inject.Inject;

public final class CGPlugin extends JavaPlugin {

    @Inject
    private MainCommand mainCommand;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("¡Computer Graphics Plugin Enabled!");
        try{
            Injector injector = Injector.create(new CoreModule(this));
            injector.injectMembers(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        start();

        GUI menu = new GUI();
        menu.register();

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new ClickEvent(),this);
        pm.registerEvents(new Quit(),this);
    }

    private void start() {
        mainCommand.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
