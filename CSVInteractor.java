import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVInteractor {
    /**
     * Checks if the csv file exists; if it does, verify the header; if it doesn't
     * exist, create a new csv file at that path and add the header.
     * 
     * @return the Scanner object at the file path
     */
    public static void InitFile() {
        File csvFile = new File(Constants.CSVFILEPATH);
        if (!csvFile.exists()) {
            // System.out.println("DEBUG: File did not exist, creating new file.");

            try {
                csvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            // System.out.println("DEBUG: The file already exists.");
        }

        try {
            Scanner scnr = new Scanner(csvFile);
            if (!scnr.hasNextLine()) {
                FileWriter writer = new FileWriter(csvFile);
                writer.write(Constants.HEADER);
                writer.close();

            } else if (scnr.nextLine() != Constants.HEADER) {
                ArrayList<String> lines = new ArrayList<String>();
                while (scnr.hasNextLine()) {
                    lines.add(scnr.nextLine());
                }
                FileWriter writer = new FileWriter(csvFile);
                writer.write(Constants.HEADER + "\n");
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
            }
            scnr.close();

            // FOR DEBUGGING Scanner
            // scnr = new Scanner(csvFile);
            // while (scnr.hasNextLine()) {
            // System.out.println(scnr.nextLine());
            // }
            // scnr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DisplayData() {
        String[] headings = Constants.HEADER.split(",");

        System.out.printf("%-15s", headings[0]);
        System.out.printf("%-15s", headings[1]);
        System.out.printf("%-15s", headings[2]);
        System.out.printf("%-15s", headings[3]);
        System.out.printf("%-15s", headings[4]);
        System.out.printf("%-40s", headings[5]);

        System.out.println();

        try {
            Scanner reader = new Scanner(new File(Constants.CSVFILEPATH));
            // Skip the header
            reader.nextLine();

            while (reader.hasNextLine()) {
                String[] recordData = reader.nextLine().split(",");
                System.out.printf("%-15s",
                        LocalDate.parse(recordData[0], DateTimeFormatter.ofPattern("M-d-yyyy"))
                                .format(DateTimeFormatter.ofPattern("MMM d, yyyy")));
                System.out.printf("% -15.2f", Double.parseDouble(recordData[1]));
                System.out.printf("% -15.2f", Double.parseDouble(recordData[2]));
                System.out.printf("%-15s", recordData[3]);
                System.out.printf("%-15s", recordData[4]);
                System.out.printf("%-40s\n", recordData[5]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void AddExpense() {
        String[] expenseRecord = new String[6];
        System.out.print("Date ('m-d-yyyy') of transaction:\n > ");
        expenseRecord[0] = System.console().readLine();
        System.out.print("Debit or cash amount ('0' if credit was used):\n > ");
        expenseRecord[1] = "-" + System.console().readLine();
        System.out.print("Credit amount ('0' if debit or cash was used):\n > ");
        expenseRecord[2] = "-" + System.console().readLine();
        System.out.print("Where did this transaction take place:\n > ");
        expenseRecord[3] = System.console().readLine();
        System.out.print("What account was used ('Cash' if cash was used):\n > ");
        expenseRecord[4] = System.console().readLine();
        System.out.print("Transaction description:\n > ");
        expenseRecord[5] = System.console().readLine();

        try {
            FileWriter writer = new FileWriter(Constants.CSVFILEPATH, true);
            for (int i = 0; i < expenseRecord.length; ++i) {
                writer.append(expenseRecord[i]);
                if (i != expenseRecord.length - 1) {
                    writer.append(",");
                } else {
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AddIncome() {
        String[] incomeRecord = new String[6];
        System.out.print("Date ('m-d-yyyy') of transaction:\n > ");
        incomeRecord[0] = System.console().readLine();
        System.out.print("Debit or cash amount ('0' if credit was used):\n > ");
        incomeRecord[1] = System.console().readLine();
        System.out.print("Credit amount ('0' if debit or cash was used):\n > ");
        incomeRecord[2] = System.console().readLine();
        System.out.print("Where did this transaction take place:\n > ");
        incomeRecord[3] = System.console().readLine();
        System.out.print("What account was used ('Cash' if cash was used):\n > ");
        incomeRecord[4] = System.console().readLine();
        System.out.print("Transaction description:\n > ");
        incomeRecord[5] = System.console().readLine();

        try {
            FileWriter writer = new FileWriter(Constants.CSVFILEPATH, true);
            for (int i = 0; i < incomeRecord.length; ++i) {
                writer.append(incomeRecord[i]);
                if (i != 5) {
                    writer.append(",");
                } else {
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GetInsights() {

    }

    public static void GetTithing() {

    }
}