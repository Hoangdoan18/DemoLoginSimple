package com.example.demologin.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug", columnDefinition = "varchar(600) NOT NULL")
    private String slug;

    @Column(name = "title", columnDefinition = "varchar(300) NOT NULL")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "create_at")
    private Timestamp createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private User createBy;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_by")
    private User modifyBy;

    @Column(name = "publish_at")
    private Timestamp publishAt;

    @Column(name = "status", columnDefinition = "int default 0")
    private int status;
}
