package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.mockito.Mockito.when;

import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = SehenswuerdigkeitController.class)
class SehenswuerdigkeitControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SehenswuerdigkeitController sehenswuerdigkeitController;

  Sehenswuerdigkeit mockSehen = new Sehenswuerdigkeit(
          568L, 11.1346f, 54.4739f, "Henri@gmx.de",
          "Fehmarn", "Eine Insel ion der Nähe zu Dänemark"
          );

  /**
   * Test if Sehenswuerdigkeit gets returned for specific id.
   * @throws Exception Thrown for the Mvc object.
   */
  @Test
  void getSehenswuerdigkeiten() throws Exception {
    when(sehenswuerdigkeitController.getSehenswuerdigkeit(Mockito.anyLong())).thenReturn(mockSehen);
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "http://localhost:8080/sehenswuerdigket/get/567").accept(
            MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String expected = "{\"id\":568,\"laengengrad\":11.1346,\"breitengrad\":54.4739,"
            + "\"nutzerEmail\":\"Henri@gmx.de\",\"name\":\"Fehmarn\","
            + "\"beschreibung\":\"Eine Insel ion der NÃ¤he zu DÃ¤nemark\"}";
    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

  }

  @Test
  void getSehenswuerdigkeit() {
  }

  @Test
  void newSehenswuerdigkeit() {
  }

  @Test
  void editSehenswuerdigkeit() {
  }

  @Test
  void deleteSehenswuerdigkeit() {
  }
}