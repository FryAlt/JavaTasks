package Warriors;
// Конкретный класс Мага
public class Mage extends Warrior 
{
    private int mana;
    
    public Mage(String name, int level) 
    {
        super(name, level);
        this.attack = 10 + level * 2;
        this.defense = 5 + level;
        this.health = 50 + level * 5;
        this.mana = 100 + level * 10;
        System.out.println("Маг " + name + " создан с " + mana + " маны");
    }
    
    @Override
    public void uniqueAbility() 
    {
        System.out.println(name + " использует мощный огненный шар!");
        mana -= 30;
    }
    
    public int getMana() 
    {
        return mana;
    }
}