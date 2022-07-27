package committee.nova.petphrase.capabilities

import net.minecraft.nbt.{NBTBase, NBTTagString}
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.{Capability, CapabilityInject}

object PetPhraseCapability {
  @CapabilityInject(classOf[IPetPhrase])
  var PET_PHRASE: Capability[IPetPhrase] = _
}

class PetPhraseCapability extends IStorage[IPetPhrase] {
  override def writeNBT(capability: Capability[IPetPhrase], instance: IPetPhrase, side: EnumFacing): NBTBase = new NBTTagString(instance.getPetPhrase)

  override def readNBT(capability: Capability[IPetPhrase], instance: IPetPhrase, side: EnumFacing, nbt: NBTBase): Unit = nbt match {
    case str: NBTTagString => instance.setPetPhrase(str.getString)
    case _ =>
  }
}
