package sammylwm.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import sammylwm.MurderMysteryLwm;

public class TimerManager {
    private BukkitRunnable task;
    static MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    public void startTimer() {

        task = new BukkitRunnable() {
            int timer = 31;

            @Override
            public void run() {
                timer--;
                for (Player player : Bukkit.getWorld("murder").getPlayers()) {
                    Scoreboard scoreboard = player.getScoreboard();
                    Objective objective = scoreboard.getObjective("starts");
                    if (objective != null) {
                        scoreboard.resetScores("Минимум игроков для начала: ");
                        Score score = objective.getScore("До начала: ");
                        score.setScore(timer);
                    }
                    int PlayerCount = (Bukkit.getWorld("murder")).getPlayers().size();
                    double chance_killer =  ((double) 1 / PlayerCount) * 100;
                    String chanceMessage = "Шанс стать убийцей: " + (int) chance_killer + "%";
                    player.sendActionBar(Component.text(chanceMessage));
                }
                if (timer == 0){
                    for (Player player : (Bukkit.getWorld("murder").getPlayers())) {
                        Scoreboard emptyScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                        player.setScoreboard(emptyScoreboard);
                    }

                    stopTimer();
                    Random_Role.CreateRole();

                    StartPlay.RandomPlace();
                }

            }
        };
        task.runTaskTimer(plugin, 20, 20);
    }

    public void stopTimer() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
            task = null;
            for (Player player : (Bukkit.getWorld("murder").getPlayers())) {
                Scoreboard scoreboard = player.getScoreboard();
                Objective objective = scoreboard.getObjective("starts");
                if (objective != null) {
                    Score score = objective.getScore("Минимум игроков для начала: ");
                    score.setScore(2);
                    scoreboard.resetScores("До начала: ");

                }
            }
        }
    }

}