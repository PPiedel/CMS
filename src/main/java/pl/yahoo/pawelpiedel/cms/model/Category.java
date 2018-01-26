package pl.yahoo.pawelpiedel.cms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Category {
    @Id
    private long id;

    private String category;

    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;
}
