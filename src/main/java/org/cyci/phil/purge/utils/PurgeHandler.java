package org.cyci.phil.purge.utils;

import com.ericdebouwer.regionclaimplus.RegionClaimPlus;
import org.bukkit.plugin.Plugin;
import org.cyci.phil.purge.Purge;
import org.cyci.phil.purge.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;

import java.util.Objects;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 1:00 PM
 */
public class PurgeHandler {

    public static boolean purgeStarted = false;
    public static int purgeCountDown = Lang.PURGE_COUNTDOWM.getInt();
    public static boolean playersCanAttack = false;

    public PurgeHandler() {
        purgeStarted = false;
    }

    static boolean isPurgeStarted() {
        return purgeStarted;
    }

    static int getPurgeCountDown() {
        return purgeCountDown;
    }

    public static void startCountDown() {
        if (!purgeStarted && Bukkit.getServer().getOnlinePlayers().size() < 2) {
            Plugin region = Bukkit.getPluginManager().getPlugin("RegionClaimPlus");
            CountdownTimer timer = new CountdownTimer(Purge.getInstance(),purgeCountDown,
                    () -> Bukkit.getLogger().info("Purge: Timer beginning 10s"),
                    () -> {
                        Bukkit.getWorlds().forEach(world -> {
                            world.getPlayers().forEach(message -> {
                                message.playSound(Objects.requireNonNull(message.getPlayer()).getLocation(), Sound.MUSIC_DISC_11, Float.MAX_VALUE, 1.0f);
                                message.sendMessage(C.c(Lang.PURGE_START.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})));
                                if (Lang.PURGE_ACTION_BARS.getBoolean()) {
                                    ActionBar ab = new ActionBar();
                                    ab.sendTitle(message.getPlayer(), C.c(Lang.PURGE_START.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})), C.c("&cAll rules, claims, etc are null this point on until the end.."));
                                }
                                new PurgeHourlyTimer().timer.scheduleTimer();
                                purgeStarted = true;
                                try {
                                    assert region != null;
                                    Bukkit.getPluginManager().disablePlugin(region);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        });
                    },
                    (t) -> {
                        Bukkit.getWorlds().forEach(world -> {
                                    world.getPlayers().forEach(message -> {
                                        message.sendMessage(C.c(Lang.PURGE_COUNTDOWN_MESSAGE_START.getConfigValue(new String[]{String.valueOf(t.getSecondsLeft()), Lang.PREFIX.getConfigValue(null)})));
                                    });
                                });
                        //purgeStarted = true;
                    });
            try {
                timer.scheduleTimer();
            } catch (Exception err) {
                Bukkit.getLogger().info(String.valueOf(err));

            }
        } else {

        }
    }
}
