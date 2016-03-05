package testtesting.testing;

/**
 * Created by filip on 2/20/2016.
 */
public class Student {

    private String first_name,last_name;
    private int id,age;

    public Student() {
    }

   public void print_student(){
       System.out.println(id +" "+first_name+" "+last_name +" "+age);
   }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
