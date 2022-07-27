package committee.nova.petphrase.capabilities

class PetPhraseImpl extends IPetPhrase {
  private var phrase = ""

  override def setPetPhrase(p: String): Unit = phrase = p

  override def getPetPhrase: String = phrase
}
