package BOJ;

import java.io.*;
import java.util.*;

public class BJ11286_절댓값힙 {
    public static void main(String[] args) throws IOException {

        PriorityQueue<Integer> pqPositive = new PriorityQueue<>();
        PriorityQueue<Integer> pqNegative = new PriorityQueue<>(Collections.reverseOrder());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            
            if(num < 0) pqNegative.add(num);
            else if(num > 0) pqPositive.add(num);
            
            else if (num == 0){
                int po = 0; int ne = 0;
                
                if(pqPositive.isEmpty() && pqNegative.isEmpty())
                    System.out.println(0);
                
                else if(pqPositive.isEmpty() && !pqNegative.isEmpty()) 
                	System.out.println(pqNegative.poll());
                
                else if(!pqPositive.isEmpty() && pqNegative.isEmpty())
                    System.out.println(pqPositive.poll());

                else{
                    po = pqPositive.peek();
                    ne = Math.abs(pqNegative.peek());
                    if(po < ne) System.out.println(pqPositive.poll());
                    else if(po >= ne) System.out.println(pqNegative.poll());
                }
            }
        }
    }
}
