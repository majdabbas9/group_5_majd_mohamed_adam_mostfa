package il.cshaifasweng.OCSFMediatorExample.entities;

public class GradeOfStudent {
    private  int gradeNo;
    private int grade;
    public GradeOfStudent(int gradeNo,int grade)
    {
        this.gradeNo=gradeNo;
        this.grade=grade;
    }
    public int getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(int gradeNo) {
        this.gradeNo = gradeNo;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
