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
 * along with plugin program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.eccentric_nz.TARDIS;

import me.eccentric_nz.TARDIS.ARS.TARDISARSListener;
import me.eccentric_nz.TARDIS.advanced.TARDISConsoleCloseListener;
import me.eccentric_nz.TARDIS.advanced.TARDISConsoleListener;
import me.eccentric_nz.TARDIS.advanced.TARDISConsoleSwitchListener;
import me.eccentric_nz.TARDIS.advanced.TARDISDiskCraftListener;
import me.eccentric_nz.TARDIS.advanced.TARDISStorageListener;
import me.eccentric_nz.TARDIS.chameleon.TARDISChameleonListener;
import me.eccentric_nz.TARDIS.chameleon.TARDISPresetListener;
import me.eccentric_nz.TARDIS.commands.admin.TARDISAdminMenuListener;
import me.eccentric_nz.TARDIS.commands.preferences.TARDISPrefsMenuListener;
import me.eccentric_nz.TARDIS.info.TARDISInformationSystemListener;
import me.eccentric_nz.TARDIS.lazarus.TARDISLazarusGUIListener;
import me.eccentric_nz.TARDIS.lazarus.TARDISLazarusListener;
import me.eccentric_nz.TARDIS.listeners.TARDISAnvilListener;
import me.eccentric_nz.TARDIS.listeners.TARDISAreaListener;
import me.eccentric_nz.TARDIS.listeners.TARDISAreaSignListener;
import me.eccentric_nz.TARDIS.listeners.TARDISArtronCapacitorListener;
import me.eccentric_nz.TARDIS.listeners.TARDISBindListener;
import me.eccentric_nz.TARDIS.listeners.TARDISBlockBreakListener;
import me.eccentric_nz.TARDIS.listeners.TARDISBlockDamageListener;
import me.eccentric_nz.TARDIS.listeners.TARDISBlockPhysicsListener;
import me.eccentric_nz.TARDIS.listeners.TARDISBlockPlaceListener;
import me.eccentric_nz.TARDIS.listeners.TARDISButtonListener;
import me.eccentric_nz.TARDIS.listeners.TARDISChatListener;
import me.eccentric_nz.TARDIS.listeners.TARDISChunkListener;
import me.eccentric_nz.TARDIS.listeners.TARDISCondenserListener;
import me.eccentric_nz.TARDIS.listeners.TARDISCraftListener;
import me.eccentric_nz.TARDIS.listeners.TARDISCreeperDeathListener;
import me.eccentric_nz.TARDIS.listeners.TARDISDoorListener;
import me.eccentric_nz.TARDIS.listeners.TARDISEntityGriefListener;
import me.eccentric_nz.TARDIS.listeners.TARDISExplosionListener;
import me.eccentric_nz.TARDIS.listeners.TARDISFireListener;
import me.eccentric_nz.TARDIS.listeners.TARDISGravityWellListener;
import me.eccentric_nz.TARDIS.listeners.TARDISHandbrakeListener;
import me.eccentric_nz.TARDIS.listeners.TARDISHorseListener;
import me.eccentric_nz.TARDIS.listeners.TARDISHotbarListener;
import me.eccentric_nz.TARDIS.listeners.TARDISIceMeltListener;
import me.eccentric_nz.TARDIS.listeners.TARDISItemFrameListener;
import me.eccentric_nz.TARDIS.listeners.TARDISJettisonSeeder;
import me.eccentric_nz.TARDIS.listeners.TARDISJoinListener;
import me.eccentric_nz.TARDIS.listeners.TARDISKeyboardListener;
import me.eccentric_nz.TARDIS.listeners.TARDISKeyboardPacketListener;
import me.eccentric_nz.TARDIS.listeners.TARDISLightningListener;
import me.eccentric_nz.TARDIS.listeners.TARDISMakePresetListener;
import me.eccentric_nz.TARDIS.listeners.TARDISMinecartListener;
import me.eccentric_nz.TARDIS.listeners.TARDISNPCListener;
import me.eccentric_nz.TARDIS.listeners.TARDISPerceptionFilterListener;
import me.eccentric_nz.TARDIS.listeners.TARDISPistonListener;
import me.eccentric_nz.TARDIS.listeners.TARDISQuitListener;
import me.eccentric_nz.TARDIS.listeners.TARDISRecipeListener;
import me.eccentric_nz.TARDIS.listeners.TARDISRedstoneListener;
import me.eccentric_nz.TARDIS.listeners.TARDISRenderRoomListener;
import me.eccentric_nz.TARDIS.listeners.TARDISRoomSeeder;
import me.eccentric_nz.TARDIS.listeners.TARDISSaveSignListener;
import me.eccentric_nz.TARDIS.listeners.TARDISScannerListener;
import me.eccentric_nz.TARDIS.listeners.TARDISSeedBlockListener;
import me.eccentric_nz.TARDIS.listeners.TARDISSignListener;
import me.eccentric_nz.TARDIS.listeners.TARDISStattenheimListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTagListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTeleportListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTemporalListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTemporalLocatorListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTerminalListener;
import me.eccentric_nz.TARDIS.listeners.TARDISTimeLordDeathListener;
import me.eccentric_nz.TARDIS.listeners.TARDISUpdateListener;
import me.eccentric_nz.TARDIS.listeners.TARDISWorldResetListener;
import me.eccentric_nz.TARDIS.listeners.TARDISZeroRoomChatListener;
import me.eccentric_nz.TARDIS.listeners.TARDISZeroRoomPacketListener;
import me.eccentric_nz.TARDIS.sonic.TARDISSonicEntityListener;
import me.eccentric_nz.TARDIS.sonic.TARDISSonicListener;
import me.eccentric_nz.TARDIS.sonic.TARDISSonicSorterListener;
import me.eccentric_nz.TARDIS.sonic.TARDISSonicUpgradeListener;

/**
 *
 * @author eccentric_nz
 */
public class TARDISListenerRegisterer {

    private final TARDIS plugin;

    public TARDISListenerRegisterer(TARDIS plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers all the listeners for the various events required to use the
     * TARDIS.
     */
    public void registerListeners() {
        if (plugin.getConfig().getBoolean("creation.use_block_stack")) {
            plugin.getPM().registerEvents(new TARDISBlockPlaceListener(plugin), plugin);
        }
        plugin.getPM().registerEvents(new TARDISBlockBreakListener(plugin), plugin);
        TARDISDoorListener doorListener = new TARDISDoorListener(plugin);
        plugin.getPM().registerEvents(doorListener, plugin);
        plugin.getGeneralKeeper().setDoorListener(doorListener);
        TARDISButtonListener buttonListener = new TARDISButtonListener(plugin);
        plugin.getPM().registerEvents(buttonListener, plugin);
        plugin.getGeneralKeeper().setButtonListener(buttonListener);
        plugin.getPM().registerEvents(new TARDISRedstoneListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISSignListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISUpdateListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISAreaListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISFireListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISBlockDamageListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISExplosionListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISEntityGriefListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISLightningListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISCreeperDeathListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISArtronCapacitorListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISRoomSeeder(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISJettisonSeeder(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISBindListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISHandbrakeListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISGravityWellListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISCondenserListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISIceMeltListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISChunkListener(plugin), plugin);
        TARDISScannerListener scannerListener = new TARDISScannerListener(plugin);
        plugin.getPM().registerEvents(scannerListener, plugin);
        plugin.getGeneralKeeper().setScannerListener(scannerListener);
        plugin.getPM().registerEvents(new TARDISTimeLordDeathListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISJoinListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISQuitListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISKeyboardListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISBlockPhysicsListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISTagListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISMakePresetListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISPistonListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISTerminalListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISChameleonListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISPresetListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISARSListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISSaveSignListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISAreaSignListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISStattenheimListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISHotbarListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISAdminMenuListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISTemporalLocatorListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISRecipeListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISSeedBlockListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISCraftListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISChatListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISMinecartListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISHorseListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISTeleportListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISAnvilListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISInformationSystemListener(plugin), plugin);
        if (getNPCManager()) {
            plugin.getPM().registerEvents(new TARDISNPCListener(plugin), plugin);
        }
        if (plugin.getPM().isPluginEnabled("Multiverse-Adventure")) {
            plugin.getPM().registerEvents(new TARDISWorldResetListener(plugin), plugin);
        }
        plugin.getPM().registerEvents(new TARDISSonicUpgradeListener(plugin), plugin);
        TARDISSonicListener sonicListener = new TARDISSonicListener(plugin);
        plugin.getPM().registerEvents(sonicListener, plugin);
        plugin.getGeneralKeeper().setSonicListener(sonicListener);
        plugin.getPM().registerEvents(new TARDISSonicEntityListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISSonicSorterListener(plugin), plugin);
        TARDISRenderRoomListener rendererListener = new TARDISRenderRoomListener(plugin);
        plugin.getPM().registerEvents(rendererListener, plugin);
        plugin.getGeneralKeeper().setRendererListener(rendererListener);
        plugin.getPM().registerEvents(new TARDISDiskCraftListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISStorageListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISConsoleListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISConsoleSwitchListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISConsoleCloseListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISPerceptionFilterListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISPrefsMenuListener(plugin), plugin);
        plugin.getPM().registerEvents(new TARDISTemporalListener(plugin), plugin);
        if (plugin.getConfig().getBoolean("allow.zero_room")) {
            plugin.getPM().registerEvents(new TARDISZeroRoomChatListener(plugin), plugin);
            if (plugin.getPM().isPluginEnabled("ProtocolLib")) {
                new TARDISZeroRoomPacketListener(plugin);
            }
        }
        if (plugin.getPM().isPluginEnabled("ProtocolLib")) {
            TARDISKeyboardPacketListener kpl = new TARDISKeyboardPacketListener(plugin);
            plugin.getPM().registerEvents(kpl, plugin);
            kpl.startSignPackets();
            if (plugin.getPM().isPluginEnabled("LibsDisguises")) {
                plugin.getPM().registerEvents(new TARDISLazarusListener(plugin), plugin);
                plugin.getPM().registerEvents(new TARDISLazarusGUIListener(plugin), plugin);
            }
        }
        plugin.getPM().registerEvents(new TARDISItemFrameListener(plugin), plugin);
    }

    private boolean getNPCManager() {
        if (plugin.getPM().getPlugin("Citizens") != null && plugin.getPM().getPlugin("Citizens").isEnabled() && plugin.getConfig().getBoolean("allow.emergency_npc")) {
            plugin.debug("Enabling Emergency Program One!");
            return true;
        } else {
            // set emergency_npc false as Citizens not found
            plugin.getConfig().set("allow.emergency_npc", false);
            plugin.saveConfig();
            plugin.debug("Emergency Program One was disabled as it requires the Citizens plugin!");
            return false;
        }
    }
}