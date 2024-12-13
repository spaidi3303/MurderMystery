package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossbarStartPlay {
    static BossBar bossBar = Bukkit.createBossBar("До конца: 5 минут", BarColor.PURPLE, BarStyle.SOLID);

    public static void BossbarPlay(Player player) {
        JoinNewPlayer.bossBar.removePlayer(player);
        bossBar.addPlayer(player);
    }

}
