public class AInteger{
    // Public String that stores the integer of arbitrary length
    public String s;
    // Default constructor that initializes the value of string with 0 initially.
    public AInteger(){
        this.s = "0";
    }
    // Constructor that initializes the instance of a number as a string with passed argument
    public AInteger(String s){
        this.s = s;
    }
    // Copy constructor that creates an instance of AInteger
    public AInteger(AInteger other_copy){
        this.s = other_copy.s;
    }


}