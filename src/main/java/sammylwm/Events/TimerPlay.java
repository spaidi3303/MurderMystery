package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sammylwm.MurderMysteryLwm;

public class TimerPlay {
    private int[] second = new int[1]; // Счётчик времени
    private BukkitRunnable task; // Переменная для управления задачей
    static MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    public void startTimer(BossBar bossbar) {
        second[0] = 61;

        task = new BukkitRunnable() {
            @Override
            public void run() {
                second[0] -= 1;

                bossbar.setTitle("До конца: " + second[0] + " секунд");
                bossbar.setProgress(Math.max(0.0, (double) second[0] / 60));

                if (second[0] <= 0) {
                    stopTimer(bossbar);

                }
            }
        };

        task.runTaskTimer(plugin, 0, 20);
    }

    public void stopTimer(BossBar bossbar) {
        if (task != null && !task.isCancelled()) {
            task.cancel();
            task = null;

            for (Player player : (Bukkit.getWorld("murder").getPlayers())) {
                bossbar.removePlayer(player);
            }
        }
    }

}

