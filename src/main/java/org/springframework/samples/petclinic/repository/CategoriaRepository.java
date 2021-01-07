package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Categoria;

public interface CategoriaRepository extends Repository<Categoria, Integer> {

	void save(Categoria categoria) throws DataAccessException;

	@Query("SELECT categoria FROM Categoria categoria WHERE categoria.id =:categoriaId")
	public Categoria findCategoriaById(@Param("categoriaId") int categoriaId);

}
