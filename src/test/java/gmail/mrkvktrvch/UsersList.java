package gmail.mrkvktrvch;

public enum UsersList {
    MARK("Mark", "Kasimov", "mrkvktrvch@gmail.com", "28", "100500", "IT"),
    IVAN("Ivan", "Petrov", "yasobaka@mail.ru", "35", "50000", "Security");

    private String name;
    private String lastName;
    private String email;
    private String age;
    private String salary;
    private String department;

    UsersList(String name, String lastName, String email, String age, String salary, String department) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}