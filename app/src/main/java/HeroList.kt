public class HeroList {
    val heroes = listOf(
        Hero(0, "Deadpool", "A wise-cracking, fourth-wall-breaking mercenary, Deadpool is known for his regenerative healing factor and unorthodox fighting style. His humor, unpredictability, and chaotic nature make him a fan favorite, even if his methods are often questionable.", "https://i.pinimg.com/736x/e7/74/a0/e774a0d3ac93047a35d3d0641a094fea.jpg"),
        Hero(1, "Iron Man", "A genius inventor and billionaire, Tony Stark created a high-tech suit of armor to save his life and fight evil. He is a founding member of the Avengers and known for his wit and leadership.", "https://i.pinimg.com/736x/bd/ec/d8/bdecd83ebbd830907d3a052f352caeee.jpg"),
        Hero(2, "Spider Man", "A teenage superhero with spider-like abilities, Peter Parker balances high school life with fighting crime. He is known for his agility, web-slinging, and youthful enthusiasm.", "https://i.pinimg.com/236x/2c/e2/f0/2ce2f0a5038abaf18a3004806a29114d.jpg"),
        Hero(3, "Thor", "The Norse God of Thunder, Thor wields the mighty hammer Mjolnir (and later Stormbreaker). He is a powerful warrior from Asgard who protects both Earth and the Nine Realms.", "https://i.pinimg.com/736x/41/08/58/4108581fd03f820bfd446833f1c27fe0.jpg"),
        Hero(4, "Hulk", "A scientist exposed to gamma radiation, Bruce Banner transforms into the Hulk when angry. Despite his destructive power, he struggles to control his inner rage and seeks peace.", "https://i.pinimg.com/736x/8f/06/94/8f0694a0d259a5862cbf661fd4273183.jpg")
    )

    public fun getHeroList(): List<Hero>
    {
        return heroes;
    }

    fun getHerobyId(id : Int): Hero?
    {
        return heroes.find { it.id == id};
    }
}