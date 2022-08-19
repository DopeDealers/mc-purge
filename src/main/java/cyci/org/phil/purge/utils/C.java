package cyci.org.phil.purge.utils;

import cyci.org.phil.purge.Purge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Thu - 18/Aug/2022 - 10:20 PM
 */
public class C {
    public static Component c(String input) {
        return (Component) LegacyComponentSerializer.legacy('&').deserialize(input);
    }
}
