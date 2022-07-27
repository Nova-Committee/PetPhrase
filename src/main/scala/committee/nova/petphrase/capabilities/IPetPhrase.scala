package committee.nova.petphrase.capabilities

trait IPetPhrase {
  def setPetPhrase(p: String): Unit

  def getPetPhrase: String
}
