package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMock {

    @Mock
    private FournisseurRepository fournisseurRepository;


    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    Fournisseur fournisseur = new Fournisseur(1L,"code1","libelle1");

    List<Fournisseur> fournisseurList = new ArrayList<Fournisseur>(){
           {
               add(new Fournisseur(2L,"code2","libelle2"));
               add(new Fournisseur(3L,"code3","libelle3"));
           }
      };

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testRetrieveFournisseur() {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(2L);
        Assertions.assertNotNull(fournisseur1);
    }


    @Test
    public void testRetrieveAllFournisseurs() {
        // Mocking data
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur(1L, "code1", "libelle1"));
        fournisseurs.add(new Fournisseur(2L, "code2", "libelle2"));
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // Calling the method under test
        List<Fournisseur> retrievedFournisseurs = fournisseurService.retrieveAllFournisseurs();

        // Assertions
        assertEquals(fournisseurs.size(), retrievedFournisseurs.size());
        assertEquals(fournisseurs.get(0), retrievedFournisseurs.get(0));
        assertEquals(fournisseurs.get(1), retrievedFournisseurs.get(1));
    }

}
