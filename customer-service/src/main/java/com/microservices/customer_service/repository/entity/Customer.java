package com.microservices.customer_service.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id", nullable = false, unique = true, length = 8)
    @NotEmpty(message = "El numberId no puede estar vacio")
    @Size(min = 8, max = 8, message = "El tamanno del carnet de identidad debe ser 8")
    private String numberID;

    @NotEmpty(message = "El nombre(s) no puede estan vacio")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Los apellidos no puede estan vacio")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "El nombre no puede estan vacio")
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "No es una direccion de correo bien formada")
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @NotNull(message = "La region no puede ser vacia")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Region region;

    private String state;

}
