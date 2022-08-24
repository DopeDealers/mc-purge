package org.cyci.phil.purge.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Tue - 23/Aug/2022 - 2:27 PM
 */
public class PurgePlaceholder extends PlaceholderExpansion  {
    @Override
    public @NotNull String getIdentifier() {
        return "purge";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Phil#0004";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }
}
