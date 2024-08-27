
public class RelationParser {
    /* Reference to the input string being parsed */
    private String input;
    private String[] strArr;

	/* Constructor to initialize the input field */
    public RelationParser(String unparsedInput) {
        input = unparsedInput;
        strArr = input.split(" ", 3);
	}
	
	/* Parses and returns the name of the relation to create */
    public String parseRelationName() {
        return strArr[1];
    }
	
	/* Parses and returns the number of attributes to create */
    public int parseAttributeCount() {
        int i = 0;
        int count = 0;
        int commaCount = 0;
        boolean syntax = true;
        while (strArr[2].charAt(i) != ';') {
            switch (strArr[2].charAt(i)) {
                case '(':
                    syntax = false;
                    i++;
                    break;
                case ')':
                    syntax = true;
                    i++;
                    break;
                case ',':
                    i++;
                    break;
                case ' ':
                    i++;
                    break;
                default:
                    //keep reading until break or space
                    boolean exit = false;
                    while (exit == false) {
                        char curChar = strArr[2].charAt(i);
                        if (Character.isWhitespace(curChar) || curChar == ')' 
                        || curChar == ';' || curChar == '(') {
                            count++;
                            exit = true;
                        } else if (curChar == ',') {
                            count++;
                            commaCount++;
                            exit = true;
                        } else {
                            i++;
                        }
                    }
                    break;
            }
        }
        if (syntax == false) {
            count = -1;
        }
        return (count/(commaCount + 1));
    }
}