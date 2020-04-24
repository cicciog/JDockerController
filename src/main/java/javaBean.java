
/**
 *
 * @author francesco
 */
class javaBean {

    private static final long serialVersionUID = 1L;

    public String Name, RollNo, Department, Result, Pointer;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getPointer() {
        return Pointer;
    }

    public void setPointer(String pointer) {
        Pointer = pointer;
    }

    @Override
    public String toString() {
        return "javaBean{"
                + "Name=" + Name
                + ", RollNo=" + RollNo
                + ", Department=" + Department
                + ", Result=" + Result
                + ", Pointer=" + Pointer + '}';
    }

}
