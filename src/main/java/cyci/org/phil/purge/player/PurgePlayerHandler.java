package cyci.org.phil.purge.player;

import cyci.org.phil.purge.GameStates;
import cyci.org.phil.purge.Purge;
import cyci.org.phil.purge.utils.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 1:50 PM
 */
public class PurgePlayerHandler {
    private final FileConfiguration file;
    private final UUID uuid;
    private String player;
    private String name;
    private boolean isMurderer;
    private int kills;

    public PurgePlayerHandler(Player player) {
        this.file = FileUtil.getFile("Players.yml");
        this.uuid = player.getUniqueId();
    }

    public String getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getKills() {
        return kills;
    }

    public boolean isMurderer() {
        return isMurderer;
    }
    public void setMurderer(boolean murdered) {
        this.isMurderer = murdered;
    }
    public void handler(Player p) {
        if (Purge.getInstance().getGamestate() == GameStates.WAITING_FOR_PLAYERS) {
            Purge.getInstance().setGamestate(GameStates.BEFORE_PURGE);
        }
    }
    public int addKills() {
        return kills + 1;
    }

    public final synchronized void save(boolean save) {
        this.file.set(getUUID() + ".murderer", isMurderer());
        this.file.set(getUUID() + "murderer.kills", getKills());
        if (save)
            FileUtil.saveFile(this.file, "Players.yml");
    }
}
