package BOJ;

import java.util.*;

public class BJ3040_백설공주와일곱난쟁이 {

    static int arr[] = new int[2];
    static ArrayList<int[]> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();

        int[] dwarf = new int[9];
        int sum = 0;

        for(int i=0; i<9; i++){
            dwarf[i] = sc.nextInt();
            sum += dwarf[i];
        }

        combination(0, 0);
        boolean[] booleans = new boolean[9];

        for(int i=0; i<list.size(); i++){

            int idx1 = list.get(i)[0];
            int idx2 = list.get(i)[1];

            if(sum-dwarf[idx1]-dwarf[idx2] == 100){
                booleans[idx1] = true;
                booleans[idx2] = true;
                break;
            }
        }

        for(int i=0; i<9; i++){
            if(!booleans[i]) System.out.println(dwarf[i]);
        }
    }

    private static void combination(int cnt, int start){
        if(cnt == 2){
            list.add(Arrays.copyOf(arr, arr.length));
            return;
        }
        for(int i=start; i<9; i++){
            arr[cnt] = i;
            combination(cnt+1, i+1);
        }
    }
}
