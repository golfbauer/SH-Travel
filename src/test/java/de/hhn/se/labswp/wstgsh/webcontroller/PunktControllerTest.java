package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
  void newPunkt() {
  }

  @Test
  void replacePunkt() {
  }

  @Test
  void deleteAttraktion() {
  }
}