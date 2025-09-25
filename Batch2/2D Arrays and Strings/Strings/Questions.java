class Questions {
    public static String compressString(String str){
        String ans = "";
        ans += str.charAt(0);

        char prev = str.charAt(0);

        for(int i=1; i<str.length(); i++){
            char curr = str.charAt(i);

            if(curr != prev){
                ans += curr;
            }

            prev = curr;
        }

        return ans;
    }

    public static String compressString(String str){
        String ans = "";
        ans += str.charAt(0);

        char prev = str.charAt(0);
        int count = 1;

        for(int i=1; i<str.length(); i++){
            char curr = str.charAt(i);

            if(prev == curr){
                count++;
            } else {
                if(count > 1){
                    ans += count;
                }
                ans += curr;
                
                count = 1;
            }

            prev = curr;
        }

        if(count > 1){
            ans += count;
        }

        return ans;
    }

    public static void main(String[] args){
        String str = "aaabbbcdeeffffffaaddzz";

        String compressed = compressString(str);
        System.out.println(compressed);
    }
}