package com.pst.learncrew.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import brave.Tracer;
import com.pst.learncrew.dto.ProfileDto;
import com.pst.learncrew.enums.AgreementTypeEnum;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SuppressWarnings({"unchecked", "rawtypes"})
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DefaultProfilesDaoTest {

  @InjectMocks DefaultProfilesDao profilesDao;

  @Mock private Tracer tracer;

  @Mock NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Test
  void test_getProfiles() {
    List<ProfileDto> result = new ArrayList<>();
    ProfileDto res = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    result.add(res);
    when(namedParameterJdbcTemplate.query(
            Mockito.anyString(), Mockito.anyMap(), Mockito.any(RowMapper.class)))
        .thenReturn(result);
    List<ProfileDto> businessResponseObj =
        profilesDao.getProfiles(Mockito.mock(AgreementTypeEnum.class), false, "");
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_createAgreementProfile() {
    when(namedParameterJdbcTemplate.update(Mockito.anyString(), Mockito.anyMap()))
        .thenReturn(Mockito.anyInt());
    ProfileDto businessResponseObj = profilesDao.createAgreementProfile(Mockito.any());
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_getProfileById() {
    ProfileDto result = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    when(namedParameterJdbcTemplate.queryForObject(
            Mockito.anyString(), Mockito.anyMap(), Mockito.any(RowMapper.class)))
        .thenReturn(result);
    ProfileDto businessResponseObj = profilesDao.getProfileById(1);
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_updateProfile() {
    when(namedParameterJdbcTemplate.update(Mockito.anyString(), Mockito.anyMap()))
        .thenReturn(Mockito.anyInt());
    ProfileDto businessResponseObj = profilesDao.updateProfile(1, Mockito.any());
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_deleteProfile() {
    when(namedParameterJdbcTemplate.update(Mockito.anyString(), Mockito.anyMap())).thenReturn(1);
    profilesDao.deleteProfile(1);
    assertDoesNotThrow(() -> {});
  }
}
