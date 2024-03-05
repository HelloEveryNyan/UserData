import java.util.Date;

public class UserData {
    String lastName;
    String firstName;
    String middleName;
    Date birthDate;
    long phoneNumber;
    char gender;

    public UserData(String lastName, String firstName, String middleName, Date birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
