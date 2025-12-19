/**
 * Customer 클래스 (STEP 4: 캡슐화 적용)
 * 고객 정보를 관리하는 클래스입니다.
 */
public class Customer {
    // 필드 - private으로 캡슐화 (이미 되어있음)
    private String name;     // 고객명
    private String email;    // 이메일
    private String grade;    // 등급

    /**
     * 생성자
     */
    public Customer(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 고객 정보 출력
     */
    public void displayCustomerInfo() {
        System.out.printf("고객명: %s | 이메일: %s | 등급: %s%n", name, email, grade);
    }
}