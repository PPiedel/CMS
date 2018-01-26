package pl.yahoo.pawelpiedel.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Collection<Post> posts;
}
