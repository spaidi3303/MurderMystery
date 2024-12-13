package sammylwm.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sammylwm.Commands.PLAY.PlayGone;

import java.time.Duration;
import java.util.Random;

public class Random_Role {

    public static void CreateRole() {
        int PlayerCount = (Bukkit.getWorld("murder")).getPlayers().size();
        final Random random = new Random();
        int killer = random.nextInt(PlayerCount) + 1;
        int detective = random.nextInt(PlayerCount) + 1;
        while(killer == detective){
            detective = random.nextInt(PlayerCount) + 1;
        }

        int a = 0;
        for (Player player : (Bukkit.getWorld("murder")).getPlayers()) {
            a++;
            String role = "";
            if (a == killer){
                role = "killer";
            } else if (a == detective){
                role = "detective";
            } else {
                role = "resident";
            }
            Role metarole = new Role();
            metarole.create(role, player);

            player.showTitle(Title.title(
                    Component.text("Игра началась!"),
                    Component.text(role),
                    Title.Times.times(Duration.ofSeconds(1),  Duration.ofSeconds(5), Duration.ofSeconds(1))));
            PlayGone.ScoreboardPlay(player, role);
        }

    }

}
