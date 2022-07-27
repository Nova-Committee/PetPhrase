package committee.nova.petphrase.command.init

import committee.nova.petphrase.command.impl.{CommandClearPhrase, CommandSetPhrase}
import net.minecraftforge.fml.common.event.FMLServerStartingEvent

object CommandInit {
  def init(event: FMLServerStartingEvent): Unit = {
    event.registerServerCommand(new CommandSetPhrase)
    event.registerServerCommand(new CommandClearPhrase)
  }
}
