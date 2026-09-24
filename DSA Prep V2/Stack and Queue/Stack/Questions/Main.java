import java.util.Stack;
class Main {
    public static boolean isDuplicateBracket(String str){
        
    }

    public static void main(String[] args){
        String str = "(((a+(b))+c+d))";

        boolean isDuplicate = isDuplicateBracket(str);

        if(isDuplicate){
            System.out.println("Brackets are duplicate!!!");
        } else {
            System.out.println("Brackets are not duplicate!!!");
        }
    }
}