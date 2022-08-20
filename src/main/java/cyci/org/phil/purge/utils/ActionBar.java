package cyci.org.phil.purge.utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

import java.time.Duration;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 9:53 PM
 */

public class ActionBar {
    public void sendTitle(Audience target, Component input, Component subinput) {
        Title title = Title.title(input, subinput);
        target.showTitle(title);
    }
}
