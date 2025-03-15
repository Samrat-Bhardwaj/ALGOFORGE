import java.util.*;
class Main {
    public static String compressString(String str){
        int n = str.length();
        String ans = str.charAt(0) + "";

        char prev = str.charAt(0);
        char curr;

        for(int idx=1; idx<n; idx++){
            curr = str.charAt(idx);

            if(curr != prev){
                ans += curr; // O(n)
            }

            prev = curr;
        }

        return ans;
    }

    public static String compressString2(String str){
        int n = str.length();
        String ans = str.charAt(0) + "";

        char prev = str.charAt(0);
        char curr;
        int count = 1;

        for(int idx=1; idx<n; idx++){
            curr = str.charAt(idx);

            if(curr != prev){ // add count of prev character,add new character
                if(count > 1){ // count of prev character
                    ans += count;
                }
                ans += curr;
                count = 1;
            } else {
                count++;
            }

            prev = curr;
        }

        if(count > 1){
            ans += count;
        }

        return ans;
    }

    // leetcode 443 ===============================
    int revereseAndFill(char[] str, int j,int count){
        int reverseNum = 0;
        String toAdd = count+"";
        
        for(int i=0; i<toAdd.length(); i++){
            str[j++] = toAdd.charAt(i);
        }
        
        return j;
    }

    public int compress(char[] str) {
        int n = str.length;
        // String ans = str[0] + "";

        char prev = str[0];
        char curr;
        int count = 1;

        int j = 1;

        for(int idx=1; idx<n; idx++){
            curr = str[idx];

            if(curr != prev){ // add count of prev character,add new character
                if(count > 1){ // count of prev character
                    // ans += count;
                    j = revereseAndFill(str,j,count);
                }
                // ans += curr;
                str[j] = curr;
                j++;
                count = 1;
            } else {
                count++;
            }

            prev = curr;
        }

        if(count > 1){
            j = revereseAndFill(str,j,count);
        }

        return j;
    }

    // toggle casing

    public static String toggleCase(String str){
        int n = str.length();
        StringBuilder sb = new StringBuilder();

        for(int idx=0; idx<n; idx++){
            char ch = str.charAt(idx);

            if(ch>='a' && ch<='z'){ // lowercase character, 'e' -> 'E'
                sb.append((char)(ch + ('A' - 'a'))); // we want to go from upper to lower -> 'A' - 'a'
            } else if(ch>='A' && ch<='Z'){ // uppercase character
                sb.append((char)(ch + ('a' - 'A'))); // we want to go from lower to upper
            } else { // its not a english letter
                sb.append(ch);
            }
        }

        return sb.toString();
    }


    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        String a = scn.nextLine(); // separate by space // hello 5
        // int x = scn.nextInt(); // werty
        // String b = scn.next();

        // System.out.println(a); // "hello Algoforge"
        // System.out.println(x); // "Second"


        // System.out.println(compressString2(a));

        // char c1 = 'a';
        // char c2 = 'A';

        // if(c2 < c1){
        //     System.out.println("c1 is smaller than c2");
        // } 

        // System.out.println('D'-'A');
        System.out.println(toggleCase(a));
    }
}