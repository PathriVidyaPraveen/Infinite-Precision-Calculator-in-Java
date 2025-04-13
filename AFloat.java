public class AFloat{
    public String s;
    public char sign;
    public AFloat(){
        this.s = "0.0";
        this.sign = '+';
    }
    public AFloat(String s){
     
        if (s == null || s.isEmpty()){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(s.charAt(0)=='+' || s.charAt(0)== '-'){
            this.sign = s.charAt(0);
            s = s.substring(1);
        }else if(Character.isDigit(s.charAt(0))){
            this.sign = '+';
        }
        
    int decimal_count = 0;
    for (char c : s.toCharArray()) {
        if (!(Character.isDigit(c)) && decimal_count >= 1){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(c=='.'){
            decimal_count ++;
        }
    }
       this.s = s; 
    }
    public AFloat(AFloat other_copy){
        this.s = other_copy.s;
        this.sign = other_copy.sign;
    }
    public static AFloat parse(String s){
        return new AFloat(s);
    }
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
}