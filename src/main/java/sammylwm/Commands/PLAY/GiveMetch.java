package sammylwm.Commands.PLAY;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sammylwm.MurderMysteryLwm;

public class GiveMetch  implements Listener {
    MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    public static void Give_metch(Player player){
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("&4Меч убийцы");
        item.setItemMeta(meta);
        player.getInventory().setItem(0, item);
    }

}
