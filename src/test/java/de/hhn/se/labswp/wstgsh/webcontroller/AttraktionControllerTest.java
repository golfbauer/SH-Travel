package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AttraktionControllerTest {

  @Mock
  private AttraktionRepository attraktionRepository;
  private AttraktionController underTest;

  @BeforeEach
  void setUp() {
    underTest = new AttraktionController(attraktionRepository);
  }

  @Test
  void canGetAllAttraktionen() {
    //when
    underTest.all();
    //then
    verify(attraktionRepository).findAll();
  }

  @Test
  void canGetAttraktionWithId() {
    long id  = 10;
    given(attraktionRepository.findById(id)).willReturn(java.util.Optional.of(new Attraktion()));
    //when
    underTest.one(id);
    //then
    verify(attraktionRepository).findById(id);
  }

  @Test
  void attraktionWithIdDoesntExist() {
    //given
    long id = 10;
    given(attraktionRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Id not found");
  }

  @Test
  void canAddNewAttraktion() {
    //given
    Set<AttraktionOeffnungszeit> set = new HashSet<>();
    Attraktion attraktion = new Attraktion(9999L, 45.6F, 45.6F,
            "test@web.de", "Eine Attraktion", "Dies ist ein test", set);
    //when
    underTest.newAttraktion(attraktion);
    //then
    ArgumentCaptor<Attraktion> attraktionArgumentCaptor = ArgumentCaptor.forClass(Attraktion.class);
    verify(attraktionRepository).save(attraktionArgumentCaptor.capture());

    Attraktion attraktionCaptured = attraktionArgumentCaptor.getValue();

    assertThat(attraktionCaptured).isEqualTo(attraktion);
  }

  @Test
  void canEditaAttraktion() {
    //given
    long id = 9;
    Set<AttraktionOeffnungszeit> set = new HashSet<>();
    Attraktion attraktion = new Attraktion(9L, 45.6F, 45.6F,
            "test@web.de", "Ein Punkt", "Dies ist ein test", set);
    //when
    underTest.replaceAttraktion(attraktion, id);
    //then
    verify(attraktionRepository).deleteById(id);
    verify(attraktionRepository).save(attraktion);
  }

  @Test
  void willThrowExceptionCauseOfId() {
    //given
    long id = 9;
    Set<AttraktionOeffnungszeit> set = new HashSet<>();
    Attraktion attraktion = new Attraktion(10L, 45.6F, 45.6F,
            "test@web.de", "Ein Punkt", "Dies ist ein test", set);
    //when //then
    assertThatThrownBy(() -> underTest.replaceAttraktion(attraktion, id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("New Attraktion must have same id as old one");

    verify(attraktionRepository, never()).deleteById(any());
    verify(attraktionRepository, never()).save(any());
  }

  @Test
  void canDeleteaAttraktion() {
    //given
    long id = 10;
    //when
    underTest.deleteAttraktion(id);
    //then
    verify(attraktionRepository).deleteById(id);
  }
}