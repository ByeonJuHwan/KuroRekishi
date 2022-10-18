package chat;

import java.util.List;

public interface ChatDao {
    // 상대방에게 좋아요를 누를시 db에 저장한다. 현재시간과함께
    int insertId (String id, String id2);
    
    // 나에게 좋아요를 준 사람들의 아이디리스트를 리턴해준다
    List<String> idList (String id);
    
    // 중복 좋아요를 방지하기위함
    boolean checkDuplicateThumb(String id);
}
