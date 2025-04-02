import Warriors.*;

public class WarriorGame
{
    public static void main(String[] args) 
    {
        // Создаем фабрики
        WarriorFactory mageFactory = new MageFactory();
        WarriorFactory archerFactory = new ArcherFactory();
        WarriorFactory knightFactory = new KnightFactory();
        
        // Создаем воинов с помощью фабрик
        Warrior mage = mageFactory.createWarrior("Gandalf", 5);
        Warrior archer = archerFactory.createWarrior("Legolas", 7);
        Warrior knight = knightFactory.createWarrior("Arthur", 10);
        
        // Используем воинов
        System.out.println("\nМаг:");
        mage.displayStats();
        mage.uniqueAbility();
        
        System.out.println("\nЛучник:");
        archer.displayStats();
        archer.uniqueAbility();
        
        System.out.println("\nРыцарь:");
        knight.displayStats();
        knight.uniqueAbility();
    }
}
