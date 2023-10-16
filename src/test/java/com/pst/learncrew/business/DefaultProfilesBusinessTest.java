package com.pst.learncrew.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import brave.Tracer;
import com.pst.learncrew.dao.ProfilesDao;
import com.pst.learncrew.dto.ProfileDto;
import com.pst.learncrew.enums.AgreementTypeEnum;
import com.pst.learncrew.mapper.ProfilesMapper;
import com.pst.learncrew.models.Profile;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DefaultProfilesBusinessTest {

  @InjectMocks DefaultProfilesBusiness profilesBusiness;
  @Mock private ProfilesDao profilesDao;

  @Spy private ProfilesMapper profilesMapper = Mappers.getMapper(ProfilesMapper.class);

  @Mock NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  @Mock private Tracer tracer;

  @Test
  void test_getProfiles() {
    List<ProfileDto> result = new ArrayList<>();
    ProfileDto res = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    result.add(res);
    List<Profile> modelResult = new ArrayList<>();
    Profile modelRes = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    modelResult.add(modelRes);
    when(profilesMapper.mapProfileEntityListToBeans(Mockito.anyList())).thenReturn(modelResult);
    when(profilesMapper.mapProfileBeansToEntityList(Mockito.anyList())).thenReturn(result);
    when(profilesDao.getProfiles(Mockito.mock(AgreementTypeEnum.class), false, ""))
        .thenReturn(result);
    List<Profile> businessResponseObj =
        profilesBusiness.getProfiles(Mockito.mock(AgreementTypeEnum.class), false, "");
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_createAgreementProfile() {
    ProfileDto result = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    Profile modelResult = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    when(profilesMapper.mapProfileEntityToBean(Mockito.any())).thenReturn(modelResult);
    when(profilesMapper.mapProfileBeanToEntity(Mockito.any())).thenReturn(result);
    when(profilesDao.createAgreementProfile(Mockito.mock(ProfileDto.class))).thenReturn(result);
    Profile businessResponseObj =
        profilesBusiness.createAgreementProfile(Mockito.mock(Profile.class));
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_getProfileById() {
    ProfileDto result = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    Profile modelResult = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    when(profilesMapper.mapProfileEntityToBean(Mockito.any())).thenReturn(modelResult);
    when(profilesMapper.mapProfileBeanToEntity(Mockito.any())).thenReturn(result);
    when(profilesDao.getProfileById(1)).thenReturn(result);
    Profile businessResponseObj = profilesBusiness.getProfileById(1);
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_updateProfile() {
    ProfileDto result = Mockito.mock(ProfileDto.class, Mockito.RETURNS_MOCKS);
    Profile modelResult = Mockito.mock(Profile.class, Mockito.RETURNS_MOCKS);
    when(profilesMapper.mapProfileEntityToBean(Mockito.any())).thenReturn(modelResult);
    when(profilesMapper.mapProfileBeanToEntity(Mockito.any())).thenReturn(result);
    when(profilesDao.updateProfile(1, Mockito.mock(ProfileDto.class))).thenReturn(result);
    Profile businessResponseObj = profilesBusiness.updateProfile(1, Mockito.mock(Profile.class));
    assertNotNull(businessResponseObj);
  }

  @Test
  void test_deleteProfile() {
    profilesBusiness.deleteProfile(1);
    assertDoesNotThrow(() -> {});
  }
}
