import DBO.BatchDBO;
import DBO.LecturerDBO;
import DBO.StudentDBO;
import Model.Batch;
import Model.Lecturer;
import Model.Student;
import middleware.Validation;

import java.sql.*;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        while (true){
            Menu();
        }
    }

    private static void Menu(){
        System.out.println("1. Student management.");
        System.out.println("2. Lecturer management.");
        System.out.println("3. Batch management.");
        Scanner scan = new Scanner(System.in);

        try{
            System.out.println("please choose to continue");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    StudentManagement();
                    break;
                case 2:
                    lecturerManagement();
                    break;
                case 3:
                    batchManagement();
                    break;
                default:
                        System.out.println("Please choose correct option4!");
                        scan.nextLine();
                        scan.nextLine();
                        Menu();

            }
        }catch (Exception ex){
            System.out.println("Please choose correct option3!");
            scan.nextLine();
            scan.nextLine();
            Menu();
        }

    }

    static void StudentManagement(){
        System.out.println("----------------------------");
        System.out.println("1. Add new student.");
        System.out.println("2. View all student.");
        System.out.println("3. Search student.");
        System.out.println("4. Delete student.");
        System.out.println("5. Update student.");
        System.out.println("6. Back to menu");
        Scanner scan = new Scanner(System.in);

        try{
            StudentDBO stdDB = new StudentDBO();
            System.out.println("please choose to continue");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    AddStudent();
                    break;
                case 2:

                    stdDB.selectAllStudent();
                    break;
                case 3:
                    SearchStudentByName();
                    break;
                case 4:
                    System.out.println("please enter student's name");

                    scan.nextLine();
                    String stdName = scan.nextLine();
                    stdDB.searchStudentByName(stdName);
                    stdDB.DeleteStudentByName();
                    break;
                case 5:

                    updateStudentByName();
                    break;
                case 6:
                    Menu();
                    break;
                default:
                    System.out.println("Please choose correct option2!");
                    scan.nextLine();
                    scan.nextLine();
                    StudentManagement();

            }
        }catch (Exception ex){
            System.out.println("-error-");
            System.out.println(ex.getMessage());
            scan.nextLine();
            scan.nextLine();
            StudentManagement();
        }
    }
    private static void AddStudent() throws SQLException, ClassNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ready to add Student........");
        String stdId, stdName, stdEmail, stdPhone, stdAddress;
        String stdDob;
        int stdBatch;
        Validation valid = new Validation();

        System.out.println("Input Student ID(GC/GT***)");
        stdId = scn.nextLine();
        while (valid.isValidStudentId(stdId)==false){
            System.out.println("Please re-enter student ID(GC/GT***)!!");
            stdId = scn.nextLine();
        }
        System.out.println("Input Student Name...");
        stdName = scn.nextLine();
        while (valid.isValidName(stdName) ==false ){
            System.out.println("Please re-enter student name!!");
            stdName = scn.nextLine();
        }
        System.out.println("Input Student Date of Birth...");
        stdDob = scn.nextLine();
        while(valid.isValidDate(stdDob)==false){
            System.out.println("Please re-enter student date of birth(yyyy/MM/dd)!!!");
            stdDob = scn.nextLine();
        }
        System.out.println("Input Student Email...");
        stdEmail = scn.nextLine();
        while(valid.isValidEmailAddress(stdEmail)==false){
            System.out.println("please re-enter student email(abc@domain.com)!!");
            stdEmail = scn.nextLine();
        }
        System.out.println("Input Student Phone...");
        stdPhone = scn.nextLine();
        while (valid.isValidPhoneNumber(stdPhone) ==false) {
            System.out.println("please re-enter student phone number(10 digits)!!");
            stdPhone = scn.nextLine();
        }
        System.out.println("Input Student Address...");
        stdAddress = scn.nextLine();
        System.out.println("Input Student Batch...");
        stdBatch = scn.nextInt();
        Student std = new Student(stdId, stdName,stdDob,stdEmail,stdPhone,stdAddress,stdBatch);
        StudentDBO stdDB = new StudentDBO();
        stdDB.insertStudent(std);
        System.out.println("Insert Success!!!");
    }
    private static void SearchStudentByName() throws SQLException, ClassNotFoundException {
        String stdName;
        Scanner scn = new Scanner(System.in);
        System.out.println("Ready to search Student.....");
        System.out.println("Input Student name:  " );
        stdName = scn.nextLine();
        StudentDBO stdDB = new StudentDBO();
        stdDB.searchStudentByName(stdName);
    }
    private static void updateStudentByName() throws SQLException, ClassNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ready to update Student.....");
        System.out.println("Input Student name:  " );
        String stdName = scn.nextLine();
        StudentDBO stdDBupdate = new StudentDBO();
        Validation valid = new Validation();
        stdDBupdate.searchStudentByName(stdName);
        String stdId,  stdEmail, stdPhone, stdAddress;
        String stdDob;
        int stdBatch;
        System.out.println("Input Student ID to update!!");
        stdId = scn.nextLine();
        System.out.println("Input Student Name!!!");
        stdName = scn.nextLine();

        while (valid.isValidName(stdName) ==false ){
            System.out.println("Please re-enter student name!!");
            stdName = scn.nextLine();
        }
        System.out.println("Input Student Email!!!");
        stdEmail = scn.nextLine();
        while(valid.isValidEmailAddress(stdEmail)==false){
            System.out.println("please re-enter student email(abc@domain.com)!!");
            stdEmail = scn.nextLine();
        }
        System.out.println("Input Student Phone !!!");
        stdPhone = scn.nextLine();
        while (valid.isValidPhoneNumber(stdPhone) ==false) {
            System.out.println("please re-enter student phone number(10 digits)!!");
            stdPhone = scn.nextLine();
        }
        System.out.println("Input Student Address!!");
        stdAddress = scn.nextLine();
        System.out.println("Input Student Date of Birth!!");
        stdDob = scn.nextLine();
        while(valid.isValidDate(stdDob)==false){
            System.out.println("Please re-enter student date of birth(yyyy/MM/dd)!!!");
            stdDob = scn.nextLine();
        }
        System.out.println("Input Student Batch!!");
        stdBatch = scn.nextInt();
        Student std = new Student(stdId, stdName,stdDob,stdEmail,stdPhone,stdAddress,stdBatch);
        stdDBupdate.updateStudent(std);
    }
     static void lecturerManagement(){
        System.out.println("----------------------------");
        System.out.println("1. Add new lecturer.");
        System.out.println("2. View all lecturer.");
        System.out.println("3. Search for lecturers.");
        System.out.println("4. Delete lecturer.");
        System.out.println("5. Update lecturer.");
        System.out.println("6.Back to menu.");
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Please choose to continue...");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    AddLecturer();
                    break;
                case 2:
                    LecturerDBO lecDBO = new LecturerDBO();
                    lecDBO.viewAllLecturer();
                    break;
                case 3:
                    searchLecturerByname();
                    break;
                case 4:
                    System.out.println("please enter lecturer's name");
                    LecturerDBO lecturerDBo = new LecturerDBO();
                    scanner.nextLine();
                    String lecName = scanner.nextLine();
                    lecturerDBo.searchLecturerByName(lecName);
                    lecturerDBo.deleteLecturerByName(); // delete lecturer function
                    break;
                case 5:
                    updateLecturerByName(); // update function
                    break;
                case 6:
                    Menu();
                    break;
                default:
                    System.out.println("Please choose correct option2!");
                    scanner.nextLine();
                    scanner.nextLine();
                    lecturerManagement();

            }
        }
        catch (Exception ex){
            System.out.println("---error---");
            if (ex.getMessage() == null){
                System.out.println("Lecturer or student not found");
            }else{
                System.out.println(ex.getMessage());
            }
            scanner.nextLine();
            scanner.nextLine();
            lecturerManagement();
        }
    }
    private static void AddLecturer() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Validation valid = new Validation();
        System.out.println("Ready to add lecturer.........");
        String lecID, lecName, lecDob, lecEmail, lecPhone, lecAddress;
        int lecDeptID;
        System.out.println("Please enter lecturer's ID...");
        lecID = scanner.nextLine();
        while (valid.isValidLecturerID(lecID)){
            System.out.println("Please re-enter lecturer ID!!");
            lecID = scanner.nextLine();
        }
        System.out.println("Please enter lecturer's name...");
        lecName = scanner.nextLine();
        while (valid.isValidName(lecName) == false){
            System.out.println("Please re-enter lecturer name!!");
            lecName = scanner.nextLine();
        }
        System.out.println();
        System.out.println("Please enter lecturer's date of birth...");
        lecDob = scanner.nextLine();
        while (valid.isValidDate(lecDob) == false){
            System.out.println("Please re-enter date of birth (yyyy/MM/dd)!!");
            lecDob = scanner.nextLine();
        }
        System.out.println("Please enter lecturer's Email...");
        lecEmail = scanner.nextLine();
        while (valid.isValidEmailAddress(lecEmail)==false){
            System.out.println("Please enter correct email format!!");
            lecEmail = scanner.nextLine();
        }
        System.out.println();System.out.println("Please enter lecturer's phone number....");
        lecPhone = scanner.nextLine();
        while(valid.isValidPhoneNumber(lecPhone)==false){
            System.out.println("please re-enter phone number!!");
            lecPhone = scanner.nextLine();
        }
        System.out.println("Please enter lecturer's address!!");
        lecAddress = scanner.nextLine();
        System.out.println("Please enter lecturer's department ID!!!");
        lecDeptID = scanner.nextInt();
        Lecturer lec = new Lecturer(lecID, lecName, lecDob, lecEmail,lecPhone,lecAddress,lecDeptID);
        LecturerDBO lecDBO = new LecturerDBO();
        lecDBO.insertLecturer(lec);
        System.out.println("Add success!!");
    }
    private static  void searchLecturerByname() throws SQLException, ClassNotFoundException {
        String lecName;
        Scanner scn = new Scanner(System.in);
        System.out.println("Ready to search lecturer.....");
        System.out.println("Input lecturer name:  " );
        lecName = scn.nextLine();
        LecturerDBO lecDbo = new LecturerDBO();
        lecDbo.searchLecturerByName(lecName);
    }
    private static void updateLecturerByName() throws SQLException, ClassNotFoundException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ready to update lecturer.....");
        System.out.println("Input lecturer name:  " );
        String lecName = scn.nextLine();
        LecturerDBO lecDB = new LecturerDBO();
        Validation valid = new Validation();
        lecDB.searchLecturerByName(lecName);
        String lecID, lecDob, lecEmail, lecPhone, lecAddress;
        int lecDeptID;
        System.out.println("Input lecturer ID to update!!");
        lecID = scn.nextLine();
        System.out.println("Input lecturer Name!!!");
        lecName = scn.nextLine();
        while (valid.isValidName(lecName)){
            System.out.println("Please re-enter lecturer name!!");
            lecName = scn.nextLine();
        }
        System.out.println("Input lecturer Date of Birth!!");
        lecDob = scn.nextLine();
        while (valid.isValidDate(lecDob) == false){
            System.out.println("Please re-enter date of birth (yyyy/MM/dd)!!");
            lecDob = scn.nextLine();
        }
        System.out.println("Input lecturer Email!!!");
        lecEmail = scn.nextLine();
        while (valid.isValidEmailAddress(lecEmail)==false){
            System.out.println("-----Please enter correct email format!!!-----");
            lecEmail = scn.nextLine();
        }
        System.out.println("Input lecturer Phone !!!");
        lecPhone = scn.nextLine();
        while(valid.isValidPhoneNumber(lecPhone)==false){
            System.out.println("please re-enter phone number!!");
            lecPhone = scn.nextLine();
        }
        System.out.println("Input lecturer Address!!");
        lecAddress = scn.nextLine();

        System.out.println("Input lecturer department ID!!");
        lecDeptID = scn.nextInt();
        Lecturer lec = new Lecturer(lecID, lecName, lecDob, lecEmail, lecPhone, lecAddress, lecDeptID);
        lecDB.updateLecturer(lec);
    }
     static void  batchManagement(){

        System.out.println("1. Add new batch.");
        System.out.println("2. Batch list.");
        System.out.println("3. Edit batch.");
        System.out.println("4. Delete batch.");
        System.out.println("5. Back to menu.");
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.println("Please choose to continue....");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    addBatch();
                    break;
                case 2:
                    BatchDBO btcDBO = new BatchDBO();
                    btcDBO.viewAllBatch();
                    break;
                case 3:
                    updateBatch();
                    break;
                case 4:
                    BatchDBO batchDBO = new BatchDBO();
                    batchDBO.deleteBatchByName();
                    break;
                case 5:
                    Menu();
                    break;
                default:
                    System.out.println("Please choose correct option!");
                    scanner.nextLine();
                    scanner.nextLine();
                    batchManagement();
            }
        } catch (Exception ex){
            System.out.println("Error!! Please press enter to continue....");
            System.out.println(ex.getMessage());
            scanner.nextLine();
            scanner.nextLine();
            batchManagement();
        }
    }
    private static void addBatch() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready to add new batch....");
        String btcName;
        System.out.println("please enter batch name");
        btcName = scanner.nextLine();
        Batch btc = new Batch (btcName);
        BatchDBO btcDBO = new BatchDBO();
        btcDBO.insertBatch(btc);
        System.out.println("Add new batch success!!");
    }

    private static void updateBatch() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready to edit batch.......");
        System.out.println("Enter batch name to edit!!");
        String btcName = scanner.nextLine();
        BatchDBO batchDBO = new BatchDBO();
        batchDBO.searchBatchByName(btcName);
        System.out.println("Please enter new batch name!!");
        btcName = scanner.nextLine();
        Batch btc = new Batch(btcName);
        batchDBO.editBatch(btc);
    }
}
