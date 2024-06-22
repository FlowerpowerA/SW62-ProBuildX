package com.construtech.buildsphere.platform.profiles.domain.services;

import com.construtech.buildsphere.platform.profiles.domain.model.aggregates.Profile;
import com.construtech.buildsphere.platform.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
