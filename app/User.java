public class User {
    private String userName;
    private String password;
    private double age;
    private String email;
    private int acecssLevel;



    public User(String userName, String password, double age, String email) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel=0;

    }
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.age = 0;
        this.email = email;
        this.acecssLevel=0;
    }
    public User(String userName, String password,double age, String email,int acecssLevel) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel=acecssLevel;
    }
    public int getAcecssLevel(){return acecssLevel;}

    public void setAcecssLevel(int acecssLevel) {
        this.acecssLevel = acecssLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", acecssLevel=" + acecssLevel +
                '}';
}
