package com.project.PFEBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateEnvoie = LocalDateTime.now();
    String content;

    Boolean lu=false ;
    @ManyToOne(fetch = FetchType.EAGER)
    Utilisateur sender ;
    @ManyToOne(fetch = FetchType.EAGER)
    Utilisateur receiver;
    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", dateEnvoie=" + dateEnvoie +
                ", content='" + content + '\'' +
                ", lu=" + lu +
                '}';
    }
}
