package sammylwm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import sammylwm.Events.Role;

public class ISCOREAPI {
    /* ISCOREAPI by Firebreath15 v1.5
     *
     * Written for private use. Do not duplicate or republish.
     */
    Scoreboard board;
    Objective obj;

    public ISCOREAPI() {
        this.board = Bukkit.getScoreboardManager().getNewScoreboard();
    }

    public void createObjective(String name, String displayName) {
        this.obj = this.board.registerNewObjective(name, "dummy");
        this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.obj.setDisplayName(displayName);

    }

    public void setScoreboard(Player p) {
        p.setScoreboard(this.board);
    }

    public void removeObjective(String obj) {
        if (board.getObjective(obj) != null) {
            this.board.getObjective(obj).unregister();
        }
    }

    public Scoreboard getPlayerScoreboard(Player p) {
        return p.getScoreboard();
    }

    public void removePlayerScoreboard() {
        board.getObjective(DisplaySlot.SIDEBAR).unregister();
    }

    public void GetSetScore(String getScore, int setScore) {
        this.obj.getScore(getScore).setScore(setScore);

    }

    public void EditScore(Player player){
        Role metarole = new Role();
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective(player.getName() + "_play");
        if (objective != null) {
            int PlayerCount = 0;
            for (Player player1 : (Bukkit.getWorld("murder")).getPlayers()) {
                String gamemode = player1.getGameMode().name();
                if (!gamemode.equals("SPECTATOR")){
                    PlayerCount++;
                }
            };
            if (PlayerCount == 0){
                // конец игры
            }

            for(String entry : scoreboard.getEntries()){
                scoreboard.resetScores(entry);
            }
            createObjective(player.getName() + "_play", "§aНазвание карты");
            GetSetScore("--------------------", 4);
            GetSetScore("Мирных жителей: " + (PlayerCount-1), 3);
            GetSetScore("Твоя роль: " + metarole.read(player) , 2);
            GetSetScore("---------------------" , 1);
            setScoreboard(player);

        }
    }


}