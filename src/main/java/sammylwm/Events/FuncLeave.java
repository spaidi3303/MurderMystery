package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import sammylwm.Commands.PLAY.LeaveFromPlay;
import sammylwm.MurderMysteryLwm;

public class FuncLeave {
    static MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    public static void CheckLeave(Player player, MurderMysteryLwm plugin){
        LeaveFromPlay.LeavePlay(player);

        // очистить любой scoreboard
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        // проверка сколько в лобби
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            int playerCount = (Bukkit.getWorld("murder")).getPlayers().size();
            for (Player players : (Bukkit.getWorld("murder")).getPlayers()) {
                Scoreboard scoreboard = players.getScoreboard();
                Objective objective = scoreboard.getObjective("starts");
                if (objective != null) {
                    Score score = objective.getScore("Игроков: ");
                    score.setScore(playerCount);
                    if (playerCount < 2) {
                        JoinNewPlayer.timerManager.stopTimer();
                    }
                }
            }
        }, 1L);

        // очистить инвентарь
        player.getInventory().clear();

        // очистить роль
        Role metarole = new Role();
        metarole.dell(player);

        // очистка боссбаров
        JoinNewPlayer.bossBar.removePlayer(player);
        BossbarStartPlay.bossBar.removePlayer(player);

    }
}