package pl.yahoo.pawelpiedel.cms.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Post {
    @Id
    private long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "posts_tags",
    joinColumns =  @JoinColumn(name = "post_id") ,
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Collection<Tag> tags;

}
