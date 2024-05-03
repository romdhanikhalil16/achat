package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategorieProduitTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Test
    void testRetrieveAllCategorieProduits() {
        // Arrange
        List<CategorieProduit> expectedList = new ArrayList<>();
        when(categorieProduitRepository.findAll()).thenReturn(expectedList);

        // Act
        List<CategorieProduit> actualList = categorieProduitService.retrieveAllCategorieProduits();

        // Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void testAddCategorieProduit() {
        // Arrange
        CategorieProduit cp = new CategorieProduit();

        // Act
        categorieProduitService.addCategorieProduit(cp);

        // Assert
        verify(categorieProduitRepository, times(1)).save(cp);
    }

    @Test
    void testDeleteCategorieProduit() {
        // Arrange
        Long id = 1L;

        // Act
        categorieProduitService.deleteCategorieProduit(id);

        // Assert
        verify(categorieProduitRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateCategorieProduit() {
        // Arrange
        CategorieProduit cp = new CategorieProduit();

        // Act
        categorieProduitService.updateCategorieProduit(cp);

        // Assert
        verify(categorieProduitRepository, times(1)).save(cp);
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Arrange
        Long id = 1L;
        CategorieProduit expectedCategorieProduit = new CategorieProduit();
        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(expectedCategorieProduit));

        // Act
        CategorieProduit actualCategorieProduit = categorieProduitService.retrieveCategorieProduit(id);

        // Assert
        assertEquals(expectedCategorieProduit, actualCategorieProduit);
    }
}