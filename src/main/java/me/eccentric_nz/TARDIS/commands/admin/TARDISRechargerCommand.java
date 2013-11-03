/*
 * Copyright (C) 2013 eccentric_nz
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
package me.eccentric_nz.TARDIS.commands.admin;

import java.util.HashMap;
import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.database.ResultSetTravellers;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author eccentric_nz
 */
public class TARDISRechargerCommand {

    private final TARDIS plugin;
    private Material charger = Material.REDSTONE_LAMP_ON;

    public TARDISRechargerCommand(TARDIS plugin) {
        this.plugin = plugin;
        if (plugin.bukkitversion.compareTo(plugin.prewoodbuttonversion) >= 0) {
            charger = Material.BEACON;
        }
    }

    public boolean setRecharger(CommandSender sender, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (player == null) {
            sender.sendMessage(plugin.pluginName + "You can't set a recharger location from the console!");
            return true;
        }
        Block b = player.getTargetBlock(plugin.tardisCommand.transparent, 50);
        if (!b.getType().equals(charger)) {
            player.sendMessage(plugin.pluginName + "You must be targeting a " + charger.toString() + " block!");
            return true;
        }
        // make sure they're not targeting their inner TARDIS beacon
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("player", player.getName());
        ResultSetTravellers rst = new ResultSetTravellers(plugin, where, false);
        if (rst.resultSet()) {
            player.sendMessage(plugin.pluginName + "You cannot use the TARDIS " + charger.toString() + " to recharge!");
            return true;
        }
        Location l = b.getLocation();
        plugin.getConfig().set("rechargers." + args[1] + ".world", l.getWorld().getName());
        plugin.getConfig().set("rechargers." + args[1] + ".x", l.getBlockX());
        plugin.getConfig().set("rechargers." + args[1] + ".y", l.getBlockY());
        plugin.getConfig().set("rechargers." + args[1] + ".z", l.getBlockZ());
        // if worldguard is on the server, protect a 3x3x3 area around beacon
        if (plugin.worldGuardOnServer && plugin.getConfig().getBoolean("use_worldguard")) {
            int minx = l.getBlockX() - 2;
            int maxx = l.getBlockX() + 2;
            int minz = l.getBlockZ() - 2;
            int maxz = l.getBlockZ() + 2;
            Location wg1 = new Location(l.getWorld(), minx, l.getBlockY() + 2, minz);
            Location wg2 = new Location(l.getWorld(), maxx, l.getBlockY() - 2, maxz);
            plugin.wgutils.addRechargerProtection(player, args[1], wg1, wg2);
        }
        plugin.saveConfig();
        sender.sendMessage(plugin.pluginName + "The config was updated!");
        return true;
    }
}
