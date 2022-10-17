package chat;

import static chat.Chat.Entity.*;

public interface ChatSql {
    // 좋아요를 누를시 상대방에게 내 아이디와 상대방 아이디를 입력.
    String SQL_CHAT_INSERT_ID = String.format("insert into %s(%s,%s) values (?,?)", TBL_CHAT,COL_GIVEID,COL_GAVEDID);
}
