import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest 
{ 
    @Test
    public void testInvalidLogin() 
    {
        try 
        {
            new ClassUser("Log", "Password1@", "user@example.com", 25);
            fail("Ожидалось исключение IllegalArgumentException");
        }
        catch (IllegalArgumentException e) 
        {
            assertEquals("Логин должен содержать не менее 6 символов и состоять только из латинских и кириллических букв и цифр.", e.getMessage());
            System.out.println("UnitTest \"testInvalidLogin\": " + e.getMessage());
        }
    }

    @Test
    public void testInvalidPassword() 
    {
        try 
        {
            new ClassUser("Login1", "pass", "user@example.com", 25);
            fail("Ожидалось исключение IllegalArgumentException");
        }
        catch (IllegalArgumentException e) 
        {
            assertEquals("Пароль должен содержать от 8 до 128 символов, включать как минимум одну заглавную букву, одну строчную букву и одну цифру, и не содержать пробелов.", e.getMessage());
            System.out.println("UnitTest \"testInvalidPassword\": " + e.getMessage());
        }
    }

    @Test
    public void testInvalidEmail()
    {
        try
        {
            new ClassUser("Login1", "Password1@", "invalid-email", 25);
            fail("Ожидалось исключение IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) 
        {
            assertEquals("Некорректный адрес электронной почты.", e.getMessage());
            System.out.println("UnitTest \"testInvalidEmail\": " + e.getMessage());
        }
    }

    @Test
    public void testInvalidAge() 
    {
        try 
        {
            new ClassUser("Login1", "Password1@", "user@example.com", 17);
            fail("Ожидалось исключение IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Возраст должен быть 18 лет или более.", e.getMessage());
            System.out.println("UnitTest \"testInvalidAge\": " + e.getMessage());
        }
    }
}