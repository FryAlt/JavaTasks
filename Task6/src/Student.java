import java.util.HashMap;
import java.util.Map;

public class Student 
{
    private String fullName;
    private String phoneNumber;
    private int course;
    private Map<String, Integer> subjects;
    private int skipTokens;
    private boolean isExpelled;

    public Student(String fullName, String phoneNumber, int course) 
    {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.course = course;
        this.subjects = new HashMap<>();
        this.skipTokens = 3;
        this.isExpelled = false;
    }

    // Геттеры и сеттеры
    public String getFullName() 
    {
        return fullName;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public int getCourse() 
    {
        return course;
    }

    public Map<String, Integer> getSubjects() 
    {
        return subjects;
    }

    public int getSkipTokens() 
    {
        return skipTokens;
    }

    public boolean isExpelled() 
    {
        return isExpelled;
    }

    public void setExpelled(boolean expelled) 
    {
        isExpelled = expelled;
    }

    public void useSkipToken() 
    {
        if (skipTokens > 0) 
            skipTokens--;        
    }

    @Override
    public String toString() 
    {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", course=" + course +
                ", subjects=" + subjects +
                ", skipTokens=" + skipTokens +
                ", isExpelled=" + isExpelled +
                '}';
    }
}