package Warriors;
// Интерфейс фабрики воинов
public interface WarriorFactory 
{
    Warrior createWarrior(String name, int level);
}