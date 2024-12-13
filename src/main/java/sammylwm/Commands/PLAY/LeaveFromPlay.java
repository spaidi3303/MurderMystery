package sammylwm.Commands.PLAY;

import org.bukkit.entity.Player;
import sammylwm.Events.Role;

public class LeaveFromPlay {
    public static void LeavePlay(Player player){
        Role metarole = new Role();
        if (metarole.read(player) != null){
            String role = metarole.read(player);
            if (role.equals("killer")){
                System.out.println("killer");
                // конец игры
            } else if (role.equals("detective")) {
                System.out.println("detective");
                // обработка выхода детектива
            } else if (role.equals("resident")){
                // обработка выхода игрока
                System.out.println("resident");
            }
        }



    }

}
