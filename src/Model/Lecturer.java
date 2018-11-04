package Model;


public class Lecturer {
    public String lecID;
    public String lecName;
    public String lecDoB;
    public String lecEmail;
    public String lecPhone;
    public String lecAddress;
    public int lecDeptID;


    public Lecturer(String lecID, String lecName, String lecDoB, String lecEmail, String lecPhone, String lecAddress, int lecDept) {
        this.lecID = lecID;
        this.lecName = lecName;
        this.lecDoB = lecDoB;
        this.lecEmail = lecEmail;
        this.lecPhone = lecPhone;
        this.lecAddress = lecAddress;
        this.lecDeptID = lecDept;
    }
}
