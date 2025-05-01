package arbitraryarithmetic;
// packages both AInteger.java and AFloat.java into a single package arbitraryarithmetic

import java.lang.Math;

public class AFloat{
    // added 2 public string and character variables for storing the magnitude and sign of a number
    // By default 0.0 is taken to be positive - can be considered a limitation
    public String s; // magnitude
    public char sign; // sign
    // default constructor if no arguments are given
    public AFloat(){
        this.s = "0.0";
        this.sign = '+';
    }
    // constructor for a given string argument passed as parameter
    public AFloat(String s){
     try{
        // Handled invalid input if the given string is empty or null / invalid 
        if (s == null || s.isEmpty()){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        // stores the first character as a sign if provided or otherwise stores + by default
        if(s.charAt(0)=='+' || s.charAt(0)== '-'){
            this.sign = s.charAt(0);
            s = s.substring(1);
        }else if(Character.isDigit(s.charAt(0))){
            this.sign = '+';
        }
        // decimal_count stores the number of decimal points or '.' - invalid if greater than 1 - handles validation
    int decimal_count = 0;
    for (char c : s.toCharArray()) {
        
        if (!(Character.isDigit(c)) && decimal_count >= 1){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(c=='.'){
            decimal_count ++;
        }
    }
   // adds a decimal point and .0 at the end if integer is given to convert to float - puts zero if starts with a . and adds 0 at the end if ends with a .

    if (!s.contains(".")) {
        s += ".0";
        }
        if (s.startsWith(".")) {
            s = "0" + s;
        }
        if (s.endsWith(".")) {
        s = s + "0";
        }
       this.s = s;
     }catch(IllegalArgumentException e){
        // catches and handles invalid input exception 
        System.out.println(e.getMessage());
        System.exit(0);
     } 
    }
    // Copy constructor written 
    public AFloat(AFloat other_copy){
        this.s = other_copy.s;
        this.sign = other_copy.sign;
    }
    // parser returns a AFloat class object from a string
    public static AFloat parse(String s){
        return new AFloat(s);
    }
    // converts integer to a float and returns an AFloat object
    private static AFloat int_to_float(String s){
        if (!s.contains(".")) {
        s += ".0";
        }
        if (s.startsWith(".")) {
            s = "0" + s;
        }
        if (s.endsWith(".")) {
        s = s + "0";
        }

        return parse(s);
    }
    // pads given number of zeroes to the left of the string
    private static String pad_left_zeroes(String s,int n){
        for(int i=0;i<n;i++){
            s = "0"+s;
        }
        return s;

    }
    // pads given number of zeroes to the right of the string
    private static String pad_right_zeroes(String s,int n){
        for(int i=0;i<n;i++){
            s = s+"0";

        }
        return s;

    }
// handles addition of two floating point numbers - mainly done using AInteger addition and instead added some decimal tracker
// like thing for handling the decimal point and doing AInteger addition and then placing back the decimal in result in the appropriate position
// handled logic only for two non negative numbers and written rest by repeating the code with taking only magnitudes

    public static AFloat add(AFloat s1, AFloat s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AFloat s2_mod = new AFloat(s2.s);
           AFloat result = subtract(s1,s2_mod);
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
           AFloat s1_mod = new AFloat(s1.s);
            return subtract(s2,s1_mod);
        }else if(s1.sign=='-' && s2.sign=='-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.add(s1_mod, s2_mod);
            result.sign = '-';
            return result;

        }
        // find out the decimal place index
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length(); // number of decimal digits in a
        int num_decimals_b = b.substring(b_decimal_place+1).length(); // number of decimal digits in b
        int num_digits_a = a.substring(0,a_decimal_place).length(); // number of digits before deciaml point
        int num_digits_b = b.substring(0,b_decimal_place).length(); // number of digits before decimal point
        // Pad left zeroes and right zeroes to make equal length strings for addition
        if(num_digits_a < num_digits_b){
            int diff = num_digits_b - num_digits_a;
            a = pad_left_zeroes(a,diff);
        }else{
            int diff = num_digits_a - num_digits_b;
            b = pad_left_zeroes(b,diff);
        }

        if(num_decimals_a < num_decimals_b){
            int diff = num_decimals_b - num_decimals_a;
            a = pad_right_zeroes(a,diff);
        }else{
            int diff = num_decimals_a - num_decimals_b;
            b = pad_right_zeroes(b,diff);
        }
        // Again make them into integers
        a_decimal_place = 0;
        b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // After making into integers , use the AInteger add method and then add the decimal point in the correct place 
        // return the AFloat object after addition
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        int decimal_length = a.substring(a_decimal_place+1).length();
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger sum = AInteger.add(a_integer,b_integer);
        String result = sum.s;
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        AFloat result_float = new AFloat(result);
        return result_float;
        


    }
    // handled the subtraction of 2 floationg point numbers - initially writing the logic for two non negative numbers and then extending it to 
    // negative number cases
    // Similar to addition , used a decimal tracker then made to integer , did subtraction using AInteger.subtract and then
    // finally made into a AFloat object after subtraction
    public static AFloat subtract(AFloat s1, AFloat s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AFloat s2_mod = new AFloat(s2.s);
           AFloat result = add(s1,s2_mod);
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
           AFloat s1_mod = new AFloat(s1.s);
           AFloat result = add(s1_mod,s2);
           result.sign = '-';
           return result;
        }else if(s1.sign=='-' && s2.sign=='-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = subtract(s2_mod,s1_mod);
            
            return result;

        }
        
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        int num_digits_a = a.substring(0,a_decimal_place).length();
        int num_digits_b = b.substring(0,b_decimal_place).length();
        // Pad left zeroes and right zeroes to make equal length strings for addition
        if(num_digits_a < num_digits_b){
            int diff = num_digits_b - num_digits_a;
            a = pad_left_zeroes(a,diff);
        }else{
            int diff = num_digits_a - num_digits_b;
            b = pad_left_zeroes(b,diff);
        }

        if(num_decimals_a < num_decimals_b){
            int diff = num_decimals_b - num_decimals_a;
            a = pad_right_zeroes(a,diff);
        }else{
            int diff = num_decimals_a - num_decimals_b;
            b = pad_right_zeroes(b,diff);
        }
        // Again make them into integers
        a_decimal_place = 0;
        b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        int decimal_length = a.substring(a_decimal_place+1).length();
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger difference = AInteger.subtract(a_integer,b_integer);
        String result = difference.s;
        char result_sign = difference.sign;
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        AFloat result_float = new AFloat(result);
        result_float.sign = result_sign;
        return result_float;
        


    }
    // removes excess zeroes on the right probably after the decimal digit 
    private static String remove_excess_zeroes_on_right(String str){
        boolean first_non_zero_decimal_digit_found = false;
        int len = str.length();
        int num_excess_zeroes_on_right = 0;
        for(int i=len-1;i>=0;i--){
            char digit = str.charAt(i);
            if(digit != '0'){
                first_non_zero_decimal_digit_found = true;
                break;
            }else{
                num_excess_zeroes_on_right ++;

            }
        }
        String processed_str = str.substring(0,len-num_excess_zeroes_on_right);
        return processed_str;
    }
    // Handled multiplication for two non negative float numbers using AiNteger.multiply similar to add and subtract
    // number of decimals in a*b if a has m decimal digits and b has n decimal digits is m + n.
    // handled all the negative number cases similar to non negative things

    public static AFloat multiply(AFloat s1, AFloat s2){
        if(s1.sign == '-' && s2.sign == '+'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat result = AFloat.multiply(s1_mod,s2);
            result.sign = '-';
            return result;
        }else if(s1.sign == '+' && s2.sign == '-'){
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.multiply(s1,s2_mod);
            result.sign = '-';
            return result;
        }else if(s1.sign == '-' && s2.sign == '-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.multiply(s1_mod,s2_mod);
            result.sign = '+';
            return result;
        }
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        int decimal_length = num_decimals_a + num_decimals_b;
        // Pad left zeroes and right zeroes to make equal length strings for addition
        
        // Again make them into integers
        
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger product = AInteger.multiply(a_integer,b_integer);
        String result = product.s;
        
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        result = remove_excess_zeroes_on_right(result);
        result = int_to_float(result).s;
        AFloat result_float = new AFloat(result);
        
        return result_float;
        




    }
    // created a custom DivisionByZeroException for handling cases where denominator is zero
    public static class DivisionByZeroException extends ArithmeticException{
        public DivisionByZeroException(){
            super("Division by zero is not allowed.");
        }
        public DivisionByZeroException(String message){
            super(message);
        }
    }
 // handled floating point division using AInteger.divide methos using
 // wrote code for only non negative numbers and then handled remaining cases of negative numbers accordingly

    public static AFloat divide(AFloat s1, AFloat s2){

        try{
            // checks if the denominator is zero
            boolean is_denominator_zero = true;
            for(int i=0;i<s2.s.length();i++){
                char ch = s2.s.charAt(i);
                if(ch != '0' && ch != '.'){
                    is_denominator_zero = false;
                    break;
                }else{
                    continue;
                }
            }
            if(is_denominator_zero){
                // throws custom exception
                throw new DivisionByZeroException("Division by zero error");
            }
        }catch(DivisionByZeroException e){
            // handles the catched exception
            System.out.println(e.getMessage());
            System.exit(0);
        }

        boolean is_numerator_zero = true;
            for(int i=0;i<s1.s.length();i++){
                char ch = s1.s.charAt(i);
                if(ch != '0' && ch != '.'){
                    is_numerator_zero = false;
                    break;
                }else{
                    continue;
                }
            }
            // if numberator is zero returns 0.0
        if(is_numerator_zero){
            return new AFloat("0.0");
        }
// handles all cases
        if(s1.sign == '-' && s2.sign == '+'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat result = AFloat.divide(s1_mod,s2);
            result.sign = '-';
            return result;
        }else if(s1.sign == '+' && s2.sign == '-'){
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.divide(s1,s2_mod);
            result.sign = '-';
            return result;
        }else if(s1.sign == '-' && s2.sign == '-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.divide(s1_mod,s2_mod);
            result.sign = '+';
            return result;
        }

        // Actual logic of float division for two positive floating point numbers

// Logic used : Convert the strings to integers for both numerator and denominator and multiply numerator by 10^30
// This is for thirty digits precision. Then use AInteger division method for the obtained integers and finally place the decimal point
// at the expected place. In between , handle all the excessive zeroes cases etc. with the help of helper functions
// Returns a final AFloat object
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        String a_int = "";
        String b_int = "";
        

        if(num_decimals_a > num_decimals_b){
            a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
            b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
            int diff_decimals = num_decimals_a - num_decimals_b;
            for(int i=0;i<diff_decimals;i++){
                b_int += "0";

            }
        }else{
            a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
             b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
            int diff_decimals = num_decimals_b - num_decimals_a;
            for(int i=0;i<diff_decimals;i++){
                a_int += "0";

            }
        }


        String thirty_precision_maker = "1";
        for(int i=0;i<30;i++){
            thirty_precision_maker += "0";
        }

        

        a_int = (AInteger.multiply(new AInteger(a_int),new AInteger(thirty_precision_maker))).s;

        AInteger numerator = new AInteger(a_int);
        AInteger denominator = new AInteger(b_int);

        AInteger result = AInteger.divide(numerator,denominator);

        String result_int = result.s;
        while (result_int.length() <= 30) {
    result_int = "0" + result_int;
}

        
        String result_float = result_int.substring(0,result_int.length()-30) + "." + result_int.substring(result_int.length()-30);
        result_float = remove_excess_zeroes_on_right(result_float);
        AFloat quotient = int_to_float(result_float);
        return quotient;




    }

}
