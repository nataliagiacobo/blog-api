package br.com.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "albums", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "userId"})})
@SQLRestriction("active is true")
public class Album extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy="album", fetch = FetchType.EAGER)
    private List<Image> photos;
    @Column(name = "active", nullable = false)
    private Boolean active;
}