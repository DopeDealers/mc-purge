package cyci.org.phil.purge.player;

import com.google.common.collect.ImmutableList;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 1:58 PM
 */
public class PurgePlayerRegistry {
    private static final Map<String, PurgePlayerHandler> players = new HashMap<>();

    public static List<PurgePlayerHandler> getPlayers() {
        return (List<PurgePlayerHandler>) ImmutableList.copyOf(players.values());
    }
    public static PurgePlayerHandler registerPlayer(PurgePlayerHandler player) {
        return players.put(player.getName(), player);
    }
    public static PurgePlayerHandler removePlayer(PurgePlayerHandler player) {
        return players.remove(player.getName());
    }
    public static boolean isPlayer(Player name) {
        return players.containsKey(name.getName());
    }
    public static PurgePlayerHandler getPlayer(Player player) {
        return isPlayer(player) ? players.get(player.getName()) : null;
    }
}
