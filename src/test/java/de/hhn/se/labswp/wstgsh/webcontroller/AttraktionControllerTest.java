package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
  void one() {
  }

  @Test
  void newAttraktion() {
  }

  @Test
  void replaceAttraktion() {
  }

  @Test
  void deleteAttraktion() {
  }
}