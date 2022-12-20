package jp.cron.combattag.event;

import jp.cron.combattag.Plugin;
import jp.cron.combattag.manager.PlayerEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener implements org.bukkit.event.Listener {
    private Plugin pl;

    public Listener(Plugin pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onPlayerMoved(PlayerMoveEvent event) {
        PlayerEntity epl = pl.playerManager.getPlayer(event.getPlayer());
        if (!epl.moveTag) {
            epl.moveTag = true;
            event.getPlayer().sendMessage("テレポートをキャンセルしました。");
        }
    }

    @EventHandler
    public void onPlayerHitPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER) {
            Player pl = (Player) event.getEntity();
            PlayerEntity epl = this.pl.playerManager.getPlayer(pl);
            epl.lastCombat = System.currentTimeMillis();
        }
    }

}
