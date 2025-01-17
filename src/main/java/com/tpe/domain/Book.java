package com.tpe.domain;

import lombok.*;

import javax.crypto.Mac;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Kitap ismi boşluk olamaz!!!")
    @NotNull(message = "Kitap ismi girilmelidir!!!")//hata icin ayrı ayrı mesaj verebiliriz.
    @Size(min = 3,max = 50,message = "Kitap ismi en az 2 karakter icermelidir!!!")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Yazar ismi boşluk olamaz!!!")
    @NotNull(message = "Yazar ismi girilmelidir!!!")//hata icin ayrı ayrı mesaj verebiliriz.
    @Size(min = 3,max = 50,message = "Yazar ismi en az 2 karakter icermelidir!!!")
    @Column(nullable = false)
    private String author;


    @NotBlank(message = "Lütfen yayın yılını giriniz!!!")
    @Column(nullable = false)
    private String publicationDate;//yayın tarihi

}
