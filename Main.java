import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take years from the user
        int[] years = askForYears(scanner);
        int fromYear = years[0];
        int toYear = years[1];

        // Display palindrome dates between the given years
        printBonusDatesBetween(fromYear, toYear);
        
        scanner.close();
    }

    // Function to request years from the user
    public static int[] askForYears(Scanner scanner){
        int[] years = new int[2];

        // Take the first year from the user
        System.out.print("\nEnter a year from which I should start printing dates: ");
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.print("\nThe year must be a number.");
            System.out.print("\nEnter a year from which I should start printing dates: ");
            continue;
        }
        int firstYear = scanner.nextInt();

        // Convert the year to positive if it's negative
        if(firstYear < 0){
            firstYear = firstYear * (-1);
        }

        // Take the second year from the user
        System.out.print("\nEnter a year to which I should print dates: ");
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.print("\nThe year must be a number.");
            System.out.print("\nEnter a year to which I should print dates: ");
            continue;
        }

        // Convert the year to positive if it's negative
        int secondYear = scanner.nextInt();
        if(secondYear < 0){
            secondYear =secondYear * (-1);
        }

        // I'm checking if the user entered a period BC :)
        if(firstYear < secondYear){
            years[0] = firstYear;
            years[1] = secondYear; 
        }else {
            years[0] = secondYear;
            years[1] = firstYear;
        }

        return years;
    }

    // Function to calculate years between two given years
    public static int[] giveYearsBetween(int fromYear, int toYear){
        int [] yearsBetween = new int[toYear-fromYear+1];
        for(int i=0; i<=toYear - fromYear; i++){
            int year = fromYear + i;
            yearsBetween[i] = year;
        }

        return yearsBetween;
    }

    // Function to find palindrome dates within the 'int[] yearsBetween'
    public static List<LocalDate> givePalindromeDates(int[] yearsBetween){
        List<LocalDate> palindromeDates = new ArrayList<>();

        for(int i=0; i < yearsBetween.length; i++){
            int year = yearsBetween[i];
                if(year>=1001 && year<=9999){
                    String yearStr = Integer.toString(year);
                    String monthAndDay = "";
                        for(int j = 3; j >= 0; j--){
                            if(j == 1){
                                monthAndDay += "-";
                            }
                            monthAndDay += yearStr.charAt(j);
                        }
                    String dataStr = yearStr + "-" + monthAndDay;
                    
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate data = LocalDate.parse(dataStr, formatter);

                        // Check if the 'String dataStr' after conversion to 'LocalDate data' has not been changed. 
                        // I noticed that the date 1360-06-31 has been corrected to 1360-06-30,
                        // when parsing from String to LocalDate (String dataStr -> LocalDate data).
                        String dataAfterParse = data.toString();
                        if(!dataAfterParse.equals(dataStr)){
                            continue;
                        }
                        // If 'LocalDate data' after converting back to 'String dataAfterParse' is equal to 'String dataStr',
                        // this is OK and you can add this date to 'List<LocalDate> palindromeDates'.
                        palindromeDates.add(data);

                    }catch (DateTimeParseException e){
                        continue; // If there was an error during parsing, continue to the next year
                    }
                }else {
                    continue; 
                    // Ignore years outside the range 1001-9999,
                    // because you can't make a palindrome out of them.
                }
        }
        
        return palindromeDates;
    }


    // Function to print palindrome dates
    public static void printBonusDatesBetween(int fromYear, int toYear){
        
                int[] yearsBetween = giveYearsBetween(fromYear, toYear);
                List<LocalDate> palindromeDates = givePalindromeDates(yearsBetween);

                for (LocalDate palindromeDate : palindromeDates) {
                    System.out.print("\n" + palindromeDate);
                }
        
                System.out.println("\n");
    }


}

