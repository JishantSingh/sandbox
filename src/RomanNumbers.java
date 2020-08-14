import java.util.HashMap;

public class RomanNumbers {
    static class X{
        final static int y=10;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int [][]z= new int[2][2];
        int []y = {1,2,3};
        z[0]=y;
        for(int i=0;i<z[0].length;i++) System.out.println(z[0][i]+" ");
    }
    public static void main(String [] args, int a){
        System.out.println("hey there");

    }
}

class C{
    int x, y;
    public C(int a){
        this.x = a;
    }
}

class Solution {
    static class Pair {
        int x, y;

        @Override
        public boolean equals(Object b) {
            if (b == null || getClass() != b.getClass()) return false;
            Pair that = (Pair) b;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public int hashCode() {
            return (this.x + 1) * (this.y + 1);
        }

        public Pair(int a, int b) {
            x = a;
            y = b;
        }

    }

    public static HashMap<Pair, String> mp = new HashMap<>();

    static {
        mp.put(new Pair(1, 0), "I");
        mp.put(new Pair(5, 0), "V");
        mp.put(new Pair(1, 1), "X");
        mp.put(new Pair(5, 1), "L");
        mp.put(new Pair(1, 2), "C");
        mp.put(new Pair(5, 2), "D");
        mp.put(new Pair(1, 3), "M");
    }

    public String getRoman(int x, int y) {
        if (x == 0) return "";
        if (x != 4 && x != 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < x; i++) {
                sb.append(mp.get(new Pair(1, y)));
            }
            return sb.toString();
        } else if (x == 4) {
            return mp.get(new Pair(1, y)) + mp.get(new Pair(5, y));
        } else {
            return mp.get(new Pair(1, y)) + mp.get(new Pair(1, y + 1));
        }
    }

    public String intToRoman(int num) {
        int pv = 0;
        String sb = "";
        while (num != 0) {
            int dig = num % 10;
            num /= 10;
            sb = getRoman(dig, pv++) + sb;
        }
        return sb;
    }
}