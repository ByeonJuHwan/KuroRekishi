package recommendation;

import static recommendation.Recommendation.Entity.*;

public interface DateSql {
    // 검색기능 다중 LIKE
    String SQL_SEARCH  = String.format("select * from %s where %s = ? and %s = ? order by %s", TBL_RECOMM,COL_LOC,COL_CATEGORY,COL_LOC);
    
    // 자세히 보기 버튼 누를시 네이버 지도 연
    String SQL_SEARCH_URL = String.format("select %s from %s where %s=?", COL_URL,TBL_RECOMM,COL_NAME);
    
    // 이미지 주소링크를 통해서 첫번째 이미지를 불러옴
    String SQL_SEARCH_IMAGEURL = String.format("select %s from %s where %s=?", COL_IMAGEURL,TBL_RECOMM,COL_NAME);
    
    // 이미지 주소링크를 통해서 두번째 이미지를 불러
    String SQL_SEARCH_IMAGEURL2 = String.format("select %s from %s where %s=?", COL_IMAGEURL2,TBL_RECOMM,COL_NAME);
}
