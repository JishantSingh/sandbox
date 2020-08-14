import java.util.*;

public class StudentGradeManagementSystem {
    /**
     * size 21 based on given limit in question
     */
    ArrayList<Student> students = new ArrayList<>(21);

    /**
     * privately accessible modifier
     */
    private void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Option 1 implementation
     */
    private void printList() {
        System.out.println("Student\tMark1\tMark2\tMark3\tAverage\tGrade");
        for (Student student : students) {
            student.prettyPrint();
        }
    }

    /**
     * stable selection sort
     * does not modify instance owned list of students
     *
     * @return sorted list of students based on name, algo: selection sort
     */
    private ArrayList<Student> ascendingSelectionSort() {
        int numStudents = this.students.size();
        ArrayList<Student> studentList = new ArrayList<>(this.students);
        for (int i = 0; i < numStudents; i++) {
            int maxIndex = i;
            //calculate student with max marks in remaining array i.e index >= i, array is
            // descending sorted before that;
            for (int j = i; j < numStudents; j++) {
                if (studentList.get(maxIndex).name.compareToIgnoreCase(studentList.get(j).name) > 0)
                    maxIndex = j;
            }
            //swapping student with maximum marks with student from index i
            Student temporayHolder = studentList.get(maxIndex);
            studentList.set(maxIndex, studentList.get(i));
            studentList.set(i, temporayHolder);
        }
        return studentList;
    }

    /**
     * Option 2 implementation
     */
    private void printListAlphabetically() {
        System.out.println("Student\tMark1\tMark2\tMark3\tAverage\tGrade");
        for (Student student : this.ascendingSelectionSort()) {
            student.prettyPrint();
        }
    }

    /**
     * stable insertion sort
     * does not modify instance owned list of students
     *
     * @return
     */
    private ArrayList<Student> insertionSortDescending() {
        ArrayList<Student> studentList = new ArrayList<>(this.students);
        int numStudents = this.students.size();
        for (int i = 1; i < numStudents; i++) {
            Student key = studentList.get(i);
            int j = i - 1;
            while (j > 0 && studentList.get(j).averageMarks < key.averageMarks) {
                studentList.set(j + 1, studentList.get(j));
                j = j - 1;
            }
            studentList.set(j + 1, key);
        }
        return studentList;
    }

    /**
     * Option 3 implementation
     */
    private void printListDescendingOnAverageMarks() {
        System.out.println("Student\tMark1\tMark2\tMark3\tAverage\tGrade");
        for (Student student : this.insertionSortDescending()) student.prettyPrint();
    }

    /**
     * uses Collections provided binary search with custom defined comparator
     *
     * @param requiredAverage: required student average
     * @return Student if found, else throws IllegalArgumentException
     */
    private Student findStudentWithAverage(double requiredAverage) {
        ArrayList<Student> studentList = new ArrayList<>(this.students);
        studentList.sort(new AverageMarksComparator());
        int index = Collections.binarySearch(studentList,
                new Student(null, requiredAverage, requiredAverage, requiredAverage),
                new AverageMarksComparator());
        if (index < 0) {
            System.out.println("No student with average marks= " + requiredAverage);
            throw new IllegalArgumentException();
        }
        return studentList.get(index);
    }

    /**
     * uses linear search to find student with minimum average, requires avg marks for all students <=100
     *
     * @return student with minimum average
     */
    private Student getStudentWithMinimumAverage() {
        Student requiredStudent = new Student("Unnamed", 101, 101, 101);
        for (Student student : this.students) {
            if (requiredStudent.averageMarks >= student.averageMarks) requiredStudent = student;
        }
        return requiredStudent;
    }

    /**
     * TreeMap provides implicit ordering by key.
     * iterates over list of students and groups result in treeMap
     */
    private void printGradeDistribution() {
        TreeMap<Character, Integer> distribution = new TreeMap<>();
        distribution.put('A', 0);
        distribution.put('B', 0);
        distribution.put('C', 0);
        distribution.put('D', 0);
        distribution.put('F', 0);
        for (Student student : this.students) {
            distribution.put(student.grade, distribution.getOrDefault(student.grade, 0) + 1);
        }
        System.out.print("Grade distribution: ");
        for (Character key : distribution.keySet()) {
            System.out.print(key + "=" + distribution.get(key) + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentGradeManagementSystem studentGradeManagementSystem = new StudentGradeManagementSystem();
        System.out.println("Enter number of students?");
        int numberOfStudents = sc.nextInt();
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.println("Enter name and 3 marks for student " + i + " ?");
            String name = sc.next();
            double marks1 = sc.nextDouble();
            double marks2 = sc.nextDouble();
            double marks3 = sc.nextDouble();
            studentGradeManagementSystem.addStudent(new Student(name, marks1, marks2, marks3));
        }
        boolean isChoiceZero = false;
        do {
            System.out.println("============================================\n" +
                    "1: Print the entire list\n" +
                    "2: Sort and print the list alphabetically\n" +
                    "3: Sort and print the list in descending order based on the average.\n" +
                    "4: Find the student average\n" +
                    "5: Find the student who has the minimum average\n" +
                    "6: Print the grade distribution\n" +
                    "0: Exit\n" +
                    "============================================\n" + "Enter your choice?");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("This is the end of the program...Thank you");
                    isChoiceZero = true;
                    break;
                case 1:
                    studentGradeManagementSystem.printList();
                    break;
                case 2:
                    studentGradeManagementSystem.printListAlphabetically();
                    break;
                case 3:
                    studentGradeManagementSystem.printListDescendingOnAverageMarks();
                    break;
                case 4:
                    System.out.println("Enter the student average: ");
                    try {
                        double requiredAverage = sc.nextDouble();
                        Student requiredStudent = studentGradeManagementSystem.findStudentWithAverage(requiredAverage);
                        System.out.println("Student with the average is: " + requiredStudent.name
                                + "\tAverage=" + requiredStudent.averageMarks);

                    } catch (Exception ignored) {
                    } finally {
                        break;
                    }
                case 5:
                    Student student = studentGradeManagementSystem.getStudentWithMinimumAverage();
                    System.out.println("Student with min average is: " + student.name + "\tAverage=" + student.averageMarks);
                    break;

                case 6:
                    studentGradeManagementSystem.printGradeDistribution();
                    break;
                default:
                    System.out.println("Illegal input, try again");
            }
        } while (!isChoiceZero);

    }
}

/**
 * Defines blueprint for Student object
 */
class Student {
    String name;
    double marks1;
    double marks2;
    double marks3;
    double averageMarks;
    char grade;

    /**
     * parameterized constructor
     *
     * @param name
     * @param marks1
     * @param marks2
     * @param marks3
     */
    public Student(String name, double marks1, double marks2, double marks3) {
        this.name = name;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.averageMarks = (marks1 + marks2 + marks3) / 3;

        //upper bounds not needed as it is implicit by order of conditionals
        if (this.averageMarks >= 90) this.grade = 'A';
        else if (this.averageMarks >= 80) this.grade = 'B';
        else if (this.averageMarks >= 70) this.grade = 'C';
        else if (this.averageMarks >= 60) this.grade = 'D';
        else this.grade = 'F';
    }

    /**
     * pretty printing for repetitive use in options 1,2,3
     */
    public void prettyPrint() {
        System.out.println(this.name + "\t" + this.marks1 + "\t" + this.marks2 + "\t"
                + this.marks3 + "\t" + this.averageMarks + "\t" + this.grade);
    }

}

/**
 * Comparator used for comparison based on average marks. Used for sorting and binary search
 * provided by java.util.Collections
 */
class AverageMarksComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.averageMarks < s2.averageMarks) return -1;
        else if (s1.averageMarks > s2.averageMarks) return 1;
        return 0;
    }
}
