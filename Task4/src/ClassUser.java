import java.util.regex.Pattern;

public class ClassUser 
{
    private String login;
    private String password;
    private String email;
    private int age;

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9а-яА-ЯёЁ]{6,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*\\d)[a-zA-Zа-яА-ЯёЁ\\d~!?@#$%^&*()\\_\\-+\\[\\]{}><\\|\"'.:,;]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public ClassUser(String login, String password, String email, int age) 
    {
        if (!isValidLogin(login)) 
            throw new IllegalArgumentException("Логин должен содержать не менее 6 символов и состоять только из латинских и кириллических букв и цифр.");
        
        if (!isValidPassword(password)) 
            throw new IllegalArgumentException("Пароль должен содержать от 8 до 128 символов, включать как минимум одну заглавную букву, одну строчную букву и одну цифру, и не содержать пробелов.");
        
        if (!isValidEmail(email)) 
            throw new IllegalArgumentException("Некорректный адрес электронной почты.");
        
        if (!isValidAge(age)) 
            throw new IllegalArgumentException("Возраст должен быть 18 лет или более.");
        
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    private boolean isValidLogin(String login)
    {
        return LOGIN_PATTERN.matcher(login).matches();
    }

    private boolean isValidPassword(String password) 
    {
        return password.length() >= 8 && password.length() <= 128 && PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean isValidEmail(String email) 
    {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidAge(int age) 
    {
        return age >= 18;
    }

    public String getLogin() 
    {
        return login;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getEmail() 
    {
        return email;
    }

    public int getAge() 
    {
        return age;
    }
}
