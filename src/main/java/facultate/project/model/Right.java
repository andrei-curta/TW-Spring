package facultate.project.model;

import facultate.project.util.RightType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="right_over_resource")
public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "right_id")
    private Integer id;

    @Column
    private String name;

    public Right() {
    }

    public Right(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
