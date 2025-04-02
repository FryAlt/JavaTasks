package Warriors;
// Конкретный класс Лучника
public class Archer extends Warrior 
{
    private int arrows;
    
    public Archer(String name, int level) 
    {
        super(name, level);
        this.attack = 15 + level * 3;
        this.defense = 8 + level;
        this.health = 60 + level * 4;
        this.arrows = 20 + level * 2;
        System.out.println("Лучник " + name + " создан с " + arrows + " стрелами");
    }
    
    @Override
    public void uniqueAbility() 
    {
        System.out.println(name + " стреляет точной стрелой!");
        arrows--;
    }
    
    public int getArrows() 
    {
        return arrows;
    }
}