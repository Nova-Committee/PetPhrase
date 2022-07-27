package committee.nova.petphrase.event

import committee.nova.petphrase.PetPhrase
import committee.nova.petphrase.capabilities.{PetPhraseCapability, PetPhraseImpl}
import committee.nova.petphrase.util.StringUtil
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.{EnumFacing, ResourceLocation}
import net.minecraftforge.common.ForgeHooks.newChatWithLinks
import net.minecraftforge.common.capabilities.{Capability, ICapabilityProvider}
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.{AttachCapabilitiesEvent, ServerChatEvent}
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@EventBusSubscriber
object EventManager {
  @SubscribeEvent
  def onChat(event: ServerChatEvent): Unit = {
    val player = event.getPlayer
    if (!player.hasCapability(PetPhraseCapability.PET_PHRASE, null)) return
    val newComponent = new TextComponentTranslation(
      "chat.type.text",
      player.getDisplayName,
      newChatWithLinks(StringUtil.fillPetPhraseIn(event.getMessage, player.getCapability(PetPhraseCapability.PET_PHRASE, null).getPetPhrase))
    )
    event.setComponent(newComponent)
  }

  @SubscribeEvent
  def onCapAttachment(event: AttachCapabilitiesEvent[Entity]): Unit = {
    event.getObject match {
      case _: EntityPlayerMP => event.addCapability(new ResourceLocation(PetPhrase.MODID, "petphrase"), new ICapabilityProvider {
        override def hasCapability(capability: Capability[_], facing: EnumFacing): Boolean = capability == PetPhraseCapability.PET_PHRASE

        override def getCapability[T](capability: Capability[T], facing: EnumFacing): T = {
          if (capability == PetPhraseCapability.PET_PHRASE) return PetPhraseCapability.PET_PHRASE.cast(new PetPhraseImpl)
          null.asInstanceOf[T]
        }
      })
      case _ =>
    }
  }

  @SubscribeEvent
  def onClone(event: PlayerEvent.Clone): Unit = {
    val phrase = event.getEntityPlayer.getCapability(PetPhraseCapability.PET_PHRASE, null)
    if (phrase == null) return
    val old = event.getOriginal.getCapability(PetPhraseCapability.PET_PHRASE, null)
    phrase.setPetPhrase(old.getPetPhrase)
  }
}
