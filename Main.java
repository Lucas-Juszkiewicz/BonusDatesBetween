import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        scanner.close();
    }

    public static int[] askForYears(Scanner scanner){
        int[] years = new int[2];

        System.out.println("\nEnter the year from which I should start printing dates: ");
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println("The year must be a number greater than or equal to zero.");
            continue;
        }
        int firstYear = scanner.nextInt();

        System.out.println("\nEnter the year to which I should print dates: ");
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println("The year must be a number greater than or equal to zero.");
            continue;
        }
        int secondYear = scanner.nextInt();

        if(firstYear < secondYear){
            years[0] = firstYear;
            years[1] = secondYear; 
        }else {
            years[0] = secondYear;
            years[1] = firstYear;
        }

        return years;
    }

    public static int[] giveYearsBetween(int[] years){
        int fromYear = years[0];
        int toYear = years[1];
        int [] yearsBetween = new int[toYear-fromYear+1];
        for(int i=0; i<=toYear - fromYear; i++){
            int year = fromYear + i;
            yearsBetween[i] = year;
        }

        return yearsBetween;
    }

    public static int[] givePalindromeDates(int [] yearsBetween){
        for(int i=0; i<=yearsBetween.length; i++){
            int year = yearsBetween[i];
            String yearStr = Integer.toString(year);
            String reversedYearStr = "";
                for(int j = yearStr.length() - 1; j >= 0; j--){
                    reversedYearStr += yearStr.charAt(j);
                }
            String data = yearStr + "-" + reversedYearStr;
        }
    }



    public static void printBonusDatesBetween(int fromYear, int toYear){
        int[] yearsBetween = giveYearsBetween(fromYear, toYear);
        
}


}

