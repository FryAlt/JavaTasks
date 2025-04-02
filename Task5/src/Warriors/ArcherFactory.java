package Warriors;
// Фабрика для создания Лучников
public class ArcherFactory implements WarriorFactory 
{
    @Override
    public Warrior createWarrior(String name, int level) 
    {
        return new Archer(name, level);
    }
}