import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Enter data manual");
        System.out.println("2. Read data file");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            writeToFile();
        } else if (choice == 2) {
            readFromFile();
        } else {
            System.out.println("Incorrect option. End programme.");
        }

        scanner.close();
    }

    private static void writeToFile() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter surname: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter middle name: ");
            String middleName = scanner.nextLine();

            System.out.print("Enter birthday. Format dd.MM.yyyy: ");
            String birthDateStr = scanner.nextLine();
            Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDateStr);

            System.out.print("Enter phone number: ");
            long phoneNumber = Long.parseLong(scanner.nextLine());

            System.out.print("Sex (m/f): ");
            char gender = scanner.nextLine().charAt(0);

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            UserDataWriter.saveToFile(userData);

            System.out.println("Data safe successfully.");
        } catch (IOException | ParseException | NumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void readFromFile() {
        System.out.print("Enter reading file name : ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<UserData> userList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    UserData userData = UserDataParser.parseInput(line);
                    userList.add(userData);
                } catch (InputFormatException | ParseException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            for (UserData userData : userList) {
                System.out.println(userData.lastName + " " + userData.firstName + " " + userData.middleName +
                        " " + userData.birthDate + " " + userData.phoneNumber + " " + userData.gender);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}