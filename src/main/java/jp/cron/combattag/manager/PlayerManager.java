package jp.cron.combattag.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private Map<UUID, PlayerEntity> players = new HashMap<>();

    public PlayerManager() {
    }

    public PlayerEntity getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public PlayerEntity getPlayer(UUID uuid) {
        if (players.containsKey(uuid)) {
            return players.get(uuid);
        } else {
            PlayerEntity e = new PlayerEntity();
            players.put(uuid, e);
            return e;
        }
    }
}
