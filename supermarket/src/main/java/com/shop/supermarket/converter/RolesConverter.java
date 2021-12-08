package com.shop.supermarket.converter;

import com.shop.supermarket.dto.RolesDTO;
import com.shop.supermarket.entity.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RolesConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RolesDTO entityToDto(Roles roles)
    {
        return modelMapper.map(roles,RolesDTO.class);
    }

    public List<RolesDTO> entityToDto(List<Roles> roles)
    {
        return roles.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Roles dtoToEntity(RolesDTO rolesDTO)
    {
        return modelMapper.map(rolesDTO,Roles.class);
    }

    public List<Roles> dtoToEntity(List<RolesDTO> rolesDTOS)
    {
        return rolesDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
