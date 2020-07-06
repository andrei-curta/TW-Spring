package facultate.project.model;

import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rights_roles",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "right_id")})
    private Set<Right> rights = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "resources_roles", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private Set<Resource> resources = new HashSet<>();


    public Role() {

    }

    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rightList) {
        this.rights = rightList;
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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public List<Pair<Resource, Right>> getResourcesRights(){

        List<Pair<Resource, Right>> list = new ArrayList<>();

        Object[] resources = this.resources.toArray();
        Object[] rights = this.rights.toArray();

        int length = Math.min(resources.length, rights.length);

        for (int i=0; i < length; i++){
            Pair<Resource,Right> pair =  Pair.of((Resource)resources[i], (Right)rights[i]);
            list.add(pair);
        }

        return list;
    }

    public Role(String name) {
        this.name = name;
    }

    public void addRight(Right right){
        rights.add(right);
    }

    public void addResource(Resource resource){
        resources.add(resource);
    }
}
