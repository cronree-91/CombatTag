package jp.cron.combattag;

import jp.cron.combattag.command.SpawnCommand;
import jp.cron.combattag.config.Config;
import co.aikar.commands.PaperCommandManager;
import jp.cron.combattag.event.Listener;
import jp.cron.combattag.manager.PlayerManager;
import org.bukkit.Location;
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

        getServer().getPluginManager().registerEvents(new Listener(this), this);

        manager.registerCommand(new SpawnCommand());
    }

    private void loadConfig() {
        this.config = new Config();
        config.spawnSeconds = this.getConfig().getLong("spawnSeconds");
        config.tagSeconds = this.getConfig().getLong("tagSeconds");

        String worldName = this.getConfig().getString("spawnPoint.world");
        int x = this.getConfig().getInt("spawnPoint.x");
        int y = this.getConfig().getInt("spawnPoint.y");
        int z = this.getConfig().getInt("spawnPoint.z");
        double yaw = this.getConfig().getDouble("spawnPoint.yaw");
        double pitch = this.getConfig().getDouble("spawnPoint.pitch");

        config.spawnPoint = new Location(getServer().getWorld(worldName), x, y, z, (float) yaw, (float) pitch);

    }
}
