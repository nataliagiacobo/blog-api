package br.com.blog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts")
@SQLRestriction("active is true")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content", nullable = false, length = 3000)
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy="post")
    private List<Comment> comments;
    @Column(name = "active", nullable = false)
    private Boolean active;
}
