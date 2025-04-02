package Warriors;
// Конкретный класс Рыцаря
public class Knight extends Warrior 
{
    private int armorDurability;
    
    public Knight(String name, int level) 
    {
        super(name, level);
        this.attack = 20 + level * 2;
        this.defense = 15 + level * 2;
        this.health = 100 + level * 6;
        this.armorDurability = 200 + level * 20;
        System.out.println("Рыцарь " + name + " создан с броней прочностью " + armorDurability);
    }
    
    @Override
    public void uniqueAbility() 
    {
        System.out.println(name + " наносит удар щитом!");
        armorDurability -= 10;
    }
    
    public int getArmorDurability() 
    {
        return armorDurability;
    }
}