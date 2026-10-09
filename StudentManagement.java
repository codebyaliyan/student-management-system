import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            menu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    run = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
        sc.close();
    }

    private static void menu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Search Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }


    private static void addStudent() {
        int id = readInt("Enter Student ID: ");

        if (findById(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists.");
            return;
        }

        String name = readName("Enter Student Name: ");
        double marks = readMarks("Enter Student Marks (0-100): ");

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        printHeader();
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("Total students: " + students.size());
    }

    private static void updateStudent() {
        int id = readInt("Enter Student ID to update: ");
        Student s = findById(id);

        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Current record:");
        printHeader();
        System.out.println(s);

        String name = readName("Enter updated Student Name: ");
        double marks = readMarks("Enter updated Student Marks (0-100): ");

        s.setName(name);
        s.setMarks(marks);
        System.out.println("Student updated successfully.");
    }

    private static void searchStudent() {
        int id = readInt("Enter Student ID to search: ");
        Student s = findById(id);

        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
        } else {
            printHeader();
            System.out.println(s);
        }
    }

    private static void deleteStudent() {
        int id = readInt("Enter Student ID to delete: ");
        Student s = findById(id);

        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        printHeader();
        System.out.println(s);

        if (readYesNo("Are you sure you want to delete this student? (y/n): ")) {
            students.remove(s);
            System.out.println("Student with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static void printHeader() {
        System.out.println("\n| ID     | Name                 | Marks   |");
        System.out.println("|--------|----------------------|---------|");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    /** Keeps asking until the user enters marks between 0 and 100. */
    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double marks = Double.parseDouble(sc.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            }
            if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }
}