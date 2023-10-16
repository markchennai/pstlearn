package com.pst.learncrew.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import brave.Tracer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pst.learncrew.business.ProfilesBusiness;
import com.pst.learncrew.models.Profile;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest()
class ProfilesControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean ProfilesBusiness profilesBusiness;

  @MockBean private Tracer tracer;
  @MockBean NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Test
  void getProfilesSuccessTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            get("http://localhost:8080/crewpro/timekeeping/guarantee/profiles?agreementType= &excludeRestDaysFlag=false&commentId=1")
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.OK.value()));
  }

  @Test
  void getProfilesFailureTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            get("/crewpro/timekeeping/guarantee/profiles//")
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
  }

  @Test
  void createAgreementProfileSuccessTest() throws Exception {
    Profile mock = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            post("http://localhost:8080/crewpro/timekeeping/guarantee/profiles")
                .content(asJsonString(mock))
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  void createAgreementProfileFailureTest() throws Exception {
    Profile mock = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            post("/crewpro/timekeeping/guarantee/profiles//")
                .content(asJsonString(mock))
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
  }

  @Test
  void getProfileByIdSuccessTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            get("http://localhost:8080/crewpro/timekeeping/guarantee/profiles/{oId}", 1)
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.OK.value()));
  }

  @Test
  void getProfileByIdFailureTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            get("/crewpro/timekeeping/guarantee/profiles/{oId}//", 1)
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
  }

  @Test
  void updateProfileSuccessTest() throws Exception {
    Profile mock = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            put("http://localhost:8080/crewpro/timekeeping/guarantee/profiles/{oId}", 1)
                .content(asJsonString(mock))
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.OK.value()));
  }

  @Test
  void updateProfileFailureTest() throws Exception {
    Profile mock = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            put("/crewpro/timekeeping/guarantee/profiles/{oId}//", 1)
                .content(asJsonString(mock))
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
  }

  @Test
  void deleteProfileSuccessTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            delete("http://localhost:8080/crewpro/timekeeping/guarantee/profiles/{oId}", 1)
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
  }

  @Test
  void deleteProfileFailureTest() throws Exception {

    Map<String, String> headers = new HashMap<String, String>();
    mockMvc
        .perform(
            delete("/crewpro/timekeeping/guarantee/profiles/{oId}//", 1)
                .header("key", headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
