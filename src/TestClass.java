import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TestClass {


    public static void main(String args[]) throws IOException {
        URL url = new URL("http://stagingbcore.bounce.bike/zone_service/validate_parking");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("AppToken", "ykD0aGLjXRecH521aqJk");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Postman-Token", "e5d2810f-5f9f-4bf5-9551-5191afbd573d");
        conn.setRequestProperty("authkey", "ykD0aGLjXRecH521aqJk");
        conn.setRequestProperty("cache-control", "cache-control");
        conn.setRequestProperty("token", "f3523c66-8782-11e8-8973-c329568ede24");
        conn.setRequestMethod("POST");
        String body = "{\"bike_id\": 0,\"booking_id\":1514038,\"user_lat\":12.90426,\"user_lon\":77.60193,\"user_loc_accuracy\":8,\"user_loc_time\":1585055787443,\"bike_lat\":12.90426010,\"bike_lon\":77.60193005,\"bike_hdop\":11,\"bike_sat_count\":10,\"bike_loc_time\":1585055787443,\"geo_id\":1,\"is_bluetooth\":true,\"process_step\":\"start\"}";
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()){
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
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
            pat.chars().forEach(System.out::println);
//            System.out.println(pat);
//            System.out.println(ans);
//            int p = ans.stream().map(x -> pat.chars().mapToObj(y -> {
//                System.out.println(x);
//                return x.append((char) y);
//            })).collect(Collectors.toList());
//                    .forEach(System.out::println);
        }
        return ans.stream().map(StringBuilder::toString).collect(Collectors.toList());
    }
}

class T {
    private List<Integer> ll;

    T() {
        this.ll = new ArrayList<>();
        this.ll.add(1);
        this.ll.add(2);
    }

    public List<Integer> get() {
        return this.ll;
    }

    public void set(List<Integer> ly) {
        this.ll = ly;
    }

}
