package Warriors;
// Фабрика для создания Рыцарей
public class KnightFactory implements WarriorFactory 
{
    @Override
    public Warrior createWarrior(String name, int level) 
    {
        return new Knight(name, level);
    }
}