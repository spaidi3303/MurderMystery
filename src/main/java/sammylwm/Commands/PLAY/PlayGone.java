package sammylwm.Commands.PLAY;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sammylwm.Events.Role;
import sammylwm.ISCOREAPI;
import sammylwm.MurderMysteryLwm;

public class PlayGone {
    private static MurderMysteryLwm plugin = MurderMysteryLwm.getPlugin();

    public static void ScoreboardPlay(Player player, String role) {
        Role metarole = new Role();
        ISCOREAPI board = new ISCOREAPI();
        board.createObjective(player.getName() + "_play", "§aНазвание карты");
        int PlayerCount = (Bukkit.getWorld("murder")).getPlayers().size();
        board.GetSetScore("--------------------", 4);
        board.GetSetScore("Мирных жителей: " + (PlayerCount-1), 3);
        board.GetSetScore("Твоя роль: " + metarole.read(player) , 2);
        board.GetSetScore("---------------------" , 1);
        board.setScoreboard(player);
        timer_metch(player);
    }

    public static void timer_metch(Player player){
        Role metarole = new Role();
        new BukkitRunnable() {
            int timer = 11;
            @Override
            public void run() {
                timer--;
                if (timer <= 5){
                    player.sendMessage("MurderMystery* убийца получит меч через: " + timer + " сек.");
                }
                if (timer == 1){
                    player.sendMessage("MurderMystery* убийца получил меч!");
                    player.sendMessage(metarole.read(player));
                    if (metarole.read(player).equals("killer")){
                        GiveMetch.Give_metch(player);
                    }
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }

}