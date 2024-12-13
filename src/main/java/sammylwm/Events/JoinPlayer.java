package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sammylwm.Commands.world_okey;
import sammylwm.MurderMysteryLwm;

import java.util.Arrays;

public class JoinPlayer implements Listener {
    static MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        // Player player = (Player) event.getPlayer();

    }

    // Выход в лобби
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String FromWorld = event.getFrom().getName();
        String toWorld = player.getWorld().getName();
        if (FromWorld.equals("murder") && !toWorld.equals("murder")) {
            FuncLeave.CheckLeave(player, (MurderMysteryLwm) plugin);
        }

    }

    @EventHandler
    public void PlayerLeaveServer(PlayerQuitEvent event) {
        String world = event.getPlayer().getWorld().getName();
        Player player = event.getPlayer();
        Location location = new Location(Bukkit.getWorld("world"), 774, 74, 403);
        player.teleport(location);
        if (world.equals("murder")) {
            FuncLeave.CheckLeave(player, (MurderMysteryLwm) plugin);
        }

    }


    // Заход в портал
    @EventHandler
    private void PlayerInzone(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        if (Check_block(from) && Check_block(to)) {
            double x = 784;
            double y = 16;
            double z = 356;

            Location location = new Location(Bukkit.getWorld("murder"), x, y, z);
            player.teleport(location);
            ItemStack item = new ItemStack(Material.RED_BED);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(131123);
            meta.setDisplayName("§cВыйти из игры");
            item.setItemMeta(meta);
            player.getInventory().setItem(8, item);
            JoinNewPlayer.Board(player);

        }

    }

    // Отмена перетаскивания кровати в игре
    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null
                && e.getCurrentItem().hasItemMeta()
                && e.getCurrentItem().getItemMeta().hasCustomModelData()
                && e.getCurrentItem().getItemMeta().getCustomModelData() == 131123) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void ClickInter(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!world_okey.worldOkey(player)) {
            return;
        }
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasCustomModelData()) {
                if (itemInHand.getItemMeta().getCustomModelData() == 131123) {
                    e.setCancelled(true);
                    Location location = new Location(Bukkit.getWorld("world"), 774, 74, 403);
                    player.teleport(location);
                    FuncLeave.CheckLeave(player, (MurderMysteryLwm) plugin);
                }
            }
        }
    }


    @EventHandler
    public void QClick(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (!world_okey.worldOkey(player)) {
            return;
        }
        ItemStack droppedItem = e.getItemDrop().getItemStack();

        if (droppedItem.hasItemMeta() && droppedItem.getItemMeta().hasCustomModelData()) {
            if (droppedItem.getItemMeta().getCustomModelData() == 131123) {
                e.setCancelled(true);
            }
        }
    }


    private boolean Check_block(Location location) {
        double x1 = 772;
        double y1 = 71;
        double z1 = 407;
        double x2 = 770;
        double y2 = 73;
        double z2 = 409;

        double[] x = {x1, x2};
        Arrays.sort(x);
        x[1] = x[1] + 1;
        double[] y = {y1, y2};
        Arrays.sort(y);
        y[1] = y[1] + 1;
        double[] z = {z1, z2};
        Arrays.sort(z);
        z[1] = z[1] + 1;
        return location.getX() >= x[0] && location.getX() <= x[1] &&
                location.getY() >= y[0] && location.getY() <= y[1] &&
                location.getZ() >= z[0] && location.getZ() <= z[1];
    }


}
