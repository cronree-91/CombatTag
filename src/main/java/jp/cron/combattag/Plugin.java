package jp.cron.combattag;

import jp.cron.combattag.command.SpawnCommand;
import jp.cron.combattag.config.Config;
import co.aikar.commands.PaperCommandManager;
import jp.cron.combattag.manager.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin {
    public Config config;
    public final PlayerManager playerManager = new PlayerManager();

    @Override
    public void onEnable() {
        getLogger().info("========================= " + getName() + " v" + getDescription().getVersion() + " =========================");
        getLogger().info("  Developed by cronree-91");
        this.saveDefaultConfig();
        PaperCommandManager manager = new PaperCommandManager(this);

        loadConfig();
        manager.registerDependency(Config.class, config);

        manager.registerCommand(new SpawnCommand());
    }

    private void loadConfig() {
        this.config = new Config();
        config.spawnPoint = this.getConfig().getLocation("spawnPoint");
        config.tagSeconds = this.getConfig().getLong("tagSeconds");
        config.spawnSeconds = this.getConfig().getLong("spawnSeconds");
    }
}
