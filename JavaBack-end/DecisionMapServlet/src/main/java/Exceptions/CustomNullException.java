package Exceptions;

public class CustomNullException extends NullPointerException {
    // Member Variable
    private String message = "This node is null";

    // Constructor
    public CustomNullException(String input) {
        super(input);
    }

    // Public Methods
    public String getMessage(){
        String str = super.getMessage();

        if (str != "" || str != null){
            return str;
        } else{
            return message;
        }
    }

}
