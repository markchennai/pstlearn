package com.pst.learncrew.business;

import com.pst.learncrew.enums.AgreementTypeEnum;
import com.pst.learncrew.models.Profile;
import java.util.List;

public interface ProfilesBusiness {

  public List<Profile> getProfiles(
      AgreementTypeEnum agreementType, Boolean excludeRestDaysFlag, String commentId);

  public Profile createAgreementProfile(Profile profile);

  public Profile getProfileById(Integer oId);

  public Profile updateProfile(Integer oId, Profile profile);

  public void deleteProfile(Integer oId);
}
