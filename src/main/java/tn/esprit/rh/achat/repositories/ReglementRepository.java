package tn.esprit.rh.achat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.rh.achat.entities.Reglement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReglementRepository extends CrudRepository<Reglement, Long>{
	Optional<Reglement> findById(Long id);
	List<Reglement> findAll();     // Other methods if any}
	@Query("SELECT r FROM Reglement r where r.facture.idFacture=:idFacture")
	List<Reglement> retrieveReglementByFacture(@Param("idFacture") Long idFacture);

	
	@Query("SELECT sum(r.montantPaye) FROM Reglement r where  r.dateReglement between :startDate"
			+ " and :endDate and r.facture.archivee=false")
	float getChiffreAffaireEntreDeuxDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
