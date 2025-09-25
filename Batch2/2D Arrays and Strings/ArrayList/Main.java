import java.util.*;
class Main {
    public static String toggleCase(String str){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if(ch >= 'A' && ch <= 'Z'){ // uppercase char
                sb.append((char)('a' + (ch - 'A')));
            } else if(ch >= 'a' && ch <='z'){ // lowercase char
                sb.append((char)('A' + (ch - 'a')));
            } else { // not english char
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args){
        // ArrayList<Integer> al = new ArrayList<>();

        // al.add(55);
        // al.add(1);
        // al.add(2);
        // al.add(3);
        // // al.add(2,98);
        

        // al.set(3,100);
        // System.out.println(al);

        // al.remove(3);
        // System.out.println(al.get(2));

        // StringBuilder sb = new StringBuilder();
        // sb.append("abcd");
        // sb.setCharAt(1,'Z');
        
        // System.out.println(sb);

        String str = "AbcdEFGHiJk2";
        System.out.println(toggleCase(str));
    }
}