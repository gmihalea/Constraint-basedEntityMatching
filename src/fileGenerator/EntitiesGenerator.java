package fileGenerator;

import server.util.Printer;

import java.io.IOException;
import java.util.Random;

/**
 * Created by geanina on 23.06.2016.
 */
public class EntitiesGenerator {

    String[] languages = {"English", "Spanish", "French", "Italian", "Romanian", "Polish", "Norwegian", "Swedish",
                            "Deutsch"};
    String[] programmingLevel = {"Beginner", "Medium", "Confident"};
    String[] country = {"UK", "Spain", "France", "Italy", "Romania", "Poland", "Germany", "Norway", "Sweden"};
    String[] dedicateTime = {"1-3","3-5", "5-7", "7-10", ">10"};
    String[] timeZone = {"GMT+1", "GMT+2", "GMT+3", "GMT+4"};
    String[] programmingLanguages = {"C", "C++", "C#", "Java", "Python", "Ruby", "R", "PHP", "CSS", "HTML", "jQuery",
            "Matlab", "Bash", "Haskell", "Perl", "Prolog", "Verilog", "VHDL", "Cuda", "Scala" };


    public void generateMentors(final int n, final String path) throws IOException {
        Printer.printInFile(path, "Score,LastName,FirstName,Country,Languages,TimeZone,Email,ProgrammingLanguages,ProgrammingLevel,Gender,DedicatedTime,VCSFamiliar\n");

        Random r = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb;

        for(int i = 0; i < n; ++i) {
            /**
             * SCORE
             */
            double score = 10 * r.nextDouble();
            Printer.printInFile(path, String.format("%,02f", score));

            /**
             * LAST NAME
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String lastName = sb.toString();
            Printer.printInFile(path, "," + String.valueOf(lastName));

            /**
             * FIRST NAME
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String firstName = sb.toString();
            Printer.printInFile(path, "," + String.valueOf(firstName));

            /**
             * COUNTRY
             */
            String country = this.country[r.nextInt(this.country.length)];
            Printer.printInFile(path, "," + country + ",");

            /**
             * LANGUAGES
             */
            int noOfLanguages = r.nextInt(4) + 1;

            for (int k = 0; k < noOfLanguages; ++k) {
                String language = this.languages[r.nextInt(this.languages.length)];
                Printer.printInFile(path, language);
                if (k != noOfLanguages - 1) {
                    Printer.printInFile(path, " ");

                }
            }

            /**
             * TIME ZONE
             */
            String timeZone = this.timeZone[r.nextInt(this.timeZone.length)];
            Printer.printInFile(path, "," + timeZone + ",");

            /**
             * EMAIL
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String email = sb.toString();
            Printer.printInFile(path, String.valueOf(email) + "@gmail.com" + ",");

            /**
             * PROGRAMMING LANGUAGES
             */
            int noOfProgrammingLanguages = r.nextInt(10) + 1;

            for (int k = 0; k < noOfProgrammingLanguages; ++k) {
                String programmingLanguage = this.programmingLanguages[r.nextInt(this.programmingLanguages.length)];
                Printer.printInFile(path, programmingLanguage);
                if (k != noOfProgrammingLanguages - 1) {
                    Printer.printInFile(path, " ");
                }
            }

            /**
             * PROGRAMMING LEVEL
             */
            String programmingLevel = this.programmingLevel[r.nextInt(this.programmingLevel.length)];
            Printer.printInFile(path, "," + programmingLevel);

            /**
             * GENDER
             */
            Printer.printInFile(path, ",Female");

            /**
             * DEDICATED TIME
             */
            String dedicatedTime = this.dedicateTime[r.nextInt(this.dedicateTime.length)];
            Printer.printInFile(path, "," + dedicatedTime);

            /**
             * VCS Familiar
             */
            Printer.printInFile(path, ",Yes");

            Printer.printInFile(path, "\n");
        }
    }

    public void generateMentees(final int n, final String path) throws IOException {

        Printer.printInFile(path, "Score,FirstName,LastName,Gender,HomeCountry,Email,ProgrammingLevel,DedicatedTime,ProgrammingLanguagesKnown,ProgrammingLanguages,Country,TimeZone,Languages\n");

        Random r = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb;

        for(int i = 0; i < n; ++i) {
            /**
             * SCORE
             */
            double score = 10 * r.nextDouble();
            Printer.printInFile(path, String.format("%,02f", score));

            /**
             * FIRST NAME
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String firstName = sb.toString();
            Printer.printInFile(path, "," + String.valueOf(firstName));

            /**
             * LAST NAME
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String lastName = sb.toString();
            Printer.printInFile(path, "," + String.valueOf(lastName));

            /**
             * GENDER
             */
            Printer.printInFile(path, ",Female");

            /**
             * HomeCountry
             */
            Printer.printInFile(path, ",UK");

            /**
             * EMAIL
             */
            sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            String email = sb.toString();
            Printer.printInFile(path, "," + String.valueOf(email) + "@gmail.com");

            /**
             * PROGRAMMING LEVEL
             */
            String programmingLevel = this.programmingLevel[r.nextInt(this.programmingLevel.length)];
            Printer.printInFile(path, "," + programmingLevel);

            /**
             * DEDICATED TIME
             */
            String dedicatedTime = this.dedicateTime[r.nextInt(this.dedicateTime.length)];
            Printer.printInFile(path, "," + dedicatedTime);

            /**
             * PROGRAMMING LANGUAGES KNOWN
             */
            Printer.printInFile(path, ",-,");

            /**
             * PROGRAMMING LANGUAGES
             */
            int noOfProgrammingLanguages = r.nextInt(10) + 1;

            for (int k = 0; k < noOfProgrammingLanguages; ++k) {
                String programmingLanguage = this.programmingLanguages[r.nextInt(this.programmingLanguages.length)];
                Printer.printInFile(path, programmingLanguage);
                if (k != noOfProgrammingLanguages - 1) {
                    Printer.printInFile(path, " ");
                }
            }

            /**
             * COUNTRY
             */
            String country = this.country[r.nextInt(this.country.length)];
            Printer.printInFile(path, "," + country);

            /**
             * TIME ZONE
             */
            String timeZone = this.timeZone[r.nextInt(this.timeZone.length)];
            Printer.printInFile(path, "," + timeZone + ",");

            /**
             * LANGUAGES
             */
            int noOfLanguages = r.nextInt(4) + 1;

            for (int k = 0; k < noOfLanguages; ++k) {
                String language = this.languages[r.nextInt(this.languages.length)];
                Printer.printInFile(path, language);
                if (k != noOfLanguages - 1) {
                    Printer.printInFile(path, " ");

                }
            }
            Printer.printInFile(path, "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        EntitiesGenerator generator = new EntitiesGenerator();
        generator.generateMentees(1000000, "/home/geanina/Desktop/GeneratedFiles/mentees.csv");
    }
}
