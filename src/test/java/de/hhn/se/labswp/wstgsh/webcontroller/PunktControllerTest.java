package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PunktControllerTest {

  @Mock
  private PunktRepository punktRepository;
  private PunktController underTest;

  @BeforeEach
  void setUp() {
    underTest = new PunktController(punktRepository);
  }

  @Test
  void canGetAllPunkte() {
    //when
    underTest.all();
    //then
    verify(punktRepository).findAll();
  }

  @Test
  void canGetPunktWithId() {
    //given
    long id  = 10;
    given(punktRepository.findById(id)).willReturn(java.util.Optional.of(new Punkt()));
    //when
    underTest.one(id);
    //then
    verify(punktRepository).findById(id);
  }

  @Test
  void punktWithIdDoesntExist() {
    //given
    long id = 10;
    given(punktRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("id not found");
  }

  @Test
  void canAddNewPunkt() {
    //given
    Punkt punkt = new Punkt(9999L, 45.6F, 45.6F, "test@web.de",
            "Ein Punkt");
    //when
    underTest.newPunkt(punkt);
    //then
    ArgumentCaptor<Punkt> punktArgumentCaptor = ArgumentCaptor.forClass(Punkt.class);
    verify(punktRepository).save(punktArgumentCaptor.capture());

    Punkt capturedPunkt = punktArgumentCaptor.getValue();

    assertThat(capturedPunkt).isEqualTo(punkt);
  }

  @Test
  void canEditAController() {
    //given
    long id = 10;
    Punkt punkt = new Punkt(9999L, 45.6F, 45.6F, "test@web.de",
            "Ein Punkt");
    given(punktRepository.findById(id)).willReturn(Optional.of(new Punkt(3L, 5F,
            5F, "test@web.de", "Zweiter Punkt")));
    //when
    underTest.replacePunkt(punkt, id);

    //then
    ArgumentCaptor<Punkt> punktArgumentCaptor = ArgumentCaptor.forClass(Punkt.class);
    verify(punktRepository).save(punktArgumentCaptor.capture());

    Punkt capturedPunkt = punktArgumentCaptor.getValue();

    assertThat(capturedPunkt).isEqualTo(punkt);
  }

  @Test
  void canDeleteAPunkt() {
    //given
    long id = 10;
    //when
    underTest.deletePunkt(id);
    //then
    verify(punktRepository).deleteById(id);
  }
}