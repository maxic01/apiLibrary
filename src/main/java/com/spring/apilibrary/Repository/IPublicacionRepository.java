package com.spring.apilibrary.Repository;


import com.spring.apilibrary.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacionRepository extends JpaRepository<Publicacion, Long> {
}
