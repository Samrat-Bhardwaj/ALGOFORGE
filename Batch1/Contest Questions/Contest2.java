import java.util.*;

public class Solution {
    public static int findSecondMax(int[] arr){
        int max = Integer.MIN_VALUE;
        int smax = Integer.MIN_VALUE;

        for(int i=0; i<arr.length; i++){
            if(max < arr[i]){
                smax = max;
                max = arr[i];
            } else if(smax < arr[i]){
                smax = arr[i];
            }
        }

        return smax;
    }

    public static int numberOfConsecutive(int[] arr){
        int count = 1;
        int maxConsecutive = 0;

        for(int i=1; i<arr.length; i++){
            if(arr[i] == arr[i-1]){
                count++;
            } else {
                count = 1;
            }

            maxConsecutive = Math.max(maxConsecutive, count);
        }

        return maxConsecutive;
    }

    public static void reverseArray(int[] arr, int si, int ei){
        while(si < ei){
            int temp = arr[si];
            arr[si] = arr[ei];
            arr[ei] = temp;

            si++;
            ei--;
        }
    }

    public static void rotateArray(int[] arr, int k){
        int n = arr.length;

        k = k % n;

        if(k < 0){
            k = k + n;
        }

        reverseArray(arr,0,n-1);
        reverseArray(arr,0,k-1);
        reverseArray(arr,k,n-1);
    }

    public static void fillShellValues(ArrayList<Integer> shellValues, int[][] arr, int s, int n, int m){
        int sr = s-1;
        int er = n-s;
        int sc = s-1;
        int ec = m-s;
        int row = 0;
        int col = 0;

        // top wall
        row=sr;
        for(col=sc; col<=ec; col++){
            shellValues.add(arr[row][col]);
            // shellValues.add(matrix.get(row).get(col));
        }
        sr++;

        // right wall
        col = ec;
        for(row=sr; row<=er; row){
            shellValues.add(arr[row][col]);
        }
        ec--;

        // bottom wall
        row = er;
        for(col = ec; col>=sc; col--){
            shellValues.add(arr[row][col]);
        }
        er--;

        // left wall 
        col = sc;
        for(row = er; row>=sr; row--){
            shellValues.add(arr[row][col]);
        }
        sc++;        
    }

    public static void fillMatrixValues(int[] odArray, int[][] arr, int s, int n, int m){
        int sr = s-1;
        int er = n-s;
        int sc = s-1;
        int ec = m-s;
        int row = 0;
        int col = 0;
        int idx = 0;

        // top wall
        row=sr;
        for(col=sc; col<=ec; col++){
            arr[row][col] = odArray[idx];
            idx++;
        }
        sr++;

        // right wall
        col = ec;
        for(row=sr; row<=er; row++){
            arr[row][col] = odArray[idx];
            idx++;
        }
        ec--;

        // bottom wall
        row = er;
        for(col = ec; col>=sc; col--){
            arr[row][col] = odArray[idx];
            // matrix.get(row).set(col, odArray[idx]);
            idx++;
        }
        er--;

        // left wall 
        col = sc;
        for(row = er; row>=sr; row--){
            arr[row][col] = odArray[idx];
            idx++;
        }
        sc++;
    }

    public static void shellRotate(int n, int m, int s, int k, List<List<Integer>> matrix) {
        int[][] arr = new int[n][m];

        // fill2DMatrix
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                arr[i][j] = matrix.get(i).get(j);
            }
        }

        ArrayList<Integer> shellValues = new ArrayList<>();
        fillShellValues(shellValues, arr, s, n, m);

        int[] odArray = new int[shellValues.size()];
        for(int i=0; i<odArray.length; i++){
            odArray[i] = shellValues.get(i);
        }
        rotateArray(odArray, k);

        fillMatrixValues(odArray,arr,s,n,m);

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                matrix.get(i).set(j, arr[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = scn.nextInt();
        }

        System.out.println(numberOfConsecutive(arr));
    }
}