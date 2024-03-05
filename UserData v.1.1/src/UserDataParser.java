import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataParser {

    public static UserData parseInput(String input) throws InputFormatException, ParseException {
        String[] data = input.split("\\s+");
        if (data.length != 6) {
            throw new InputFormatException("Incorrect. Need 6 value.");
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];

        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(data[3]);
        } catch (ParseException e) {
            // Если не удалось разобрать дату в формате "dd.MM.yyyy", попробуем другой формат
            birthDate = new SimpleDateFormat("MM/dd/yy").parse(data[3]);
        }

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            throw new InputFormatException("Incorrect phone number");
        }

        char gender = data[5].charAt(0);

        return new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }
}
