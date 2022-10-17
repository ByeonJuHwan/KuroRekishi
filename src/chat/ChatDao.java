package chat;

public interface ChatDao {
    // 상대방에게 좋아요를 누를시 db에 저장한다. 현재시간과함께
    int insertId (String id, String id2);
}
