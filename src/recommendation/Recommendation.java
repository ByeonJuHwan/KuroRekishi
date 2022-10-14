package recommendation;

public class Recommendation {
    public interface Entity{
        String TBL_RECOMM = "RECOMMENDATION";
        String COL_NAME = "NAME";
        String COL_LOC = "LOC";
        String COL_ENJOY = "ENJOY";
        String COL_CATEGORY = "CATEGORY";
        String COL_URL = "URL";
        String COL_IMAGEURL = "IMAGEURL";
        String COL_IMAGEURL2 = "IMAGEURL2";
    }
    
    
    // feild
    private String name;
    private String loc;
    private String enjoy;
    private String category;
    private String URL;
    private String IMAGEURL;
    private String IMAGEURL2;
    
    public String getURL() {
        return URL;
    }

    // constructor
    public Recommendation() {}

    public Recommendation(String name, String loc, String category) {
        this.name = name;
        this.loc = loc;
        this.category = category;
    }

    public Recommendation(String name, String loc, String enjoy, String category) {
        this.name = name;
        this.loc = loc;
        this.enjoy = enjoy;
        this.category = category;
    }
    
    // getter setter
    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public String getEnjoy() {
        return enjoy;
    }

    public String getCategory() {
        return category;
    }

    public String getIMAGEURL() {
        return IMAGEURL;
    }

    public String getIMAGEURL2() {
        return IMAGEURL2;
    }
    
}
