/*
 * Copyright (C) 2014 eccentric_nz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.eccentric_nz.TARDIS.listeners;

import java.util.HashMap;
import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.achievement.TARDISAchievementFactory;
import me.eccentric_nz.TARDIS.database.QueryFactory;
import me.eccentric_nz.TARDIS.database.ResultSetPlayerPrefs;
import me.eccentric_nz.TARDIS.database.ResultSetTardis;
import me.eccentric_nz.TARDIS.utility.TARDISMessage;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * The Sontarans are a race of belligerent and militaristic clones from the
 * planet Sontar. They wage eternal war throughout Mutter's Spiral against the
 * Rutan Host.
 *
 * @author eccentric_nz
 */
public class TARDISCreeperDeathListener implements Listener {

    private final TARDIS plugin;

    public TARDISCreeperDeathListener(TARDIS plugin) {
        this.plugin = plugin;
    }

    /**
     * Listens for Creeper deaths. If the creeper is killed by a player and the
     * creeper is a charged creeper, then the player receives the configured
     * amount of Artron Energy.
     *
     * @param e the death of an entity
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onCreeperDeath(EntityDeathEvent e) {
        LivingEntity ent = e.getEntity();
        if (ent instanceof Creeper) {
            Creeper c = (Creeper) ent;
            if (c.isPowered()) {
                Player p = c.getKiller();
                if (p != null) {
                    String killerUUID = p.getUniqueId().toString();
                    // is the killer a Time Lord?
                    HashMap<String, Object> where = new HashMap<String, Object>();
                    where.put("uuid", killerUUID);
                    ResultSetTardis rs = new ResultSetTardis(plugin, where, "", false);
                    if (rs.resultSet()) {
                        HashMap<String, Object> wherep = new HashMap<String, Object>();
                        wherep.put("uuid", killerUUID);
                        ResultSetPlayerPrefs rsp = new ResultSetPlayerPrefs(plugin, wherep);
                        QueryFactory qf = new QueryFactory(plugin);
                        HashMap<String, Object> set = new HashMap<String, Object>();
                        int amount = plugin.getArtronConfig().getInt("creeper_recharge");
                        if (!rsp.resultSet()) {
                            set.put("uuid", killerUUID);
                            set.put("artron_level", amount);
                            qf.doInsert("player_prefs", set);
                        } else {
                            int level = rsp.getArtronLevel() + amount;
                            HashMap<String, Object> wherea = new HashMap<String, Object>();
                            wherea.put("uuid", killerUUID);
                            set.put("artron_level", level);
                            qf.doUpdate("player_prefs", set, wherea);
                        }
                        TARDISMessage.send(p, "ENERGY_CREEPER", String.format("%d", amount));
                        // are we doing an achievement?
                        if (plugin.getAchievementConfig().getBoolean("kill.enabled")) {
                            TARDISAchievementFactory taf = new TARDISAchievementFactory(plugin, p, "kill", 1);
                            taf.doAchievement(1);
                        }
                    }
                }
            }
        }
    }
}
