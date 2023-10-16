package com.pst.learncrew.business;

import com.pst.learncrew.dao.ProfilesDao;
import com.pst.learncrew.enums.AgreementTypeEnum;
import com.pst.learncrew.mapper.ProfilesMapper;
import com.pst.learncrew.models.Profile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProfilesBusiness implements ProfilesBusiness {
  @Autowired private ProfilesDao profilesDao;
  @Autowired private ProfilesMapper profilesMapper;

  @Override
  public List<Profile> getProfiles(
      AgreementTypeEnum agreementType, Boolean excludeRestDaysFlag, String commentId) {
    return profilesMapper.mapProfileEntityListToBeans(
        profilesDao.getProfiles(agreementType, excludeRestDaysFlag, commentId));
  }

  @Override
  public Profile createAgreementProfile(Profile profile) {
    return profilesMapper.mapProfileEntityToBean(
        profilesDao.createAgreementProfile(profilesMapper.mapProfileBeanToEntity(profile)));
  }

  @Override
  public Profile getProfileById(Integer oId) {
    return profilesMapper.mapProfileEntityToBean(profilesDao.getProfileById(oId));
  }

  @Override
  public Profile updateProfile(Integer oId, Profile profile) {
    return profilesMapper.mapProfileEntityToBean(
        profilesDao.updateProfile(oId, profilesMapper.mapProfileBeanToEntity(profile)));
  }

  @Override
  public void deleteProfile(Integer oId) {
    profilesDao.deleteProfile(oId);
  }
}
