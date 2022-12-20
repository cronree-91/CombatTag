package jp.cron.combattag.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import jp.cron.combattag.Plugin;
import jp.cron.combattag.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
@CommandPermission("jp.cron.combattag.spawn")
public class SpawnCommand extends BaseCommand {

    @Dependency
    Config config;

    @Dependency
    Plugin pl;

    @Default
    public void spawn(Player player) {

        long diff = System.currentTimeMillis()-pl.playerManager.getPlayer(player).lastCombat;

        if (diff <= config.tagSeconds*1000) {
            player.sendMessage("戦闘中はテレポートできません。");
            return;
        }

        Long sec = config.spawnSeconds;
        player.sendMessage(sec+"秒後にテレポートします...動かないでください...");

        pl.playerManager.getPlayer(player).moveTag = false;

        Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
            if (!pl.playerManager.getPlayer(player).moveTag)
                player.teleport(config.spawnPoint);
            }, sec * 20);
        }
    }
