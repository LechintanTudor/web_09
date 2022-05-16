package ro.ubb.imgur.model;

public class PictureWithStats {
    public String filePath;
    public String authorName;
    public long totalVotes;

    public PictureWithStats(String filePath, String authorName, long totalVotes) {
        this.filePath = filePath;
        this.authorName = authorName;
        this.totalVotes = totalVotes;
    }
}
