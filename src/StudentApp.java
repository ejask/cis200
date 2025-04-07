/**
 * StudentApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * TODO: class description
 */

public class StudentApp {
  public static void main(String[] args) {
    final int LAB = 0, PROJECT = 1, ZYBOOK = 2, EXAM = 3, FINAL_EXAM = 4;
    Student[] students = new Student[20];
    View view = new View();
    String prefix = "Please enter the total number of points possible for ";

    double[] maxPoints = new double[] {
      Double.parseDouble(view.prompt(String.format("%sLabs", prefix))),
      Double.parseDouble(view.prompt(String.format("%sProjects", prefix))),
      Double.parseDouble(view.prompt(String.format("%szyBook", prefix))),
      Double.parseDouble(view.prompt(String.format("%sExams", prefix))),
      Double.parseDouble(view.prompt(String.format("%sFinal Exam", prefix))),
    };
    prefix = "Enter the student's ";
    int index;
    view.nextSection();

    for (index = 0; index < students.length; index++) {
      Student student = new Student();
      boolean isDuplicate = false;

      // get identifying info
      student.setFirstName(view.prompt(String.format("%sfirst name", prefix)));
      student.setLastName(view.prompt(String.format("%slast name", prefix)));
      student.setWid(view.prompt(String.format("%sWID", prefix)));

      // check for duplicate
      for (int i = 0; i < index - 1; i++) {
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
        view.nextSection();

        // get scores
        student.setTotalLabScore(
          Double.parseDouble(
            view.prompt(String.format("%sTotal Labs score", prefix))
          ) / maxPoints[LAB]
        );
        student.setTotalProjectScore(
          Double.parseDouble(
            view.prompt(String.format("%sTotal Projects score", prefix))
          ) / maxPoints[PROJECT]
        );
        student.setTotalZyBookScore(
          Double.parseDouble(
            view.prompt(String.format("%sTotal zyBook score", prefix))
          ) / maxPoints[ZYBOOK]
        );
        student.setTotalExamScore(
          Double.parseDouble(
            view.prompt(String.format("%sTotal Exams score", prefix))
          ) / maxPoints[EXAM]
        );
        student.setFinalExamScore(
          Double.parseDouble(
            view.prompt(String.format("%sFinal Exams score", prefix))
          ) / maxPoints[FINAL_EXAM]
        );

        students[index] = student;
        view.display(String.format("%d Student(s) entered.", index + 1));
      }

      // prompt to continue
      view.nextSection();
      if (!view.promptYN("Enter another student?").equalsIgnoreCase("y")) {
        view.nextSection();
        break;
      }
    }

    // display results
    for (int i = 0; i <= index; i++) {
      view.display(String.format("%s", students[i]));
      view.promptContinue("\tPress enter to display next student");
      view.nextSection();
    }
    view.display("All students displayed.");
  }
}
