import java.util.ArrayList;
import java.util.List;

public class BalancedParanthesis {

    static List<StringBuilder> allPerm(int N) {
        List<StringBuilder> state = new ArrayList<>();
        List<Integer> openBraces = new ArrayList<>();
        List<Integer> closedBraces = new ArrayList<>();
        state.add(new StringBuilder(""));
        openBraces.add(0);
        closedBraces.add(0);
        for (int i = 0; i < 2 * N; i++) {
            int K = state.size();
            for (int j = 0; j < K; j++) {
                int currentOpenBraces = openBraces.get(j);
                int currentClosedBraces = closedBraces.get(j);
                StringBuilder currentString = state.get(j);
                if (currentOpenBraces < N) {
                    if (currentClosedBraces < currentOpenBraces) {
                        state.add(new StringBuilder(currentString).append('}'));
                        closedBraces.add(currentClosedBraces + 1);
                        openBraces.add(currentOpenBraces);
                    }
                    currentString.append('{');
                    openBraces.set(j, currentOpenBraces + 1);
                } else {
                    state.get(j).append('}');
                    closedBraces.set(j, closedBraces.get(j) + 1);
                }
            }

        }
        return state;
    }

    public static void main(String[] args) {
        List<StringBuilder> ans = allPerm(4);
        for (StringBuilder k : ans) {
            System.out.println(k);
        }
    }
}
