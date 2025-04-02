package Warriors;
// Фабрика для создания Магов
public class MageFactory implements WarriorFactory 
{
    @Override
    public Warrior createWarrior(String name, int level) 
    {
        return new Mage(name, level);
    }
}