
public class InsertParser {
    /* Reference to the input string being parsed */
    private String input;
    private String[] strArr;

	/* Constructor to initialize the input field */
    public InsertParser(String unparsedInput) {
        input = unparsedInput;
        strArr = input.split(" ", 3);
	}
	
	/* Parses and returns the name of the relation to insert into */
    public String parseRelationName() {
        return strArr[1];
    }
	
	/* Parses and returns the number of attributes to insert */
    public int parseAttributeCount() {
        int count = 0;
        String attributes = strArr[2];
        String[] attArr = attributes.split("[; ]");
        for (int i = 0; i < attArr.length; i++) {
            if (attArr[i].startsWith("'")) {
                do {
                    i++;
                } while (!attArr[i].endsWith("'"));
                count++;
            } else {
                count++;
            }
        }
        return count;
    }
}
