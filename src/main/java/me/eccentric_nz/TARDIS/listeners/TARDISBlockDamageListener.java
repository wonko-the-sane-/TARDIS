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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.builders.TARDISMaterialisationData;
import me.eccentric_nz.TARDIS.database.QueryFactory;
import me.eccentric_nz.TARDIS.database.ResultSetBlocks;
import me.eccentric_nz.TARDIS.database.ResultSetCurrentLocation;
import me.eccentric_nz.TARDIS.database.ResultSetTardis;
import me.eccentric_nz.TARDIS.utility.TARDISHostileDisplacement;
import me.eccentric_nz.TARDIS.utility.TARDISMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 * The Judoon are a race of rhinocerid humanoids frequently employed as a
 * mercenary police force.
 *
 * @author eccentric_nz
 */
public class TARDISBlockDamageListener implements Listener {

    private final TARDIS plugin;
    private final List<Material> doors = Arrays.asList(Material.IRON_DOOR_BLOCK, Material.WOODEN_DOOR, Material.TRAP_DOOR);

    public TARDISBlockDamageListener(TARDIS plugin) {
        this.plugin = plugin;
    }

    /**
     * Listens for block damage to the TARDIS Police Box. If the block is a
     * Police Box block then the event is cancelled, and the player warned.
     *
     * @param event a block being damaged
     */
    @SuppressWarnings("deprecation")
    @EventHandler(ignoreCancelled = true)
    public void onPoliceBoxDamage(BlockDamageEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();
        String l = b.getLocation().toString();
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("location", l);
        ResultSetBlocks rsb = new ResultSetBlocks(plugin, where, false);
        if (rsb.resultSet()) {
            int id = rsb.getTardis_id();
            if (p.hasPermission("tardis.sonic.admin")) {
                String[] split = plugin.getRecipesConfig().getString("shaped.Sonic Screwdriver.result").split(":");
                Material sonic = Material.valueOf(split[0]);
                ItemStack is = p.getItemInHand();
                if (is != null && is.getType().equals(sonic)) {
                    // unhide TARDIS
                    unhide(id, p);
                    return;
                }
            }
            boolean m = false;
            boolean isDoor = false;
            if (plugin.getConfig().getBoolean("allow.hads") && !plugin.getTrackerKeeper().getInVortex().contains(id) && isOwnerOnline(id)) {
                if (doors.contains(b.getType())) {
                    if (isOwner(id, p.getUniqueId().toString())) {
                        isDoor = true;
                    }
                }
                if (!isDoor && rsb.isPolice_box()) {
                    int damage = (plugin.getTrackerKeeper().getDamage().containsKey(id)) ? plugin.getTrackerKeeper().getDamage().get(Integer.valueOf(id)) : 0;
                    plugin.getTrackerKeeper().getDamage().put(id, damage + 1);
                    if (damage == plugin.getConfig().getInt("preferences.hads_damage")) {
                        new TARDISHostileDisplacement(plugin).moveTARDIS(id, p);
                        m = true;
                    }
                    if (!m) {
                        TARDISMessage.send(p, "HADS_WARNING", String.format("%d", (plugin.getConfig().getInt("preferences.hads_damage") - damage)));
                    }
                }
            } else {
                TARDISMessage.send(p, "TARDIS_BREAK");
            }
            event.setCancelled(true);
        }
    }

    private boolean isOwner(int id, String uuid) {
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("tardis_id", id);
        where.put("uuid", uuid);
        ResultSetTardis rst = new ResultSetTardis(plugin, where, "", false);
        return rst.resultSet();
    }

    private boolean isOwnerOnline(int id) {
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("tardis_id", id);
        ResultSetTardis rst = new ResultSetTardis(plugin, where, "", false);
        if (rst.resultSet()) {
            return plugin.getServer().getOfflinePlayer(rst.getUuid()).isOnline();
        } else {
            return false;
        }
    }

    private void unhide(final int id, Player player) {
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("tardis_id", id);
        ResultSetTardis rst = new ResultSetTardis(plugin, where, "", false);
        if (rst.resultSet() && rst.isHidden()) {
            Player p = (Player) plugin.getServer().getOfflinePlayer(rst.getUuid());
            // unhide this tardis
            boolean cham = false;
            if (plugin.getConfig().getBoolean("travel.chameleon")) {
                cham = rst.isChamele_on();
            }
            HashMap<String, Object> wherecl = new HashMap<String, Object>();
            wherecl.put("tardis_id", id);
            ResultSetCurrentLocation rsc = new ResultSetCurrentLocation(plugin, wherecl);
            if (!rsc.resultSet()) {
                TARDISMessage.send(player, "CURRENT_NOT_FOUND");
            }
            Location l = new Location(rsc.getWorld(), rsc.getX(), rsc.getY(), rsc.getZ());
            HashMap<String, Object> wheret = new HashMap<String, Object>();
            wheret.put("tardis_id", id);
            QueryFactory qf = new QueryFactory(plugin);
            final TARDISMaterialisationData pbd = new TARDISMaterialisationData();
            pbd.setChameleon(cham);
            pbd.setDirection(rsc.getDirection());
            pbd.setLocation(l);
            pbd.setMalfunction(false);
            pbd.setOutside(false);
            pbd.setPlayer(player);
            pbd.setRebuild(true);
            pbd.setSubmarine(rsc.isSubmarine());
            pbd.setTardisID(id);
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    plugin.getPresetBuilder().buildPreset(pbd);
                }
            }, 5L);
            // set hidden to false
            HashMap<String, Object> whereh = new HashMap<String, Object>();
            whereh.put("tardis_id", id);
            HashMap<String, Object> seth = new HashMap<String, Object>();
            seth.put("hidden", 0);
            qf.doUpdate("tardis", seth, whereh);
        }
    }
}
