package com.construtech.buildsphere.platform.profiles.domain.model.queries;

import com.construtech.buildsphere.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
