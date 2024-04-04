package ru.kpfu.itis.paramonov.mappers;

import ru.kpfu.itis.paramonov.dto.RoleDto;
import ru.kpfu.itis.paramonov.model.Role;

public class RoleModelMapper implements ModelMapper<Role, RoleDto> {
    @Override
    public RoleDto fromModel(Role model) {
        return new RoleDto(model.getName());
    }
}
