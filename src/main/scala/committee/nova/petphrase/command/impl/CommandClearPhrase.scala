package committee.nova.petphrase.command.impl

import committee.nova.petphrase.util.NBTUtil.nbtPhrase
import net.minecraft.command.{CommandBase, ICommandSender, PlayerNotFoundException}
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString

class CommandClearPhrase extends CommandBase {
  override def getName: String = "clearphrase"

  override def getUsage(sender: ICommandSender): String = "/clearphrase --- Clear your pet phrase"

  override def getRequiredPermissionLevel: Int = 1

  @throws(classOf[PlayerNotFoundException])
  override def execute(server: MinecraftServer, sender: ICommandSender, args: Array[String]): Unit = {
    val player = CommandBase.getCommandSenderAsPlayer(sender)
    player.getEntityData.setString(nbtPhrase, "")
    player.sendMessage(new TextComponentString("Phrase cleared!"))
  }

}
