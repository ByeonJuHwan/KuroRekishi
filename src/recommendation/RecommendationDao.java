package recommendation;

import java.util.List;

public interface RecommendationDao {

    
    List<Recommendation> search(String loc, String category);
    String searchUrl(String name);
    String searchImageUrl(String name);
    String searchImageUrl2(String name);
}
