package Others;
/**
 * 985. 查询后的偶数和
 * **/
public class SumEvenAfterQueries_985 {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int answer[] = new int[queries.length];
        int sum = 0;
        boolean isEven[] = new boolean[A.length];
        for (int i = 0; i < A.length; ++i){
            if (A[i] % 2 == 0){
                isEven[i] = true;
                sum += A[i];
            }
        }
        for (int i = 0; i < queries.length; ++i){
            int val = queries[i][0];
            int index = queries[i][1];
            if ((A[index] + val) % 2 == 0){
                if (isEven[index])
                    sum += val;      
                else
                    sum += (A[index] + val);
                isEven[index] = true;
            }
            else{
                if (isEven[index])
                    sum -= A[index];
                isEven[index] = false;
            }
            answer[i] = sum;
            A[index] += val;
        }
        return answer;
    }
}
