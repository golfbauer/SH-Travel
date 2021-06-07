package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import de.hhn.se.labswp.wstgsh.webapi.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReisekatalogControllerTest {
    @Mock
    private ReisekatalogRepository reisekatalogRepository;

    @Mock
    private ReiseRepository reiseRepository;

    private ReisekatalogController underTest;

    @BeforeEach
    void setup() {
        underTest = new ReisekatalogController(reisekatalogRepository, reiseRepository);
    }

    @Test
    void canGetAllReisekataloge() {
        //when
        underTest.all();
        //then
        verify(reisekatalogRepository).findAll();
    }

    @Test
    void canGetReisekatalogWithId() {
        //given
        long id = 10;
        given(reisekatalogRepository.findById(id)).willReturn(java.util.Optional.of(new Reisekatalog()));
        //when
        underTest.one(id);
        //then
        verify(reisekatalogRepository).findById(id);
    }

    @Test
    void reisekatalogWithIdDoesntExist() {
        //given
        long id = 10;
        given(reisekatalogRepository.findById(id)).willReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> underTest.one(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Id nicht gefunden.");
    }

    @Test
    void canDeleteReisekatalog() {
        //given
        long id = 10;
        //when
        underTest.deleteReise(id);
        //then
        verify(reisekatalogRepository).deleteById(id);
    }

    @Test
    void canAddReiseToReisekatalog() {
        //given
        List<Reisepunkt> reisepunkte = new ArrayList<>();
        reisepunkte.add(new Reisepunkt(34L, 4.1f, 32.32f,
                "nutzer@example.com", "NewTestReisepunkt"));
        List<Reisekatalog> reisekataloge = new ArrayList<>();
        Reise reise = new Reise(new Date(), "TestReise", true,
                reisepunkte, reisekataloge);
        Long id = 1L;
        Reisekatalog reisekatalog = new Reisekatalog(id, "nutzer@secondExample.com");

        //when
        underTest.addReise(reise.getId(),id);
        //then
        ArgumentCaptor<Reisekatalog> reisekatalogArgumentCaptor = ArgumentCaptor.forClass(Reisekatalog.class);
        verify(reisekatalogRepository).save(reisekatalogArgumentCaptor.capture());
        Reisekatalog capturedReisekatalog = reisekatalogArgumentCaptor.getValue();
        reisekatalog.addReise(reise);
        assertThat(capturedReisekatalog).isEqualTo(reisekatalog);
    }

    @Test
    void addReiseThrowsExceptionIfAlreayAdded(){
        //given
        Long id = 10L;
        Reise reise = new Reise();
        List<Reise> reisen = new ArrayList<>();
        reisen.add(reise);

        Reisekatalog reisekatalog = new Reisekatalog(id,"Nutzer@example.com",reisen);

        //then
        assertThatThrownBy(() -> underTest.addReise(reise.getId(),id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Reise konnte nicht zu Reisekatalog hinzugefügt werden.");
    }
}
