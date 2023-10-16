package com.pst.learncrew.dao;

import com.pst.learncrew.dto.ProfileDto;
import com.pst.learncrew.enums.AgreementTypeEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultProfilesDao implements ProfilesDao {
  private static final String GET_PROFILES = "select * from profiles";
  private static final String CREATE_AGREEMENT_PROFILE = "<----- PLACE YOUR SQL QUERY HERE ----->";
  private static final String GET_PROFILE_BY_ID = "<----- PLACE YOUR SQL QUERY HERE ----->";
  private static final String UPDATE_PROFILE = "<----- PLACE YOUR SQL QUERY HERE ----->";
  private static final String DELETE_PROFILE = "<----- PLACE YOUR SQL QUERY HERE ----->";

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public List<ProfileDto> getProfiles(
      AgreementTypeEnum agreementType, Boolean excludeRestDaysFlag, String commentId) {
    Map<String, Object> parameters = new HashMap<>();
    return namedParameterJdbcTemplate.query(GET_PROFILES, parameters, new ProfilesRowMapper());
  }

  @Override
  public ProfileDto createAgreementProfile(ProfileDto profileDto) {
    Map<String, Object> parameters = new HashMap<>();
    namedParameterJdbcTemplate.update(CREATE_AGREEMENT_PROFILE, parameters);
    // TODO- replace with right mapping logic below
    ProfileDto result = new ProfileDto();
    return result;
  }

  @Override
  public ProfileDto getProfileById(Integer oId) {
    Map<String, Object> parameters = new HashMap<>();
    return namedParameterJdbcTemplate.queryForObject(
        GET_PROFILE_BY_ID, parameters, new ProfilesRowMapper());
  }

  @Override
  public ProfileDto updateProfile(Integer oId, ProfileDto profileDto) {
    Map<String, Object> parameters = new HashMap<>();
    namedParameterJdbcTemplate.update(UPDATE_PROFILE, parameters);
    // TODO- replace with right mapping logic below
    ProfileDto result = new ProfileDto();
    return result;
  }

  @Override
  public void deleteProfile(Integer oId) {
    Map<String, Object> parameters = new HashMap<>();
    namedParameterJdbcTemplate.update(DELETE_PROFILE, parameters);
  }

  private static class ProfilesRowMapper implements RowMapper<ProfileDto> {
    @Override
    public ProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      ProfileDto profileDto = new ProfileDto();

      return profileDto;
    }
  }
}
