package dev.thebjoredcraft.forceitembattle.core

import dev.thebjoredcraft.forceitembattle.api.model.BattleTask
import dev.thebjoredcraft.forceitembattle.api.model.BattleTeam
import it.unimi.dsi.fastutil.objects.ObjectSet
import net.kyori.adventure.util.Services
import org.bukkit.Material
import java.util.UUID

interface ForceItemManager {
    suspend fun loadItems()

    suspend fun getOverworldItems(): ObjectSet<Material>
    suspend fun getNetherItems(): ObjectSet<Material>
    suspend fun getEndItems(): ObjectSet<Material>

    suspend fun skipItem(team: BattleTeam, skipper: String?)

    suspend fun getCurrentTask(team: BattleTeam): BattleTask?
    suspend fun getCurrentItem(uuid: UUID): Material?
    suspend fun getNewItem(uuid: UUID): Material?

    suspend fun markAsFinished(team: BattleTeam, skipped: Boolean, completer: String)

    companion object {
        val INSTANCE: ForceItemManager = Services.serviceWithFallback(ForceItemManager::class.java).orElseThrow { IllegalStateException("There is no fallback service for ${this::class.java.name} available.") }
    }
}

val forceItemManager get() = ForceItemManager.INSTANCE