package cyci.org.phil.purge;

import co.aikar.commands.PaperCommandManager;
import cyci.org.phil.purge.commands.PurgeCommand;
import cyci.org.phil.purge.config.ConfigWrapper;
import cyci.org.phil.purge.config.Lang;
import cyci.org.phil.purge.listeners.PurgeDamageListener;
import cyci.org.phil.purge.player.PurgePlayerHandler;
import cyci.org.phil.purge.player.PurgePlayerRegistry;
import cyci.org.phil.purge.utils.SaveTimer;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Purge extends JavaPlugin {
    private static Purge Instance;
    private static Chat chat = null;
    private static Economy econ = null;
    private static LuckPerms api;
    //private static TitleManagerAPI tmapi;
    public ConfigWrapper messagesFile = new ConfigWrapper(this, "/", "messages.yml");
    // taken from https://github.com/JordanOsterberg/Minigame-Template/blob/master/src/me/potato/minigame/Main.java
    public ArrayList<Player> alive = new ArrayList<>();
    public ArrayList<Player> spectating = new ArrayList<>();

    public static Purge getInstance() {
        return Instance;
    }
    private GameStates gamestates;

    public GameStates getGamestate() {
        return this.gamestates;
    }
    public void setGamestate(GameStates gamestate) {
        this.gamestates = gamestate;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        checkDepend();
        loadMessages();
        setupEconomy();
        luckPermsRegistry();
        setupChat();
        PaperCommandManager manager = new PaperCommandManager(getInstance());
        manager.enableUnstableAPI("brigadier");
        getServer().getPluginManager().registerEvents((Listener) new PurgeDamageListener(), (Plugin)this);
        // optional: enable unstable api to use help
        manager.enableUnstableAPI("help");
        manager.registerCommand(new PurgeCommand());
        setGamestate(GameStates.WAITING_FOR_PLAYERS);
        //tmapi = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");

        Bukkit.getOnlinePlayers().forEach(player -> {
            PurgePlayerHandler pplayer = new PurgePlayerHandler(player);
            PurgePlayerRegistry.registerPlayer(pplayer);
        });
        (new SaveTimer()).runTaskTimer((Plugin)this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void checkDepend() {
        CommandSender console = Bukkit.getServer().getConsoleSender();
        List<String> dependencies = getDescription().getDepend();
        console.sendMessage("§8*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
        console.sendMessage(" ");
        console.sendMessage("§8*=*§r        §4Purge         §8*=*");
        console.sendMessage(" ");
        console.sendMessage("§8- Checking if the dependencies are on the server.");
        console.sendMessage("§8*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
        for (String plugin_names : dependencies) {
            Plugin plugin_name = getServer().getPluginManager().getPlugin(plugin_names); // gets plugin on server
            if (plugin_name != null && plugin_name.isEnabled()) { // checks if its enabled
                console.sendMessage("§7- §a" + plugin_name + " §7Has successfully been found and registered");
            } else { // if not disable
                console.sendMessage("§7- §c" + String.format(plugin_names, ", ") + " §7Has not been found. disabling");
                Bukkit.getScheduler().runTaskLaterAsynchronously(getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        getServer().getPluginManager().disablePlugin(getInstance());
                    }
                }, 40);
            }
        }
        console.sendMessage(" ");
        console.sendMessage("§8*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
    }
    public void luckPermsRegistry() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            api = provider.getProvider();

        }
    }
    @SuppressWarnings("UnusedReturnValue")
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }
    @SuppressWarnings({"UnusedReturnValue"})
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
        }
        return true;
    }
    private void loadMessages() {
        Lang.setFile(this.messagesFile.getConfig());
        for (Lang value : Lang.values())
            this.messagesFile.getConfig().addDefault(value.getPath(), value.getDefault());
        this.messagesFile.getConfig().options().copyDefaults(true);
        this.messagesFile.saveConfig();
    }
    public static Chat getChat() {
        return chat;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public static LuckPerms getLuckPerms() {
        return api;
    }
//    public static TitleManagerAPI getTmapi() {
//        return tmapi;
//    }
}
