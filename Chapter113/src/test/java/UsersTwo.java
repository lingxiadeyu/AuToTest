import java.util.Objects;

public class UsersTwo {
    private String name;
    private String age;

    public UsersTwo(String name, String age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersTwo usersTwo = (UsersTwo) o;
        return Objects.equals(name, usersTwo.name) &&
                Objects.equals(age, usersTwo.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }





}
