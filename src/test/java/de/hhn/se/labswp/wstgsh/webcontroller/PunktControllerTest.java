package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            .hasMessageContaining("Id not found");
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
  void canEditaPunkt() {
    //given
    long id = 9;
    Punkt punkt = new Punkt(9L, 45.6F, 45.6F, "test@web.de",
            "Ein Punkt");
    //when
    underTest.replacePunkt(punkt, id);
    //then
    verify(punktRepository).deleteById(id);
    verify(punktRepository).save(punkt);
  }

  @Test
  void willThrowExceptionCauseOfId() {
    //given
    long id = 9;
    Punkt punkt = new Punkt(10L, 45.6F, 45.6F, "test@web.de",
            "Ein Punkt");
    //when //then
    assertThatThrownBy(() -> underTest.replacePunkt(punkt, id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("New Punkt must have same id as old one");

    verify(punktRepository, never()).deleteById(any());
    verify(punktRepository, never()).save(any());
  }

  @Test
  void canDeleteaPunkt() {
    //given
    long id = 10;
    //when
    underTest.deletePunkt(id);
    //then
    verify(punktRepository).deleteById(id);
  }
}