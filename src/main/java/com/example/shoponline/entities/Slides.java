package com.example.shoponline.entities;

import com.example.shoponline.entities.enums.SlideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "slides_tbl")
public class Slides {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "slides_title")
    private String title;

    @Column(name = "slides_image")
    private String image;

    @Column (name = "slides_status")
    @Enumerated(EnumType.STRING)
    private SlideStatus status;

}
