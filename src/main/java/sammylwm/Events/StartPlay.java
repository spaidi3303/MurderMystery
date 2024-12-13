package sammylwm.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StartPlay {
    static TimerPlay timerManager = new TimerPlay();
    private static final List<Integer> usedIndices = new ArrayList<>();

    public static void RandomPlace() {
        World world = Bukkit.getWorld("murder");

        Location[] locations = new Location[]{
                new Location(world, 774, 25, 491),
                new Location(world, 763, 25, 482),
                new Location(world, 758, 25, 493),
                new Location(world, 763, 25, 5081),
                new Location(world, 766, 25, 468),
                new Location(world, 776, 25, 465),
                new Location(world, 792, 25, 460),
                new Location(world, 820, 25, 454),
                new Location(world, 818, 27, 442),
                new Location(world, 808, 23, 418)
        };
        Random rand = new Random();
        int random_place;
        for (Player player : world.getPlayers()) {
            do {
                random_place = rand.nextInt(locations.length);
            } while (usedIndices.contains(random_place));
            usedIndices.add(random_place);
            player.teleport(locations[random_place]);
            player.getInventory().clear();
            BossbarStartPlay.BossbarPlay(player);
        }
        timerManager.startTimer(BossbarStartPlay.bossBar);
    }
    public static void resetUsedIndices() {
        usedIndices.clear();
    }



















}

