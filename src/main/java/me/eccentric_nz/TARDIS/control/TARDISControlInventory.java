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
package me.eccentric_nz.TARDIS.control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.database.ResultSetTardis;
import me.eccentric_nz.TARDIS.move.TARDISBlackWoolToggler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author eccentric_nz
 */
public class TARDISControlInventory {

    private final TARDIS plugin;
    private final UUID uuid;
    private final ItemStack[] controls;

    public TARDISControlInventory(TARDIS plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;
        this.controls = getItemStack();
    }

    /**
     * Constructs an inventory for the Temporal Locator GUI.
     *
     * @return an Array of itemStacks (an inventory)
     */
    private ItemStack[] getItemStack() {

        // get player prefs
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("uuid", uuid.toString());
        ResultSetTardis rs = new ResultSetTardis(plugin, where, "", false);
        String lights_onoff = "";
        String siege_onoff = "";
        String toggle_openclosed = "";
        String power_onoff = "";
        String off = plugin.getLanguage().getString("SET_OFF");
        String on = plugin.getLanguage().getString("SET_ON");
        if (rs.resultSet()) {
            siege_onoff = (rs.isSiege_on()) ? on : off;
            lights_onoff = (rs.isLights_on()) ? on : off;
            boolean open = new TARDISBlackWoolToggler(plugin).isOpen(rs.getTardis_id());
            toggle_openclosed = (open) ? plugin.getLanguage().getString("SET_OPEN") : plugin.getLanguage().getString("SET_CLOSED");
            power_onoff = (rs.isPowered_on()) ? on : off;
        }
        // Cancel / close
        ItemStack close = new ItemStack(Material.BOWL, 1);
        ItemMeta can = close.getItemMeta();
        can.setDisplayName(plugin.getLanguage().getString("BUTTON_CLOSE"));
        close.setItemMeta(can);
        // Saves
        ItemStack save = new ItemStack(Material.BOWL, 1);
        ItemMeta locs = save.getItemMeta();
        locs.setDisplayName(plugin.getLanguage().getString("BUTTON_SAVES"));
        save.setItemMeta(locs);
        // Areas
        ItemStack area = new ItemStack(Material.BOWL, 1);
        ItemMeta tar = area.getItemMeta();
        tar.setDisplayName(plugin.getLanguage().getString("BUTTON_AREAS"));
        area.setItemMeta(tar);
        // random location
        ItemStack ran = new ItemStack(Material.BOWL, 1);
        ItemMeta dom = ran.getItemMeta();
        dom.setDisplayName(ChatColor.MAGIC + "ran" + ChatColor.RESET + plugin.getLanguage().getString("BUTTON_RANDOM") + ChatColor.MAGIC + "dom");
        ran.setItemMeta(dom);
        // artron levels
        ItemStack art = new ItemStack(Material.BOWL, 1);
        ItemMeta ron = art.getItemMeta();
        ron.setDisplayName(plugin.getLanguage().getString("BUTTON_ARTRON"));
        art.setItemMeta(ron);
        // power up/down
        ItemStack pow = new ItemStack(Material.DIODE, 1);
        ItemMeta dwn = pow.getItemMeta();
        dwn.setDisplayName(plugin.getLanguage().getString("BUTTON_POWER"));
        dwn.setLore(Arrays.asList(power_onoff));
        pow.setItemMeta(dwn);
        // back
        ItemStack fast = new ItemStack(Material.BOWL, 1);
        ItemMeta ret = fast.getItemMeta();
        ret.setDisplayName(plugin.getLanguage().getString("BUTTON_BACK"));
        fast.setItemMeta(ret);
        // destination terminal
        ItemStack ter = new ItemStack(Material.BOWL, 1);
        ItemMeta min = ter.getItemMeta();
        min.setDisplayName(plugin.getLanguage().getString("BUTTON_TERM"));
        ter.setItemMeta(min);
        // architectural reconfiguration system
        ItemStack ars = new ItemStack(Material.BOWL, 1);
        ItemMeta but = ars.getItemMeta();
        but.setDisplayName(plugin.getLanguage().getString("BUTTON_ARS"));
        ars.setItemMeta(but);
        // temporal
        ItemStack temp = new ItemStack(Material.BOWL, 1);
        ItemMeta oral = temp.getItemMeta();
        oral.setDisplayName(plugin.getLanguage().getString("BUTTON_TEMP"));
        temp.setItemMeta(oral);
        // light
        ItemStack lig = new ItemStack(Material.DIODE, 1);
        ItemMeta swi = lig.getItemMeta();
        swi.setDisplayName(plugin.getLanguage().getString("BUTTON_LIGHTS"));
        swi.setLore(Arrays.asList(lights_onoff));
        lig.setItemMeta(swi);
        // TIS
        ItemStack info = new ItemStack(Material.BOWL, 1);
        ItemMeta sys = info.getItemMeta();
        sys.setDisplayName(plugin.getLanguage().getString("BUTTON_INFO"));
        info.setItemMeta(sys);
        // toggle wool
        ItemStack tog = new ItemStack(Material.DIODE, 1);
        ItemMeta gle = tog.getItemMeta();
        gle.setDisplayName(plugin.getLanguage().getString("BUTTON_TOGGLE"));
        gle.setLore(Arrays.asList(toggle_openclosed));
        tog.setItemMeta(gle);
        // siege
        ItemStack siege = new ItemStack(Material.DIODE, 1);
        ItemMeta mode = siege.getItemMeta();
        mode.setDisplayName(plugin.getLanguage().getString("BUTTON_SIEGE"));
        mode.setLore(Arrays.asList(siege_onoff));
        siege.setItemMeta(mode);
        //
        ItemStack upg = new ItemStack(Material.BOWL, 1);
        ItemMeta rade = upg.getItemMeta();
        rade.setDisplayName(plugin.getLanguage().getString("BUTTON_THEME"));
        upg.setItemMeta(rade);

        ItemStack[] is = {
            ran, fast, ter, save, area, null, art, pow, siege,
            ars, upg, null, temp, info, lig, tog, null, close
        };
        return is;
    }

    public ItemStack[] getControls() {
        return controls;
    }
}
