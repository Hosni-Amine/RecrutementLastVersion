package tn.tritux.pfe.recrutement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.tritux.pfe.recrutement.dto.request.OfferRequest;
import tn.tritux.pfe.recrutement.dto.response.OfferResponse;
import tn.tritux.pfe.recrutement.entity.Offer;
import tn.tritux.pfe.recrutement.repository.CandidatureRepository;
import tn.tritux.pfe.recrutement.repository.OfferRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OfferServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(OfferServiceImplTest.class);

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private CandidatureRepository candidatureRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger.info("Mockito annotations initialized");
    }

    @Test
    void testAjouterOffer() {
        OfferRequest request = new OfferRequest();
        Offer offer = new Offer();

        logger.info("Testing adding offer with request: {}", request);

        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        OfferResponse response = offerService.ajouterOffer(request);

        assertNotNull(response);
        verify(offerRepository, times(1)).save(any(Offer.class));

        logger.info("Add offer test passed for request: {}", request);
    }

    @Test
    void testAfficherOfferParId() {
        Offer offer = new Offer();

        logger.info("Testing get offer by id: {}", 1L);

        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer));

        OfferResponse response = offerService.afficherOfferParId(1L);

        assertNotNull(response);
        verify(offerRepository, times(1)).findById(1L);

        logger.info("Get offer by id test passed for id: {}", 1L);
    }
}
