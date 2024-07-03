package com.api_rest.service;

import com.api_rest.model.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO save(ClienteDTO cliente);
    ClienteDTO findById(Long id);
    ClienteDTO deleteById(Long id);
    ClienteDTO updateById(Long id, ClienteDTO cliente);
}
