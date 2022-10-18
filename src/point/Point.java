package point;

public class Point {
    public interface Entity{
        String TBL_POINT = "POINT";
        String COL_POINT = "POINT";
        String COL_GIVESTARID = "GIVESTARID";
        String COL_GAVEDSTARID = "GAVEDSTARID";
    }
    
    private int point;
    private String giveStarId;
    private String gavedStarId;
    
    public Point() {}

    public Point(int point, String giveStarId, String gavedStarId) {
        super();
        this.point = point;
        this.giveStarId = giveStarId;
        this.gavedStarId = gavedStarId;
    }

    public int getPoint() {
        return point;
    }

    public String getGiveStarId() {
        return giveStarId;
    }

    public String getGavedStarId() {
        return gavedStarId;
    }
    
    
    
    
}
