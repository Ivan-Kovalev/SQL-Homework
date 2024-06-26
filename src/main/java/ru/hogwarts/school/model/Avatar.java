package ru.hogwarts.school.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] preview;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;
}
