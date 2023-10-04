package com.example.shoponline.dto;

import com.example.shoponline.entities.enums.SlideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideDTO {
    private long id;
    private String title;
    private String image;
    private SlideStatus slideStatus;
}
