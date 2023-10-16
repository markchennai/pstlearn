package com.pst.learncrew.mapper;

import com.pst.learncrew.dto.ProfileDto;
import com.pst.learncrew.models.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-16T14:08:39-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProfilesMapperImpl implements ProfilesMapper {

    @Override
    public Profile mapProfileEntityToBean(ProfileDto profileDto) {
        if ( profileDto == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setOId( profileDto.getOId() );
        profile.setProfileId( profileDto.getProfileId() );
        profile.setDescription( profileDto.getDescription() );
        profile.setCreatedTimestamp( profileDto.getCreatedTimestamp() );
        profile.setCreatedUserId( profileDto.getCreatedUserId() );
        profile.setLastUpdatedTimestamp( profileDto.getLastUpdatedTimestamp() );
        profile.setLastUpdatedUserId( profileDto.getLastUpdatedUserId() );

        return profile;
    }

    @Override
    public ProfileDto mapProfileBeanToEntity(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        profileDto.setOId( profile.getOId() );
        profileDto.setProfileId( profile.getProfileId() );
        profileDto.setDescription( profile.getDescription() );
        profileDto.setCreatedTimestamp( profile.getCreatedTimestamp() );
        profileDto.setCreatedUserId( profile.getCreatedUserId() );
        profileDto.setLastUpdatedTimestamp( profile.getLastUpdatedTimestamp() );
        profileDto.setLastUpdatedUserId( profile.getLastUpdatedUserId() );

        return profileDto;
    }

    @Override
    public List<Profile> mapProfileEntityListToBeans(List<ProfileDto> profileDtos) {
        if ( profileDtos == null ) {
            return null;
        }

        List<Profile> list = new ArrayList<Profile>( profileDtos.size() );
        for ( ProfileDto profileDto : profileDtos ) {
            list.add( mapProfileEntityToBean( profileDto ) );
        }

        return list;
    }

    @Override
    public List<ProfileDto> mapProfileBeansToEntityList(List<Profile> profiles) {
        if ( profiles == null ) {
            return null;
        }

        List<ProfileDto> list = new ArrayList<ProfileDto>( profiles.size() );
        for ( Profile profile : profiles ) {
            list.add( mapProfileBeanToEntity( profile ) );
        }

        return list;
    }
}
