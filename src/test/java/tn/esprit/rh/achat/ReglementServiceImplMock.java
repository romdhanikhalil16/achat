package tn.esprit.rh.achat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplMock {

        @Mock
        private ReglementRepository reglementRepository;

        @InjectMocks
        private ReglementServiceImpl reglementService;

        @Test
        public void testRetrieveAllReglements() {
            // Given
            List<Reglement> reglements = new ArrayList<>();
            reglements.add(new Reglement());
            when(reglementRepository.findAll()).thenReturn(reglements);

            // When
            List<Reglement> result = reglementService.retrieveAllReglements();

            // Then
            assertEquals(1, result.size());
            verify(reglementRepository, times(1)).findAll();
        }

        @Test
        public void testAddReglement() {
            // Given
            Reglement reglement = new Reglement();

            // When
            reglementService.addReglement(reglement);

            // Then
            verify(reglementRepository, times(1)).save(reglement);
        }

        @Test
        public void testRetrieveReglement() {
            // Given
            Long id = 1L;
            Reglement reglement = new Reglement();
            when(reglementRepository.findById(id)).thenReturn(java.util.Optional.of(reglement));

            // When
            Reglement result = reglementService.retrieveReglement(id);

            // Then
            assertEquals(reglement, result);
            verify(reglementRepository, times(1)).findById(id);
        }

        // Similar tests can be written for other methods...

    }

