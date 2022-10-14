package recommendation;

import static recommendation.Recommendation.Entity.*;

public interface DateSql {
    // 검색기능 다중 LIKE
    String SQL_SEARCH  = String.format("select * from %s where %s = ? and %s = ? order by %s", TBL_RECOMM,COL_LOC,COL_CATEGORY,COL_LOC);
    String SQL_SEARCH_URL = String.format("select %s from %s where %s=?", COL_URL,TBL_RECOMM,COL_NAME);
}
