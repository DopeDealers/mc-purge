package cyci.org.phil.purge.utils;

import cyci.org.phil.purge.Purge;
import cyci.org.phil.purge.config.Lang;
import jdk.jfr.internal.Logger;
import net.kyori.adventure.text.Component;
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
    public static int purgeTimer = Lang.PURGE_TIME_LIMIT.getInt() * 3600;
    public static boolean playersCanAttack = false;

    static boolean isPurgeStarted() {
        return purgeStarted;
    }

    static int getPurgeCountDown() {
        return purgeCountDown;
    }
    public static CountdownTimer purgeTimerCountdown = new CountdownTimer(Purge.getInstance(), purgeTimer,
            () -> Bukkit.getLogger().info("The purge hourly timer has begun"),
            () -> {
                //deal with all your after the countdown has ended shit
                purgeStarted = false;
            },
            (players) -> {
                //countdown method
            });

    public static void startCountDown(World world) {
        if (!purgeStarted) {
            CountdownTimer timer = new CountdownTimer(Purge.getInstance(),purgeCountDown,
                    () -> Bukkit.getLogger().info("Timer beginning"),
                    () -> {
                        world.getPlayers().forEach(message -> {
                            message.playSound(Objects.requireNonNull(message.getPlayer()).getLocation(), Sound.MUSIC_DISC_11, Float.MAX_VALUE, 1.0f);
                            message.sendMessage(C.c(Lang.PURGE_START.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})));
                            ActionBar ab = new ActionBar();
                            ab.sendTitle(message.getPlayer(), C.c(Lang.PURGE_START.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})), C.c("&cAll rules, claims, etc are null this point on until the end.."));
                            //purgeTimerCountdown.scheduleTimer();
                        });
                    },
                    (t) -> {
                        world.getPlayers().forEach(message -> {
                            message.sendMessage(C.c(Lang.PREFIX.getConfigValue(null) + " &7The purge will begin in.. &r" + t.getSecondsLeft()));
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
