package tn.esprit.rh.achat;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ActiviteTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepositoryMock;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @Test
    public void testAddSecteurActivite() {
        // Créez un secteur d'activité simulé
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("ABC");
        secteurActivite.setLibelleSecteurActivite("Test");

        // Configurez le comportement simulé du repository
        when(secteurActiviteRepositoryMock.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        // Appelez la méthode à tester
        secteurActiviteService.addSecteurActivite(secteurActivite);

        // Vérifiez si la méthode save du repository a été appelée avec le bon secteur d'activité
        verify(secteurActiviteRepositoryMock).save(secteurActivite);
    }

    @Test
    public void testDeleteSecteurActivite() {
        // ID du secteur d'activité à supprimer
        Long id = 1L;

        // Configurez le comportement simulé du repository
        doNothing().when(secteurActiviteRepositoryMock).deleteById(id);

        // Appelez la méthode à tester
        secteurActiviteService.deleteSecteurActivite(id);

        // Vérifiez si la méthode deleteById du repository a été appelée avec le bon ID
        verify(secteurActiviteRepositoryMock).deleteById(id);
    }
}
