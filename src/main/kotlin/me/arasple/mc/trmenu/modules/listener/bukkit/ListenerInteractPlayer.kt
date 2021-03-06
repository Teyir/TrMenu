package me.arasple.mc.trmenu.modules.listener.bukkit

import io.izzel.taboolib.Version
import io.izzel.taboolib.module.inject.TListener
import me.arasple.mc.trmenu.modules.function.Shortcuts
import me.arasple.mc.trmenu.modules.service.mirror.Mirror
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot

/**
 * @author Arasple
 * @date 2020/7/28 15:44
 */
@TListener
class ListenerInteractPlayer : Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun rightClick(e: PlayerInteractEntityEvent) {
        Mirror.eval("Listener:onInteractEntity(async)") {
            if (Version.isAfter(Version.v1_9) && e.hand == EquipmentSlot.OFF_HAND) return@eval

            val clicked = e.rightClicked
            if (clicked !is Player) return@eval

            if (Shortcuts.rightClickPlayer(e.player, clicked)) {
                e.isCancelled = true
            }
        }
    }

}