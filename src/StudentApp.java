/**
 * StudentApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 *
 * Prompts the user for the maximum number of points possible for each category
 * of assignment in CIS 200. Then, prompts for up to 20 students and their
 * scores in each category. When all students are entered, displays each
 * student's (weighted average) total percentage and letter grade.
 */

public class StudentApp {
  public static void main(String[] args) {
    final int LAB = 0, PROJECT = 1, ZYBOOK = 2, EXAM = 3, FINAL_EXAM = 4;
    Student[] students = new Student[20];
    View view = new View();
    // View_GUI view = new View_GUI();
    String prefix = "Please enter the total number of points possible for ";
    int index;

    double[] maxPoints = new double[] {
      view.promptNum(String.format("%sLabs", prefix)),
      view.promptNum(String.format("%sProjects", prefix)),
      view.promptNum(String.format("%szyBook", prefix)),
      view.promptNum(String.format("%sExams", prefix)),
      view.promptNum(String.format("%sFinal Exam", prefix)),
    };
    prefix = "Enter the student's ";
    view.section();

    for (index = 0; index < students.length; index++) {
      Student student = new Student();

      // get identifying info
      student.setFirstName(view.prompt(String.format("%sfirst name", prefix)));
      student.setLastName(view.prompt(String.format("%slast name", prefix)));
      student.setWid(view.prompt(String.format("%sWID", prefix)));
      view.section();

      // get scores
      student.setTotalLabScore(view.promptNum(
        String.format("%sTotal Labs score", prefix)
      ) / maxPoints[LAB]);
      student.setTotalProjectScore(view.promptNum(
        String.format("%sTotal Projects score", prefix)
      ) / maxPoints[PROJECT]);
      student.setTotalZyBookScore(view.promptNum(
        String.format("%sTotal zyBook score", prefix)
      ) / maxPoints[ZYBOOK]);
      student.setTotalExamScore(view.promptNum(
        String.format("%sTotal Exams score", prefix)
      ) / maxPoints[EXAM]);
      student.setFinalExamScore(view.promptNum(
        String.format("%sFinal Exams score", prefix)
      ) / maxPoints[FINAL_EXAM]);
      
      // check for duplicate
      boolean isDuplicate = false;
      for (int i = 0; i < index; i++) {
        if (student.equals(students[i])) {
          isDuplicate = true;
        }
      }
      if (isDuplicate) {
        view.display(
          "Student with matching WID already entered. Entry ignored."
        );
        index--;
      } else {
        students[index] = student;
        view.display(String.format("%d Student(s) entered.", index + 1));
      }

      // prompt to continue
      view.section();
      if (!view.promptYN("Enter another student?")) {
        view.section();
        break;
      }
    }

    // display results
    for (int i = 0; i <= index; i++) {
      view.promptNext(students[i].toString());
    }
    view.display("All students displayed.");
  }
}
