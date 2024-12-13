package sammylwm.Commands;

import org.bukkit.entity.Player;

public class world_okey {

    public static boolean worldOkey(Player player) {
        String world = player.getWorld().getName();
        if (world.equals("murder")) {
            return true;
        } else {
            return false;
        }
    }
}

