package ro.ubb.imgur.model;

public class Picture {
    public long id;
    public String filePath;
    public long accountId;
    public long totalVotes;

    public Picture(long id, String filePath, long accountId, long totalVotes) {
        this.id = id;
        this.filePath = filePath;
        this.accountId = accountId;
        this.totalVotes = totalVotes;
    }
}
