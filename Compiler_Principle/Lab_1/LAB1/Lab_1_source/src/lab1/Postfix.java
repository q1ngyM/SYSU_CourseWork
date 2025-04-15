package lab1;
import java.io.*;

/**
 * Custom exception class for representing parsing errors.
 */
class ParseError extends Exception {
	 /**
     * Enumeration of error types: lexical or syntax.
     */
    public enum ErrorType { LEXICAL, SYNTAX }
    private ErrorType type;
    private int position; 
    
    /**
     * Constructs a new ParseError.
     *
     * @param msg  the error message
     * @param type the type of error (LEXICAL or SYNTAX)
     * @param pos  the position of the error in the input
     */
    public ParseError(String msg, ErrorType type, int pos) {
        super(msg);
        this.type = type;
        this.position = pos;
    }
    
    /**
     * Returns the type of this error.
     *
     * @return the error type
     */
    public ErrorType getErrorType() { 
    	return type; 
    }
    
    /**
     * Returns the position where the error occurred.
     *
     * @return the error position
     */
    public int getErrorPosition() { 
    	return position; 
    }
}

/**
 * 
 * converts an infix arithmetic expression to postfix notation.
 *
 * @param lookahead : static int, saving the next character from System.in stream.
 */
class Parser {
	/** The current lookahead character read from System.in. */
	static int lookahead;
	/** The current position of the character being processed (1-based index). */
	static int position;
//	int lookahead; // 1. modified to not static


	/**
     * Constructs a Parser and reads the first character from the input stream.
     *
     * @throws IOException if an I/O error occurs
     */
	public Parser() throws IOException {
		position = 1;
		lookahead = System.in.read();
	}
	
	/**
	 * The entrance function of the Parser.
	 * Firstly call term() to deal with the digit and then call rest() to deal with the operand.
	 * 
	 * @throws IOException
	 */
	void expr() throws IOException {
		while(lookahead != '\n'  && lookahead != '\r' && lookahead != -1) {
			try{
				if (Character.isDigit((char)lookahead)) {
					term();
					rest();
				}else {
//					System.out.println("Current char:" + lookahead);
//					if (lookahead == 13) System.out.println("\nYes\n");
					throw new ParseError("Unexpected symbol: '" + (char)lookahead + "'", 
	                                          ParseError.ErrorType.LEXICAL, position);
				}
			} catch (ParseError e) {
				reportError(e);
				skip();
			}
		
		}
		
	}

	 /**
     * Handles the operator part of the expression after a digit.
     * Supports '+' and '-' followed by another digit.
     *
     * @throws IOException   if an I/O error occurs
     * @throws ParseError    if the input is invalid
     */
	void rest() throws IOException, ParseError {
		while (lookahead == '+' || lookahead == '-') {
			int op = lookahead;
			match(op);
			
		} 
		if (lookahead == '\n'|| lookahead == '\r' || lookahead == -1) {
			return;
		}
		// If read an illegal character or space, report as an error.
		//        System.out.println("Current char:" + lookahead);
		throw new ParseError("Unexpected character: '" + (char)lookahead + "'",
                                  ParseError.ErrorType.LEXICAL, position);
        
	}
	
	
	/**
     * Handles digits in the expression.
     *
     * @throws IOException   if an I/O error occurs
     * @throws ParseError    if the input is invalid (e.g., missing operator)
     */
	void term() throws IOException, ParseError {
		if (Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
			match(lookahead);
			
			// Continuous digit is not allowed.
            if (lookahead != -1 && Character.isDigit((char) lookahead)) {
                throw new ParseError("Missing an operand", 
                                     ParseError.ErrorType.SYNTAX, position);
            }
		} else  {
            if (lookahead == '+' || lookahead == '-') {
                throw new ParseError("Missing an operater", 
                                     ParseError.ErrorType.SYNTAX, position);
            }
            else {
                throw new ParseError("Unexpected character: '" + (char) lookahead + "'", 
                                     ParseError.ErrorType.LEXICAL, position);
            }
        }
	}


	/**
     * Matches the current character with the expected one.
     *
     * @param t the expected character
     * @throws IOException   if an I/O error occurs
     * @throws ParseError    if the character does not match
     */
	void match(int t) throws IOException, ParseError {
		if (lookahead == t)  {
			lookahead = System.in.read();
			position ++;
		}
		else {
			throw new ParseError("Expecting '" + (char) t + "', but not '" + (char) lookahead + "'", 
                    ParseError.ErrorType.SYNTAX, position);
		}
	}
	/**
	 * Report the error e.
	 * @param e
	 */
	 void reportError(ParseError e) {
		 System.out.println("\nError Type: " + e.getErrorType() +
                 "\nPosition: " + e.getErrorPosition() +
                 "\nMessage: " + e.getMessage());
		 System.out.flush();
	 }
	 
	 /**
	  * Recover errors by simply skipping the errors.
	  */
	 void skip() throws IOException {
		 lookahead = System.in.read();
	     position++;
	 }
	    
}


/**
 * Main class for parsing and converting infix to postfix expressions.
 */
public class Postfix {
	/**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if an I/O error occurs
     */
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		System.out.flush();
		new Parser().expr();
		System.out.println("\nEnd of program.");
		System.out.flush();
	}
}

