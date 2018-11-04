package Model;



public class Student {
    public String stdId;
    public String stdName;
    public String stdDoB;
    public String stdEmail;
    public String stdPhone;
    public String stdAddress;
    public int stdBatch;


    public Student(String stdId, String stdName, String stdDoB, String stdEmail, String stdPhone, String stdAddress, int stdBatch) {
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdDoB = stdDoB;
        this.stdEmail = stdEmail;
        this.stdPhone = stdPhone;
        this.stdAddress = stdAddress;
        this.stdBatch = stdBatch;
    }

}
