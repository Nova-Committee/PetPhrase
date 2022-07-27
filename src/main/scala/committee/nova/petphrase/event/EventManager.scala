package committee.nova.petphrase.event

import committee.nova.petphrase.util.NBTUtil.nbtPhrase
import committee.nova.petphrase.util.StringUtil
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.common.ForgeHooks.newChatWithLinks
import net.minecraftforge.event.ServerChatEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@EventBusSubscriber
object EventManager {
  @SubscribeEvent
  def onChat(event: ServerChatEvent): Unit = {
    val player = event.getPlayer
    val phrase = player.getEntityData.getString(nbtPhrase)
    if (phrase.isEmpty) return
    val newComponent = new TextComponentTranslation(
      "chat.type.text",
      player.getDisplayName,
      newChatWithLinks(StringUtil.fillPetPhraseIn(event.getMessage, phrase))
    )
    event.setComponent(newComponent)
  }

  @SubscribeEvent
  def onClone(event: PlayerEvent.Clone): Unit = {
    val phrase = event.getEntityPlayer.getEntityData
    val old = event.getOriginal.getEntityData.getString(nbtPhrase)
    phrase.setString(nbtPhrase, old)
  }
}
