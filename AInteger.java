import java.lang.Math;

public class AInteger{
    // Public String that stores the integer of arbitrary length
    public String s;
    public char sign;
    // Default constructor that initializes the value of string with 0 initially.
    public AInteger(){
        this.s = "0";
        this.sign = '+';
        
    }
    // Constructor that initializes the instance of a number as a string with passed argument
    public AInteger(String s){
            
        if (s == null || s.isEmpty()){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(s.charAt(0)=='+' || s.charAt(0)== '-'){
            this.sign = s.charAt(0);
            s = s.substring(1);
        }else if(Character.isDigit(s.charAt(0))){
            this.sign = '+';
        }
        
    
    for (char c : s.toCharArray()) {
        if (!Character.isDigit(c)){
            throw new IllegalArgumentException("Invalid Input!!");
        }
    }
       this.s = s; 
    }
    // Copy constructor that creates an instance of AInteger
    public AInteger(AInteger other_copy){
        this.s = other_copy.s;
        this.sign = other_copy.sign;
    }
    // Static function that returns an instance of AInteger class
    public static AInteger parse(String s){
        return new AInteger(s);
    }
    public static AInteger add(AInteger s1, AInteger s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
           // return subtract(s1 , s2_mod);
        }else if(s1.sign=='-' && s2.sign=='+'){
            AInteger s1_mod = new AInteger(s1.s);
            //return subtract(s2,s1_mod);
        }else if(s1.sign=='-' && s2.sign=='-'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = AInteger.add(s1_mod,s2_mod);
            
            return new AInteger("-"+result.s);
        }
    String a = s1.s;
    String b = s2.s;
    int len_a = a.length();
    int len_b = b.length();

    if(len_a != len_b){
        int diff = Math.abs(len_a - len_b);
        String added_zeroes = "";
        for(int i=0;i<diff;i++){
            added_zeroes += "0";
        
        }
        if(len_a < len_b){
            a = added_zeroes + a;
        }else{
            b = added_zeroes + b;
        }
    }
    int len = a.length();
    String result = "";
    
    int carry=0;
    for(int i=len-1;i>=0;i--){
        int digit1 = (int)(a.charAt(i))-48;
        int digit2 = (int)(b.charAt(i))-48;
        int digit = digit1+digit2+carry;
        if(digit > 9){
            carry = 1;
            char digit_char = (char)(digit-10+48);
            result = digit_char + result;
        }else{
            carry = 0;
            char digit_char = (char)(digit+48);
            result = digit_char + result;
        }
    }
    if(carry == 1){
        result = "1" + result;
    }
    int remove_prefixing_zeroes=0;
    for(;remove_prefixing_zeroes<result.length();remove_prefixing_zeroes++){
        if(result.charAt(remove_prefixing_zeroes) != '0'){
            break;
        }
    }
    result = result.substring(remove_prefixing_zeroes);
    if(result.equals("")){
        result="0";
    }
    AInteger added_num = new AInteger(result);
    return added_num;

    
    }

    public static AInteger subtract(AInteger s1,AInteger s2){
        
    }
    public static void main(String[] args){
        AInteger num1 = new AInteger("-00788");
        AInteger num2 = new AInteger("-0112");
        AInteger num = AInteger.add(num1,num2);
        String number = num.s;
        System.out.println(num.sign+number);

    }


}