package committee.nova.petphrase.proxies

import committee.nova.petphrase.command.init.CommandInit
import net.minecraftforge.fml.common.event.FMLServerStartingEvent

class CommonProxy {
  def serverStarting(event: FMLServerStartingEvent): Unit = CommandInit.init(event)
}
