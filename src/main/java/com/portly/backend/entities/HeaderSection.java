package com.portly.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeaderSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heroDescription;

    @Column(length = 10000000)
    private String image;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> cv;

    private String backgroundColour;
    private String textColour;
    private String highlightColour;

}
