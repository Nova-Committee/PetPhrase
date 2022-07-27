package committee.nova.petphrase.proxies

import committee.nova.petphrase.capabilities.{IPetPhrase, PetPhraseCapability, PetPhraseImpl}
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

import java.util.concurrent.Callable

class CommonProxy {
  def preInit(event: FMLPreInitializationEvent): Unit = {
    CapabilityManager.INSTANCE.register(classOf[IPetPhrase], new PetPhraseCapability, new Callable[IPetPhrase] {
      override def call(): IPetPhrase = new PetPhraseImpl
    })
  }

  def init(event: FMLInitializationEvent): Unit = {

  }

  def postInit(event: FMLPostInitializationEvent): Unit = {

  }
}
