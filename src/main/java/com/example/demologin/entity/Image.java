package com.example.demologin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "link")
    private String link;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "type")
    private String type;

    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
}
