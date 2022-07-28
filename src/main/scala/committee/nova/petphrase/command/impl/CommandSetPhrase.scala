package committee.nova.petphrase.command.impl

import committee.nova.petphrase.util.NBTUtil.nbtPhrase
import net.minecraft.command.{CommandBase, ICommandSender, PlayerNotFoundException, WrongUsageException}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString

class CommandSetPhrase extends CommandBase {
  override def getName: String = "setphrase"

  override def getUsage(sender: ICommandSender): String = "/setphrase <Your Pet Phrase> --- Set your pet phrase to the inputted value"

  override def checkPermission(server: MinecraftServer, sender: ICommandSender): Boolean = sender.isInstanceOf[EntityPlayer]

  override def getRequiredPermissionLevel: Int = 0

  @throws[PlayerNotFoundException]
  override def execute(server: MinecraftServer, sender: ICommandSender, args: Array[String]): Unit = {
    if (args.length != 1) throw new WrongUsageException(getUsage(sender))
    val phrase = args(0)
    if (phrase.length >= 50) throw new WrongUsageException("Phrase length should be smaller than 50")
    val player = CommandBase.getCommandSenderAsPlayer(sender)
    player.getEntityData.setString(nbtPhrase, phrase)
    player.sendMessage(new TextComponentString(s"Phrase set to $phrase!"))
  }
}
