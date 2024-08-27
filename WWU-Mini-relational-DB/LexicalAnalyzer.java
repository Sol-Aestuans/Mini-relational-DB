
import java.io.*;
import java.util.Scanner;

public class LexicalAnalyzer {
	/* Parses the given file into individual commands
		and passes each to the appropriate parser */
    public void run(String fileName) {

      try {
         File inFile = new File(fileName);
         Scanner sc = new Scanner(inFile);

         while (sc.hasNextLine()) {
            String curLine;
            curLine = sc.nextLine();

            // skips comments and blank lines
            if (curLine.length() != 0) {
               if(curLine.charAt(0) != '#') {

                  StringBuilder strBuild = new StringBuilder();
                  strBuild.append(curLine);
                  String lineStr;
                  if (curLine.endsWith(";")) {
                     // pass strBuild as String into whichParser
                     lineStr = strBuild.toString().trim();
                     whichParser(lineStr);
                  } else {
                     String splitLine = "";
                     while (!splitLine.endsWith(";")) {
                        splitLine = sc.nextLine();
                        strBuild.append(splitLine.trim());
                     }
                     // same as line 26
                     lineStr = strBuild.toString();
                     whichParser(lineStr);
                  }
               }
            }
         }
      sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("File not found error");
         e.printStackTrace();
      }
   }
    // helper method for run
    /*
     * Takes a string as an input and passes it to the relevent parsers
     * based on the first character
     */
    public void whichParser(String unparsedString) {
      int attCount;
      String relName;
      switch(unparsedString.charAt(0)) {
         case 'R':
            if (checkCommand(unparsedString, 'R')) {
               RelationParser rParser = new RelationParser(unparsedString);
               relName = rParser.parseRelationName();
               attCount = rParser.parseAttributeCount();
               System.out.println("Creating " + relName + " with " + attCount + " attributes");
            }
            break;
         case 'I':
            if (checkCommand(unparsedString, 'I')) {
               InsertParser iParser = new InsertParser(unparsedString);
               attCount = iParser.parseAttributeCount();
               relName = iParser.parseRelationName();
               System.out.println("Inserting " + attCount + " attributes to " + relName + ".");
            }
            break;
         case 'P':
            if (checkCommand(unparsedString, 'P')) {
               PrintParser printParser = new PrintParser(unparsedString);
               String[] relationNames = printParser.parseRelationNames();
               System.out.print("Printing " + relationNames.length + " relations: ");
               for (int i = 0; i < relationNames.length - 1; i++) {
                  System.out.print(relationNames[i] + ", ");
               }
               System.out.print(relationNames[relationNames.length - 1]);
               System.out.println(".");
            }
            break;
         default:
            break;
      }
   }
   /*
    * Checks if the command based on the key is valid
    * returns a boolean
    */
   public boolean checkCommand(String unparsedString, char key) {
      String[] command = unparsedString.split(" ", 2);
      boolean valid = false;
      switch (key) {
         case 'P':
            if (command[0].equals("PRINT")) {
               valid = true;
            }
            break;
         case 'R':
            if (command[0].equals("RELATION")) {
               valid = true;
            }
            break;
         case 'I':
            if (command[0].equals("INSERT")) {
               valid = true;
            }
            break;
         default:
            System.out.println("Unrecognized command " + command[0]);
            break;
      }
      return valid;
   }
}
