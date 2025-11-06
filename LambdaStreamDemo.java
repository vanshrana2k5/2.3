import java.util.*;
import java.util.stream.*;
import java.util.Comparator;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | Price: " + price;
    }
}

public class LambdaStreamDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Sort Employees using Lambda Expressions");
            System.out.println("2. Filter & Sort Students using Streams");
            System.out.println("3. Product Stream Operations");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    employeeSortingDemo();
                    break;
                case 2:
                    studentFilteringDemo();
                    break;
                case 3:
                    productStreamOpsDemo();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ===== Part (a) =====
    public static void employeeSortingDemo() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 28, 50000));
        employees.add(new Employee("Alice", 25, 60000));
        employees.add(new Employee("Bob", 30, 45000));
        employees.add(new Employee("David", 22, 70000));

        System.out.println("\nOriginal List:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }

    // ===== Part (b) =====
    public static void studentFilteringDemo() {
        List<Student> students = Arrays.asList(
            new Student("Rahul", 82),
            new Student("Priya", 74),
            new Student("Aman", 91),
            new Student("Sneha", 65),
            new Student("Vikram", 88)
        );

        System.out.println("\nStudents scoring above 75% (sorted by marks):");

        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))
                .map(s -> s.name + " (" + s.marks + ")")
                .forEach(System.out::println);
    }

    // ===== Part (c) =====
    public static void productStreamOpsDemo() {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 75000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2000, "Clothing"),
            new Product("Fridge", 30000, "Appliances"),
            new Product("Washing Machine", 25000, "Appliances")
        );

        // Group by category
        System.out.println("\nProducts Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        grouped.forEach((category, list) -> {
            System.out.println(category + ": " + list);
        });

        // Most expensive product in each category
        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Optional<Product>> expensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        expensiveByCategory.forEach((category, product) ->
            System.out.println(category + ": " + product.get())
        );

        // Average price
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    } 
}