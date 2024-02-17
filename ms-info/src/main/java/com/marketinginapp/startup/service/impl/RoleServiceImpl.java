package com.marketinginapp.startup.service.impl;

import com.marketinginapp.startup.api.dto.request.RoleRequest;
import com.marketinginapp.startup.api.dto.response.RoleResponse;
import com.marketinginapp.startup.domain.entity.Role;
import com.marketinginapp.startup.domain.repository.RoleRepository;
import com.marketinginapp.startup.handler.exception.DuplicatedKeyException;
import com.marketinginapp.startup.handler.exception.MessageException;
import com.marketinginapp.startup.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.service.RoleService;
import com.marketinginapp.startup.utils.Constant;
import com.marketinginapp.startup.utils.enums.ConverterEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public RoleResponse save(RoleRequest request) {
        var entity = new Role();
        try{
            Optional<Role> optionalRole = roleRepository.findByName(ConverterEnum.toERole(request.name()).toString());
            if(optionalRole.isPresent()){
                throw new DuplicatedKeyException(String.format(Constant.EXCEPTION_ROLE_ALREADY_REGISTERED, request.name()));
            }
            entity = roleRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
        return toResponse(entity);
    }
    @Transactional(readOnly = true)
    @Override
    public RoleResponse findById(String id) {
        var entity = roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(String.format(Constant.EXCEPTION_ROLE_NOT_FOUND_BY_ID, id))
        );
        return toResponse(entity);
    }
    @Transactional(readOnly = true)
    @Override
    public List<RoleResponse> findAll() {
        List<RoleResponse> list = new ArrayList<>();
        roleRepository.findAll().forEach(role -> {
            list.add(toResponse(role));
        });
        return list;
    }
    @Transactional
    @Override
    public RoleResponse update(String id, RoleRequest request) {
        var entity = roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(String.format(Constant.EXCEPTION_ROLE_NOT_FOUND_BY_ID, id))
        );
        entity.setName(request.name());
        var role = new Role();
        try{
            entity = roleRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ERROR_WHEN_UPDATING_ROLE, exception.getMessage()));
        }
        return toResponse(entity);
    }
    private Role toEntity(RoleRequest request){
        return Role.builder().name(ConverterEnum.toERole(request.name()).toString()).build();
    }
    private RoleResponse toResponse(Role entity){
        return new RoleResponse(entity.getId(), entity.getName().toLowerCase());
    }
}
