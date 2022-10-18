package chat;

import java.sql.Date;

public class Chat {
    
    public interface Entity{
        String TBL_CHAT = "CHAT";
        String COL_GIVEID = "GIVEID";
        String COL_GAVEDID = "GAVEDID";
        String COL_SENDTIME = "SENDTIME";
        String COL_TIME = "SENDTIME";
    }
    
    private String giveId;
    private String gavedId;
    private Date sendTime;
    
    public Chat() {}

    public Chat(String giveId, String gavedId, Date sendTime) {
        this.giveId = giveId;
        this.gavedId = gavedId;
        this.sendTime = sendTime;
    }

    public String getGiveId() {
        return giveId;
    }

    public String getGavedId() {
        return gavedId;
    }

    public Date getSendTime() {
        return sendTime;
    }
  
    
    
    
}
