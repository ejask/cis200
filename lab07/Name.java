/* 
 * Name.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 * 
 * Represents a Name associated with a person, storing the person's full name,
 * age, and initials. 
 */
public class Name {
  private String fullName;
  private int age;
  private char firstInitial;
  private char middleInitial;
  private char lastInitial;

  private static final String DEFAULT_NAME = "Tater O Tot";
  private static final int DEFAULT_AGE = -1;
  public static final int MIN_AGE = 0;
  public static final int MAX_AGE = 100;

  public Name() {
    this(DEFAULT_NAME, DEFAULT_AGE);
  }

  public Name(int age) {
    this(DEFAULT_NAME, age);
  }

  public Name(String name) {
    this(name, DEFAULT_AGE);
  }

  public Name(String name, int age) {
    this.fullName = name.trim();
    setAge(age);
  }

  public void setAge(int age) {
    if (age >= MIN_AGE && age <= MAX_AGE) {
      this.age = age;
    } else {
      this.age = DEFAULT_AGE;
    }
  }

  public void setFirstInitial() {
    this.firstInitial = this.fullName.charAt(0);
  }

  public void setMiddleInitial() {
    this.middleInitial = this.fullName.split(" ")[1].charAt(0);
  }

  public void setLastInitial() {
    this.lastInitial = this.fullName.split(" ")[2].charAt(0);
  }

  public String getFullName() {
    return this.fullName;
  }

  public int getAge() {
    return this.age;
  }

  public char getFirstInitial() {
    return this.firstInitial;
  }

  public char getMiddleInitial() {
    return this.middleInitial;
  }

  public char getLastInitial() {
    return this.lastInitial;
  }

  public String rearrage() {
    String[] names = this.fullName.split(" ");
    return String.format("%s, %s %c.", names[2], names[0], this.middleInitial);
  }
}
