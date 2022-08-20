package cyci.org.phil.purge.utils;

import cyci.org.phil.purge.player.PurgePlayerRegistry;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 2:05 PM
 */
public class SaveTimer extends BukkitRunnable {
    private final FileConfiguration file;

    private final int interval = 300;

    int time;

    public SaveTimer() {
        this.file = FileUtil.getFile("Players.yml");
        this.time = 0;
    }

    public void run() {
        this.time++;
        if (this.time % 300 == 0) {
            PurgePlayerRegistry.getPlayers().forEach(player -> {
                //this.file.set(player.getUUID() + ".Votes", Integer.valueOf(player.getVotes()));

            });
            FileUtil.saveFile(this.file, "Players.yml");
        }
    }
}
