package example.assignments

abstract class AquariumFish {
    abstract val color: String
}

class Shark(override val color: String = "grey") : FishAction, FishColor {
    override fun eat() {
        println("hunt and eat fish")
    }

}

class Plecostomus(color: String = "gold") : AquariumFish(), FishAction by PrintingFishAction("eat algae"), FishColor by GoldColor {
}

interface FishAction  {
    fun eat()
}

interface FishColor {
    val color: String
}

object GoldColor : FishColor {
    override val color = "gold"
}

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}