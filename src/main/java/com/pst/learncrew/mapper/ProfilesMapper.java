package com.pst.learncrew.mapper;

import com.pst.learncrew.dto.ProfileDto;
import com.pst.learncrew.models.Profile;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfilesMapper {

  ProfilesMapper INSTANCE = Mappers.getMapper(ProfilesMapper.class);

  Profile mapProfileEntityToBean(ProfileDto profileDto);

  ProfileDto mapProfileBeanToEntity(Profile profile);

  List<Profile> mapProfileEntityListToBeans(List<ProfileDto> profileDtos);

  List<ProfileDto> mapProfileBeansToEntityList(List<Profile> profiles);
}
