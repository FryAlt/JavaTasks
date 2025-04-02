import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class StudentManager 
{
    private List<Student> students;
    private static final String LOG_FILE = "student_log.txt";
    private static final String ERROR_LOG_FILE = "error_log.txt";
    private static final String PHONE_REGEX = "^\\+?[0-9]{10,15}$";
    private static final String NAME_REGEX = "^[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+$";

    public StudentManager() 
    {
        this.students = new ArrayList<>();
        createLogFiles();
    }

    private void createLogFiles() {
        try 
        {
            if (!Files.exists(Paths.get(LOG_FILE))) 
                Files.createFile(Paths.get(LOG_FILE));
            
            if (!Files.exists(Paths.get(ERROR_LOG_FILE))) 
                Files.createFile(Paths.get(ERROR_LOG_FILE));            
        } 
        catch (IOException e) 
        {
            System.err.println("Ошибка при создании лог-файлов: " + e.getMessage());
        }
    }

    private void logToFile(String message, String file) 
    {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = "[" + timestamp + "] " + message + "\n";

        try 
        {
            Files.write(Paths.get(file), logMessage.getBytes(), StandardOpenOption.APPEND);
        } 
        catch (IOException e) 
        {
            System.err.println("Ошибка при записи в лог-файл: " + e.getMessage());
        }
    }

    public void addStudent(String fullName, String phoneNumber, int course) 
    {
        try 
        {
            validateFullName(fullName);
            validatePhoneNumber(phoneNumber);
            validateCourse(course);

            Student student = new Student(fullName, phoneNumber, course);
            students.add(student);
            
            String logMessage = "Добавлен новый студент: " + student.toString();
            System.out.println(logMessage);
            logToFile(logMessage, LOG_FILE);
        } 
        catch (IllegalArgumentException e) 
        {
            String errorMessage = "Ошибка при добавлении студента: " + e.getMessage() + 
                                " (ФИО: " + fullName + ", Телефон: " + phoneNumber + ", Курс: " + course + ")";
            System.err.println(errorMessage);
            logToFile(errorMessage, ERROR_LOG_FILE);
        }
    }

    public void addGrade(String studentFullName, String subject, int grade) 
    {
        try 
        {
            Student student = findStudent(studentFullName);
            validateGrade(grade);
            
            if (student.getSubjects().containsKey(subject)) 
            {
                int currentGrade = student.getSubjects().get(subject);
                if (currentGrade + grade > 100) 
                    throw new IllegalArgumentException("Сумма баллов по предмету не может превышать 100");
                
                student.getSubjects().put(subject, currentGrade + grade);
            } 
            else            
                student.getSubjects().put(subject, grade);            
            
            String logMessage = "Студенту " + studentFullName + " добавлено " + grade + 
                              " баллов по предмету " + subject + ". Текущий балл: " + 
                              student.getSubjects().get(subject);
            System.out.println(logMessage);
            logToFile(logMessage, LOG_FILE);
        } 
        catch (IllegalArgumentException | IllegalStateException e) 
        {
            String errorMessage = "Ошибка при добавлении баллов студенту " + studentFullName + 
                                " по предмету " + subject + ": " + e.getMessage();
            System.err.println(errorMessage);
            logToFile(errorMessage, ERROR_LOG_FILE);
        }
    }

    public void requestSkipToken(String studentFullName) 
    {
        try 
        {
            Student student = findStudent(studentFullName);
            
            if (student.getSkipTokens() <= 0) 
                throw new IllegalStateException("Лимит пропусков исчерпан");
                        
            student.useSkipToken();
            String logMessage = "Студент " + studentFullName + " взял справку для пропуска. Осталось пропусков: " + 
                              student.getSkipTokens();
            System.out.println(logMessage);
            logToFile(logMessage, LOG_FILE);
        } 
        catch (IllegalArgumentException | IllegalStateException e) 
        {
            String errorMessage = "Ошибка при выдаче справки студенту " + studentFullName + ": " + e.getMessage();
            System.err.println(errorMessage);
            logToFile(errorMessage, ERROR_LOG_FILE);
        }
    }

    public void expelStudent(String studentFullName) 
    {
        try 
        {
            Student student = findStudent(studentFullName);
            student.setExpelled(true);
            
            String logMessage = "Студент " + studentFullName + " отчислен";
            System.out.println(logMessage);
            logToFile(logMessage, LOG_FILE);
        } 
        catch (IllegalArgumentException e) 
        {
            String errorMessage = "Ошибка при отчислении студента " + studentFullName + ": " + e.getMessage();
            System.err.println(errorMessage);
            logToFile(errorMessage, ERROR_LOG_FILE);
        }
    }

    private Student findStudent(String fullName) 
    {
        for (Student student : students) 
        {
            if (student.getFullName().equals(fullName) && !student.isExpelled())
                return student;            
        }
        throw new IllegalArgumentException("Студент не найден или отчислен");
    }

    // Валидации
    private void validateFullName(String fullName) 
    {
        if (!Pattern.matches(NAME_REGEX, fullName)) 
            throw new IllegalArgumentException("ФИО должно быть в формате 'Фамилия Имя Отчество' на кириллице");
        
    }

    private void validatePhoneNumber(String phoneNumber) 
    {
        if (!Pattern.matches(PHONE_REGEX, phoneNumber))
            throw new IllegalArgumentException("Номер телефона должен содержать от 10 до 15 цифр");        
    }

    private void validateCourse(int course) 
    {
        if (course < 1 || course > 6)
            throw new IllegalArgumentException("Курс должен быть от 1 до 6");        
    }

    private void validateGrade(int grade) 
    {
        if (grade <= 0) 
            throw new IllegalArgumentException("Баллы должны быть положительным числом");
        
        if (grade > 100) 
            throw new IllegalArgumentException("Баллы по одному предмету не могут превышать 100");        
    }
}