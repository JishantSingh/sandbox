import java.util.*;

class ProductReview {
    static String modeReview(List<String> reviews, List<String> keywords) {
        Map<String, Integer> keysFreq = new HashMap<>();
        for (String keyword : keywords) {
            keysFreq.put(keyword, 0);
        }
        String modeToken = "noReviews";
        int modeFreq = 0;
        for (String review : reviews) {
            List<String> tokens = Arrays.asList(review.split(" "));
            for (String token : tokens) {
                token = token.toLowerCase();
                if (keysFreq.containsKey(token)) {
                    keysFreq.put(token, keysFreq.get(token) + 1);
                    if (modeFreq <= keysFreq.get(token)) {
                        modeFreq = keysFreq.get(token);
                        modeToken = token;
                    }
                    break;
                }
            }
        }
        return modeToken;
    }

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("best");
        keywords.add("good");
        keywords.add("average");
        List<String> reviews = new ArrayList<>();
        reviews.add("Watch is good");
        reviews.add("watch works best for my requirements");
        reviews.add("Overall i would say watch is average");
        reviews.add("My feedback for this watch is Good");
        reviews.add("No feedback from my side");
        System.out.println(modeReview(reviews,keywords));
    }
}
