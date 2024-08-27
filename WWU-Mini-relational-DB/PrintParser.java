
public class PrintParser {
    /* Reference to the input string being parsed */
    private String input;
    private String[] strArr;

	/* Constructor to initialize the input field */
    public PrintParser(String unparsedInput) {
        input = unparsedInput;
        strArr = input.split(" ", 2);
	}
	
	/* Parses and returns the names the relations to print */
    public String[] parseRelationNames() {
        String s = strArr[1];
        String[] relations = s.split("\\W+");
        return relations;
    }
}