package Model;

public class Batch {
    public int btcID;
    public String btcName;

    public Batch(int btcID, String btcName) {
        this.btcID = btcID;
        this.btcName = btcName;
    }

    public Batch(String btcName) {
        this.btcName = btcName;
    }
}
