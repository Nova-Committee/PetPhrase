package committee.nova.petphrase

import committee.nova.petphrase.PetPhrase.MODID
import committee.nova.petphrase.proxies.CommonProxy
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.{Mod, SidedProxy}

@Mod(modid = MODID, useMetadata = true, modLanguage = "scala")
object PetPhrase {
  final val MODID = "petphrase"
  final val packagePrefix = "committee.nova.petphrase.proxies."

  @SidedProxy(serverSide = packagePrefix + "CommonProxy", clientSide = packagePrefix + "ClientProxy")
  var proxy: CommonProxy = _

  @EventHandler def preInit(e: FMLPreInitializationEvent): Unit = proxy.preInit(e)

  @EventHandler def init(e: FMLInitializationEvent): Unit = proxy.init(e)

  @EventHandler def postInit(e: FMLPostInitializationEvent): Unit = proxy.postInit(e)
}
