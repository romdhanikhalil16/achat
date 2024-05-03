package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactureServiceImplTest {

    @Mock
    FactureRepository factureRepository;

    @Mock
    DetailFactureRepository detailFactureRepository;

    @Mock
    ProduitRepository produitRepository;

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    OperateurRepository operateurRepository;

    @Mock
    ReglementServiceImpl reglementService;

    @InjectMocks
    FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllFactures_shouldReturnListOfFactures() {
        // Données de test
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();
        List<Facture> factures = Arrays.asList(facture1, facture2);

        // Définir le comportement du mock
        when(factureRepository.findAll()).thenReturn(factures);

        // Appel de la méthode à tester
        List<Facture> result = factureService.retrieveAllFactures();

        // Vérifier le résultat
        assertEquals(2, result.size());
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    void addFacture_shouldReturnAddedFacture() {
        // Données de test
        Facture facture = new Facture();

        // Définir le comportement du mock
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        // Appel de la méthode à tester
        Facture result = factureService.addFacture(facture);

        // Vérifier le résultat
        assertNotNull(result);
        verify(factureRepository, times(1)).save(any(Facture.class));
    }

    @Test
    void cancelFacture_shouldSetFactureAsArchived() {
        // Données de test
        Long factureId = 1L;
        Facture facture = new Facture();

        // Définir le comportement du mock
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        // Appel de la méthode à tester
        factureService.cancelFacture(factureId);

        // Vérifier le résultat
        assertTrue(facture.isArchivee());
        verify(factureRepository, times(1)).save(any(Facture.class));
    }

    @Test
    void retrieveFacture_shouldReturnFactureById() {
        // Données de test
        Long factureId = 1L;
        Facture facture = new Facture();
        facture.setIdFacture(factureId);

        // Définir le comportement du mock
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        // Appel de la méthode à tester
        Facture result = factureService.retrieveFacture(factureId);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals(factureId, result.getIdFacture());
    }



    @Test
    void pourcentageRecouvrement_shouldReturnPercentageOfRecovery() {
        // Données de test
        Date startDate = new Date();
        Date endDate = new Date();
        float totalFacturesEntreDeuxDates = 1000;
        float totalRecouvrementEntreDeuxDates = 800;
        float expectedPourcentage = 80.0f;

        // Définir le comportement du mock
        when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(totalFacturesEntreDeuxDates);
        when(reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(totalRecouvrementEntreDeuxDates);

        // Appel de la méthode à tester
        float result = factureService.pourcentageRecouvrement(startDate, endDate);

        // Vérifier le résultat
        assertEquals(expectedPourcentage, result);
    }



}
