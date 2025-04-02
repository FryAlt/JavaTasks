package Warriors;
// Абстрактный базовый класс воина
public abstract class Warrior 
{
    protected String name;
    protected int attack;
    protected int defense;
    protected int health;
    protected int level;
    
    public Warrior(String name, int level) 
    {
        this.name = name;
        this.level = level;
    }
    
    public abstract void uniqueAbility();
    
    public void displayStats() 
    {
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Health: " + health);
    }
    
    // Геттеры и сеттеры
    public int getAttack() 
    {
        return attack;
    }
    
    public int getDefense() 
    {
        return defense;
    }
    
    public int getHealth() 
    {
        return health;
    }
    
    public int getLevel() 
    {
        return level;
    }
    
    public String getName() 
    {
        return name;
    }
}