package recommendation;

import java.util.List;

public interface RecommendationDao {

    
    List<Recommendation> search(String loc, String category);
}
