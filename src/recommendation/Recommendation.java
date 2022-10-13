package recommendation;

public class Recommendation {
    public interface Entity{
        String TBL_RECOMM = "RECOMMENDATION";
        String COL_NAME = "NAME";
        String COL_LOC = "LOC";
        String COL_ENJOY = "ENJOY";
        String COL_CATEGORY = "CATEGORY";
    }
    
    
    // feild
    private String name;
    private String loc;
    private String enjoy;
    private String category;
    
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
    
}
