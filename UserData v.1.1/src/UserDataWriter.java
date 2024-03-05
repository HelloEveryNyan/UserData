import java.io.FileWriter;
import java.io.IOException;

public class UserDataWriter {

    public static void saveToFile(UserData userData) throws IOException {
        String fileName = userData.lastName + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.format("%s %s %s %tD %d %c%n",
                    userData.lastName, userData.firstName, userData.middleName,
                    userData.birthDate, userData.phoneNumber, userData.gender));
        }
    }
}

