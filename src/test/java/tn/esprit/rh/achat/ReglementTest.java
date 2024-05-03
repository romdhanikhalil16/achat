package tn.esprit.rh.achat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import java.util.Date;

public class ReglementTest {

    private Reglement reglement;

    @Before
    public void setUp() {
        reglement = new Reglement();
    }

    @Test
    public void testGettersAndSetters() {
        Long idReglement = 1L;
        float montantPaye = 100.0f;
        float montantRestant = 50.0f;
        Boolean payee = true;
        Date dateReglement = new Date();
        Facture facture = mock(Facture.class);

        reglement.setIdReglement(idReglement);
        reglement.setMontantPaye(montantPaye);
        reglement.setMontantRestant(montantRestant);
        reglement.setPayee(payee);
        reglement.setDateReglement(dateReglement);
        reglement.setFacture(facture);

        assertEquals(idReglement, reglement.getIdReglement());
        assertEquals(montantPaye, reglement.getMontantPaye(), 0.01);
        assertEquals(montantRestant, reglement.getMontantRestant(), 0.01);
        assertEquals(payee, reglement.getPayee());
        assertEquals(dateReglement, reglement.getDateReglement());
        assertEquals(facture, reglement.getFacture());
    }

    @Test
    public void testConstructor() {
        Long idReglement = 1L;
        float montantPaye = 100.0f;
        float montantRestant = 50.0f;
        Boolean payee = true;
        Date dateReglement = new Date();
        Facture facture = mock(Facture.class);

        reglement = new Reglement(idReglement, montantPaye, montantRestant, payee, dateReglement, facture);

        assertEquals(idReglement, reglement.getIdReglement());
        assertEquals(montantPaye, reglement.getMontantPaye(), 0.01);
        assertEquals(montantRestant, reglement.getMontantRestant(), 0.01);
        assertEquals(payee, reglement.getPayee());
        assertEquals(dateReglement, reglement.getDateReglement());
        assertEquals(facture, reglement.getFacture());
    }
}

