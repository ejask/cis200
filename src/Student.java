/**
 * Student.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Models a student in CIS 200 with a first name, last name, and Wildcat ID.
 * Stores and calculates total scores for each category of assignment, as well
 * as the overall percentage and letter grade.
 */
public class Student {
  private static final double LAB_WEIGHT = 0.15;
  private static final double PROJECT_WEIGHT = 0.15;
  private static final double ZYBOOK_WEIGHT = 0.1;
  private static final double EXAM_WEIGHT = 0.3;
  private static final double FINAL_EXAM_WEIGHT = 0.3;

  private String firstName;
  private String lastName;
  private String wid;
  private double totalLabScore;
  private double totalProjectScore;
  private double totalZyBookScore;
  private double totalExamScore;
  private double finalExamScore;
  private double overAllScore;
  private char letterGrade;

  /** Constructs a new {@link Student} with default values. */
  public Student() {
    this.setFirstName("no name entered");
    this.setLastName("no name entered");
    this.setWid("no WID");
    this.setTotalLabScore(0.0);
    this.setTotalProjectScore(0.0);
    this.setTotalZyBookScore(0.0);
    this.setTotalExamScore(0.0);
    this.setFinalExamScore(0.0);
    calcOverAllPercentScore();
  }

  /**
   * Constructs a new {@link Student} with the given values. Numeric input is
   * accepted as decimal representations of percentages (e.g. 0.15 = 15%).
   * 
   * @param firstName the {@link Student}'s first name
   * @param lastName the {@link Student}'s last name
   * @param wid the {@link Student}'s Wildcat ID (WID)
   * @param totalLabScore the {@link Student}'s total lab score
   * @param totalProjectScore the {@link Student}'s total project score
   * @param totalZyBookScore the {@link Student}'s total zyBook score
   * @param totalExamScore the {@link Student}'s total exam score
   * @param finalExamScore the {@link Student}'s final exam score
   */
  public Student(
      String firstName, String lastName, String wid, double totalLabScore,
      double totalProjectScore, double totalZyBookScore, double totalExamScore,
      double finalExamScore) throws IllegalArgumentException {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setWid(wid);
    this.setTotalLabScore(totalLabScore);
    this.setTotalProjectScore(totalProjectScore);
    this.setTotalZyBookScore(totalZyBookScore);
    this.setTotalExamScore(totalExamScore);
    this.setFinalExamScore(finalExamScore);
    calcOverAllPercentScore();
  }

  /** @return this {@link Student}'s first name */
  public String getFirstName() {
    return this.firstName;
  }

  /** @return this {@link Student}'s last name */
  public String getLastName() {
    return this.lastName;
  }

  /** @return this {@link Student}'s Wildcat ID (WID) */
  public String getWid() {
    return this.wid;
  }

  /** @return this {@link Student}'s total lab score (as a percentage) */
  public double getTotalLabScore() {
    return this.totalLabScore;
  }

  /** @return this {@link Student}'s total project score (as a percentage) */
  public double getTotalProjectScore() {
    return this.totalProjectScore;
  }

  /** @return this {@link Student}'s total zyBook score (as a percentage) */
  public double getTotalZyBookScore() {
    return this.totalZyBookScore;
  }

  /** @return this {@link Student}'s total exam score (as a percentage) */
  public double getTotalExamScore() {
    return this.totalExamScore;
  }

  /** @return this {@link Student}'s final exam score (as a percentage) */
  public double getFinalExamScore() {
    return this.finalExamScore;
  }

  /** @return this {@link Student}'s overall grade (as a percentage) */
  public double getOverAllScore() {
    return this.overAllScore;
  }

  /** @return this {@link Student}'s overall letter grade */
  public char getLetterGrade() {
    return this.letterGrade;
  }

  /**
   * Returns a {@link String} representation of this {@link Student};
   * specifically the first name, last name, WID, weighted overall percentage
   * grade, and corresponding overall letter grade.
   *
   * @return a {@link String} representation of this {@link Student}
   */
  @Override
  public String toString() {
    return String.format(
        "Student Name: %s, %s\nWID: %s\nOverall Pct: %.1f%%\nFinal Grade: %c",
        this.getLastName(),
        this.getFirstName(),
        this.getWid(),
        this.getOverAllScore(),
        this.getLetterGrade());
  }

  /**
   * Compares the equality of this {@link Student} with another one, considering
   * them equal if they have the same Wildcat IDs (WIDs).
   *
   * @param stu the other {@link Student} to compare this {@link Student} to
   * @return {@code true} if the {@link Student}s are equal, {@code false}
   *     otherwise
   */
  public boolean equals(Student stu) {
    return this.getWid().equals(stu.getWid());
  }

  /**
   * Sets the value of this {@link Student}'s first name.
   * 
   * @param firstName this {@link Student}'s new last name
   */
  public void setFirstName(String firstName) {
    firstName.trim();
    this.firstName = Character.toUpperCase(firstName.charAt(0))
        + firstName.substring(1).toLowerCase();
  }

  /**
   * Sets the value of this {@link Student}'s last name.
   * 
   * @param lastName this {@link Student}'s new last name
   */
  public void setLastName(String lastName) {
    lastName.trim();
    this.lastName = Character.toUpperCase(lastName.charAt(0))
        + lastName.substring(1).toLowerCase();
  }

  /**
   * Sets the value of this {@link Student}'s Wildcat ID (WID).
   * 
   * @param wid this {@link Student}'s new WID
   * @throws IllegalArgumentException if the WID is not exactly 9 numeric
   *     characters
   */
  public void setWid(String wid) throws IllegalArgumentException {
    final int WID_LENGTH = 9;
    wid.trim();
    if (wid.length() != WID_LENGTH) {
      throw new IllegalArgumentException(String.format(
          "Invalid length; WID must be exactly %d characters", WID_LENGTH));
    }
    for (int c = 0; c <= wid.length(); c++) {
      if (!Character.isDigit(wid.charAt(c))) {
        throw new IllegalArgumentException(
            "Invalid input; WID must be entirely numeric");
      }
    }
    this.wid = wid;
  }

  /**
   * Sets the value of this {@link Student}'s Wildcat ID (WID).
   * 
   * @param wid this {@link Student}'s new WID
   * @throws IllegalArgumentException if the WID is not exactly 9 digits
   */
  public void setWid(int wid) throws IllegalArgumentException {
    setWid(String.valueOf(wid));
  }

  /**
   * Sets the value of this {@link Student}'s total lab score.
   * 
   * @param totalLabScore this {@link Student}'s new total lab score (as a
   *     decimal representation of a percentage)
   */
  public void setTotalLabScore(double totalLabScore) {
    this.totalLabScore = totalLabScore;
  }

  /**
   * Sets the value of this {@link Student}'s total project score.
   * 
   * @param totalProjectScore this {@link Student}'s new total project score (as
   *     a decimal representation of a percentage)
   */
  public void setTotalProjectScore(double totalProjectScore) {
    this.totalProjectScore = totalProjectScore;
  }

  /**
   * Sets the value of this {@link Student}'s total zyBook score.
   * 
   * @param totalZyBookScore this {@link Student}'s new total zyBook score (as
   *     a decimal representation of a percentage)
   */
  public void setTotalZyBookScore(double totalZyBookScore) {
    this.totalZyBookScore = totalZyBookScore;
  }

  /**
   * Sets the value of this {@link Student}'s total exam score.
   * 
   * @param totalExamScore this {@link Student}'s new total exam score (as a
   *     decimal representation of a percentage)
   */
  public void setTotalExamScore(double totalExamScore) {
    this.totalExamScore = totalExamScore;
  }

  /**
   * Sets the value of this {@link Student}'s final exam score.
   * 
   * @param finalExamScore this {@link Student}'s new final exam score (as a
   *     decimal representation of a percentage)
   */
  public void setFinalExamScore(double finalExamScore) {
    this.finalExamScore = finalExamScore;
  }

  /**
   * Sets the value of this {@link Student}'s final exam score.
   * 
   * @param finalExamScore this {@link Student}'s new final exam score (as a
   *     decimal representation of a percentage)
   * @throws NumberFormatException if the input is not resolvable as a number
   */
  public void setFinalExamScore(String finalExamScore) throws NumberFormatException {
    setFinalExamScore(Double.valueOf(finalExamScore));
  }

  /**
   * Calculates and stores the weighted overall score of this {@link Student} as
   * both a percentage and a letter grade.
   */
  private void calcOverAllPercentScore() {
    this.overAllScore = this.totalLabScore * LAB_WEIGHT
        + this.totalProjectScore * PROJECT_WEIGHT
        + this.totalZyBookScore * ZYBOOK_WEIGHT
        + this.totalExamScore * EXAM_WEIGHT
        + this.finalExamScore * FINAL_EXAM_WEIGHT;
    if (this.overAllScore >= 89.5) {
      this.letterGrade = 'A';
    } else if (this.overAllScore >= 79.5) {
      this.letterGrade = 'B';
    } else if (this.overAllScore >= 69.5) {
      this.letterGrade = 'C';
    } else if (this.overAllScore > 59.5) {
      this.letterGrade = 'D';
    } else {
      this.letterGrade = 'F';
    }
  }
}
