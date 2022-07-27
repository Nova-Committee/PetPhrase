package committee.nova.petphrase

import committee.nova.petphrase.PetPhrase.MODID
import committee.nova.petphrase.proxies.CommonProxy
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}

@Mod(modid = MODID, useMetadata = true, modLanguage = "scala")
object PetPhrase {
  final val MODID = "petphrase"
  final val packagePrefix = "committee.nova.petphrase.proxies."

  @SidedProxy(serverSide = packagePrefix + "CommonProxy", clientSide = packagePrefix + "ClientProxy")
  var proxy: CommonProxy = _

  @EventHandler def serverStarting(e: FMLServerStartingEvent): Unit = proxy.serverStarting(e)
}
