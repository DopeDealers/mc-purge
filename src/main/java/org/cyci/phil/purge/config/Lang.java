package org.cyci.phil.purge.config;

import org.bukkit.configuration.file.FileConfiguration;


/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Thu - 18/Aug/2022 - 11:34 PM
 */
public enum Lang {
    PREFIX("message.prefix", "&4&lPURGE&7:"),
    NO_PERM("message.no_perm", "{1} &fYou do not have the permission node:&r &3{0}"),
    NO_ARG("message.no_arg", "{1} &cYou did not provide an argument &6{0}"),
    PURGE_START("message.purge.start", "{1} &4THE PURGE HAS BEGUN"),
    PURGE_STARTED("purge.config.started", "false"),
    PURGE_ACTION_BARS("purge.config.actionbars", "true"),
    PURGE_ACTION_BAR_END("message.purge.action_bars.end", "{1}"),
    PURGE_ACTiON_BAR_START("message.purge.action_bars.end", "{1}"),
    PURGE_END("message.purge.end", "{1} &aThe purge clock has struck 0. see you soon."),
    PURGE_COUNTDOWM("message.purge.countdown", "10"),
    PURGE_COUNTDOWN_MESSAGE_START("message.purge.countdown.start", "{1} &7The purge will begin in.. &r{0}"),
    PURGE_COUNTDOWN_MESSAGE_END("message.purge.countdown.end", "{1} &7The purge will end in.. &a{0}"),
    PURGE_ON_GOING("message.purge.on_going", "{1} &7The current purge timer is {2}"),
    PURGE_HIT_PLAYER("message.purge.player_hit", "{1} &7Please do not hit players the purge has not begun.");

    private final String path;

    private final String def;

    private static FileConfiguration LANG;

    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    public static void setFile(FileConfiguration config) {
        LANG = config;
    }

    public String getDefault() {
        return this.def;
    }

    public String getPath() {
        return this.path;
    }
    public String setConfigValue(String args) {
        String value = Lang.LANG.getString(this.path, this.def);
        value = value.replace(this.def, args);
        return value;
    }

    public String getConfigValue(String[] args) {
        String value = LANG.getString(this.path, this.def);
        if (args == null)
            return value;
        if (args.length == 0)
            return value;
        for (int i = 0; i < args.length; i++)
            value = value.replace("{" + i + "}", args[i]);
        return value;
    }
    public String getValue() {
        return LANG.getString(this.path, this.def);
    }
    public boolean getBoolean() {
        return LANG.getBoolean(this.path, Boolean.parseBoolean(this.def));
    }

    public int getInt() {
        return LANG.getInt(this.path, Integer.parseInt(this.def));
    }
}
