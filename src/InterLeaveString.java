// { Driver Code Starts
//initial code
import java.util.*;
import java.lang.*;
class InterLeaveString
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t>0)
        {
            String a =sc.next();
            String b = sc.next();
            String c = sc.next();
            GfG g=new GfG();

            System.out.println(g.isInterLeave(a, b, c) ?1:0);
            t--;
        }
    }
}

// } Driver Code Ends


/*you are required to complete this method */
class GfG{
    public boolean isInterLeave(String a,String b,String c)
    {
        int al = a.length(), bl = b.length(), cl = c.length();

        if(al+bl!=cl)return false;
        boolean [][] dp = new boolean [al+1][bl+1];
        dp[0][0] = true;
        for(int i = 1;i<= al;i++){
            dp[i][0] = ((c.charAt(i-1) == a.charAt(i-1)) && dp[i-1][0]);
        }
        for(int i = 1;i<= bl;i++){
            dp[0][i] = ((c.charAt(i-1) == b.charAt(i-1)) && dp[0][i-1]);
        }
        for(int i = 1;i<al+1;i++){
            for(int j=1; j< bl+1; j++){
                dp[i][j] = ((c.charAt(i+j-1) == a.charAt(i-1)) && dp[i-1][j]) ||
                        (((c.charAt(i+j-1) == b.charAt(j-1)) && dp[i][j-1]));
            }
        }
        return dp[al][bl];
    }
}