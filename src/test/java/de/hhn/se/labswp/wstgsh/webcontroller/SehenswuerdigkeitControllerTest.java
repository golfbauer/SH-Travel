package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;

import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
class SehenswuerdigkeitControllerTest {

  @Mock
  private SehenswuerdigkeitRepository sehenswuerdigkeitRepository;
  private SehenswuerdigkeitController underTest;

  @BeforeEach
  void setUp() {
    underTest = new SehenswuerdigkeitController(sehenswuerdigkeitRepository);
  }
  /**
   * Test if Sehenswuerdigkeit gets returned for specific id.
   */
  @Test
  void canGetAllSehenswuerdigkeiten() {
    //when
    underTest.getSehenswuerdigkeiten();
    //then
    verify(sehenswuerdigkeitRepository).findAll();
  }

  @Test
  void canGetSehenswuerdigkeitWithId() {
    //given
    long id  = 10;
    given(sehenswuerdigkeitRepository.findById(id)).willReturn(java.util.Optional.of(new Sehenswuerdigkeit()));
    //when
    underTest.getSehenswuerdigkeit(id);
    //then
    verify(sehenswuerdigkeitRepository).findById(id);
  }
  @Test
  void sehenswuerdigkeitWithIdDoesntExist(){
    //given
    long id = 10;
    given(sehenswuerdigkeitRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.getSehenswuerdigkeit(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("ID not found");
  }
  @Test
  void newSehenswuerdigkeit() {
    //given
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f, "nutzerEmail@mail.com",
            "Sehenswürdig","Eine Sehenswürdige Sehenswürdigkeit");
    //when
    underTest.newSehenswuerdigkeit(temp);
    //then
    verify(sehenswuerdigkeitRepository).save(temp);
  }

  //@Test
 // void editSehenswuerdigkeit() {
    //given
   // Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f, "nutzerEmail@mail.com",
        //    "Sehenswürdig","Eine Sehenswürdige Sehenswürdigkeit");
   // Sehenswuerdigkeit temp2 = new Sehenswuerdigkeit(69L, 90.000f, 75.000f, "nutzerEmail2@mail.com",
    //        "Sehenswürdig2","Noch eine Sehenswürdige Sehenswürdigkeit");
   // underTest.newSehenswuerdigkeit(temp);
    //when
   // underTest.editSehenswuerdigkeit(69L,temp2);
    //then
   // verify(sehenswuerdigkeitRepository).save(temp2);
 // }

  @Test
  void deleteSehenswuerdigkeit() {
    //given
    long id = 10;
    //when
    underTest.deleteSehenswuerdigkeit(id);
    //then
    verify(sehenswuerdigkeitRepository).deleteById(id);
  }
}