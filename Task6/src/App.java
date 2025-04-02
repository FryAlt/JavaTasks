public class App 
{
    public static void main(String[] args) 
    {
        StudentManager manager = new StudentManager();

        // Добавление студентов
        manager.addStudent("Иванов Иван Иванович", "+79123456789", 1);
        manager.addStudent("Петров Петр Петрович", "79234567890", 2);
        
        // Неправильные данные (будут записаны в error_log.txt)
        manager.addStudent("Неправильное Имя", "123", 7);
        manager.addStudent("Сидоров Сидор Сидорович", "не_номер", 3);

        // Работа со студентами
        manager.addGrade("Иванов Иван Иванович", "Математика", 30);
        manager.addGrade("Иванов Иван Иванович", "Математика", 50); 
        manager.addGrade("Иванов Иван Иванович", "Математика", 30); // Ошибка - превышение 100 баллов
        
        manager.requestSkipToken("Иванов Иван Иванович");
        manager.requestSkipToken("Иванов Иван Иванович");
        manager.requestSkipToken("Иванов Иван Иванович");
        manager.requestSkipToken("Иванов Иван Иванович"); // Ошибка - лимит исчерпан
        
        manager.expelStudent("Петров Петр Петрович");
        manager.addGrade("Петров Петр Петрович", "Физика", 20); // Ошибка - студент отчислен
    }
}