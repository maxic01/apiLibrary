package com.spring.apilibrary.Repository;



import com.spring.apilibrary.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByPublicacionId(long publicacionId);
}
