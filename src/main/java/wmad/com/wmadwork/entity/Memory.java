package wmad.com.wmadwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "memories")
@Getter
@Setter
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 255)
    private String description;


    @Column(nullable = false, length = 150)
    private String category;

    public Memory(Object o, String springBootTutorial, String s, String education) {

    }

    public Memory() {

    }

    public void setUser(User user) {
    }
}
