import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class TestClass {


    public static void main(String args[]) {
        List<StringBuilder> sl = new ArrayList<>();
        sl.add(new StringBuilder("abc"));
        sl.add(new StringBuilder("def"));
        String st = "@&";
        IntStream cst = st.chars();
//        sl.stream()
//                .flatMap(x -> st.chars().mapToObj(y -> x.append((char) y)))
//                .forEach(System.out::println);
        System.out.println(letterCombinations("2"));
    }

    public static String[] K = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String d) {
        int n = d.length();
        List<StringBuilder> ans = new ArrayList<>();
        int idx = 0;
        Stream<StringBuilder> st = Stream.<StringBuilder>builder().add(new StringBuilder("")).build();
        for (int i = 0; i < n; i++) {
            if (ans.isEmpty()) ans.add(new StringBuilder(""));
            String pat = K[d.charAt(i) - '0'];
            ans = ans
                    .stream()
                    .flatMap(x -> pat.chars().mapToObj(y -> x.append((char) y)))
                    .collect(Collectors.toList());
            System.out.println(ans);
        }
        return ans.stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

}