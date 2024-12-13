package sammylwm.Events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sammylwm.MurderMysteryLwm;

public class Role{

    public void create(String role, Player player){
        NamespacedKey roleKey = new NamespacedKey(MurderMysteryLwm.getPlugin(), "playerRole");

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        dataContainer.set(roleKey, PersistentDataType.STRING, role);
    }

    public String read(Player player){
        NamespacedKey roleKey = new NamespacedKey(MurderMysteryLwm.getPlugin(), "playerRole");

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        return dataContainer.get(roleKey, PersistentDataType.STRING);
    }

    public void dell(Player player){
        NamespacedKey roleKey = new NamespacedKey(MurderMysteryLwm.getPlugin(), "playerRole");

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        dataContainer.remove(roleKey);
    }
}
