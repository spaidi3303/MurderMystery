package sammylwm.Commands.PLAY;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import sammylwm.Events.Role;

public class KillPlayer implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        // Проверяем, если атакующий и цель игроки
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player target = (Player) event.getEntity();
            Role metarole = new Role();
            String role_damager = metarole.read(damager);
            String role_target = metarole.read(target);

            if (role_damager.equals("killer")) {
                if (damager.getInventory().getItemInMainHand().getType().toString().endsWith("_SWORD")) {
                    target.setGameMode(GameMode.SPECTATOR);
                    // Исправлено: заменен plugin1 на правильный класс
                    sammylwm.ISCOREAPI board = new sammylwm.ISCOREAPI();
                    for (Player player : Bukkit.getWorld("murder").getPlayers()) {
                        board.EditScore(player);
                    }
                    if (role_target.equals("detective")) {
                        // дроп лука
                        Entity entity = target.getWorld().spawnEntity(target.getLocation(),
                                EntityType.ARMOR_STAND);
                        entity.customName(Component.text("villager with custom name"));
                        entity.setCustomNameVisible(true);
                    }

                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
        
}
