package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class JoinNewPlayer {
    static BossBar bossBar = Bukkit.createBossBar("§lMurderMystery", BarColor.PURPLE, BarStyle.SOLID);
    static sammylwm.Events.TimerManager timerManager = new sammylwm.Events.TimerManager();

    public static void Board(Player player) {
        bossBar.addPlayer(player);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        if (board.getObjective("starts") != null) {
            board.getObjective("starts").unregister();
        }
        Objective objective = board.registerNewObjective("starts", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§aНазвание карты");

        Score PlayersThis = objective.getScore("Игроков: ");
        int PlayerCount = (Bukkit.getWorld("murder")).getPlayers().size();
        PlayersThis.setScore(PlayerCount);
        Score timer = objective.getScore("Минимум игроков для начала: ");
        timer.setScore(2);

        for (Player players : (Bukkit.getWorld("murder")).getPlayers()) {
            PlayersThis.setScore(PlayerCount);
            players.setScoreboard(board);
        }
        if (PlayerCount == 2){
            timerManager.startTimer();
        }
    }



}
