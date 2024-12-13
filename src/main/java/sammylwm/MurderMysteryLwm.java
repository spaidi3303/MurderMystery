package sammylwm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MurderMysteryLwm extends JavaPlugin {
    private static MurderMysteryLwm plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new sammylwm.Events.JoinPlayer(), this);
        Bukkit.getPluginManager().registerEvents(new sammylwm.Commands.PLAY.KillPlayer(), this);
        Objects.requireNonNull(getCommand("hub")).setExecutor(new sammylwm.Events.Lobby());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MurderMysteryLwm getPlugin(){
        return plugin;
    }

}
