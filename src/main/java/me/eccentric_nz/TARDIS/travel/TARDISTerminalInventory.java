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
package me.eccentric_nz.TARDIS.travel;

import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author eccentric_nz
 */
public class TARDISTerminalInventory {

    private ItemStack[] terminal;

    public TARDISTerminalInventory() {
        this.terminal = getItemStack();
    }

    private ItemStack[] getItemStack() {
        // steps
        // 10
        ItemStack ten = new ItemStack(35, 1, (byte) 0);
        ItemMeta im10 = ten.getItemMeta();
        im10.setDisplayName("Step: 10");
        ten.setItemMeta(im10);
        // 10
        ItemStack twentyfive = new ItemStack(35, 1, (byte) 8);
        ItemMeta im25 = twentyfive.getItemMeta();
        im25.setDisplayName("Step: 25");
        twentyfive.setItemMeta(im25);
        // 10
        ItemStack fifty = new ItemStack(35, 1, (byte) 7);
        ItemMeta im50 = fifty.getItemMeta();
        im50.setDisplayName("Step: 50");
        fifty.setItemMeta(im50);
        // 10
        ItemStack onehundred = new ItemStack(35, 1, (byte) 15);
        ItemMeta im100 = onehundred.getItemMeta();
        im100.setDisplayName("Step: 100");
        onehundred.setItemMeta(im100);
        // -ve
        ItemStack neg = new ItemStack(35, 1, (byte) 14);
        ItemMeta nim = neg.getItemMeta();
        nim.setDisplayName("-ve");
        neg.setItemMeta(nim);
        // +ve
        ItemStack pos = new ItemStack(35, 1, (byte) 5);
        ItemMeta pim = pos.getItemMeta();
        pim.setDisplayName("+ve");
        pos.setItemMeta(pim);
        // x
        ItemStack x = new ItemStack(35, 1, (byte) 3);
        ItemMeta xim = x.getItemMeta();
        xim.setDisplayName("X");
        xim.setLore(Arrays.asList(new String[]{"0"}));
        x.setItemMeta(xim);
        // z
        ItemStack z = new ItemStack(35, 1, (byte) 4);
        ItemMeta zim = z.getItemMeta();
        zim.setDisplayName("Z");
        zim.setLore(Arrays.asList(new String[]{"0"}));
        z.setItemMeta(zim);
        // multiplier
        ItemStack m = new ItemStack(35, 1, (byte) 10);
        ItemMeta mim = m.getItemMeta();
        mim.setDisplayName("Multiplier");
        mim.setLore(Arrays.asList(new String[]{"x1"}));
        m.setItemMeta(mim);
        // environments
        // current
        ItemStack u = new ItemStack(18, 1, (byte) 0);
        ItemMeta uim = u.getItemMeta();
        uim.setDisplayName("Current world");
        u.setItemMeta(uim);
        // normal
        ItemStack w = new ItemStack(3, 1);
        ItemMeta wim = w.getItemMeta();
        wim.setDisplayName("Normal world");
        w.setItemMeta(wim);
        // nether
        ItemStack r = new ItemStack(87, 1);
        ItemMeta rim = r.getItemMeta();
        rim.setDisplayName("Nether");
        r.setItemMeta(rim);
        // the end
        ItemStack e = new ItemStack(121, 1);
        ItemMeta eim = e.getItemMeta();
        eim.setDisplayName("The End");
        e.setItemMeta(eim);
        // test
        ItemStack t = new ItemStack(33, 1);
        ItemMeta tim = t.getItemMeta();
        tim.setDisplayName("Check destination");
        t.setItemMeta(tim);
        // set
        ItemStack s = new ItemStack(47, 1);
        ItemMeta sim = s.getItemMeta();
        sim.setDisplayName("Set destination");
        s.setItemMeta(sim);
        // cancel
        ItemStack c = new ItemStack(46, 1);
        ItemMeta cim = c.getItemMeta();
        cim.setDisplayName("Cancel");
        c.setItemMeta(cim);

        ItemStack[] is = {
            null, ten, null, twentyfive, null, fifty, null, onehundred, null,
            neg, null, null, null, x, null, null, null, pos,
            neg, null, null, null, z, null, null, null, pos,
            neg, m, null, null, null, null, null, null, pos,
            null, u, null, w, null, r, null, e, null,
            t, null, null, null, s, null, null, null, c
        };
        return is;
    }

    public ItemStack[] getTerminal() {
        return terminal;
    }
}
