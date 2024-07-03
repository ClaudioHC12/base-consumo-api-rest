package com.api_rest.service.impl;

import com.api_rest.model.dto.ClienteDTO;
import com.api_rest.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Value("${api-rest.url}")
    private String API_URL;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<ClienteDTO> findAll() {
        ResponseEntity<ClienteDTO[]> response = restTemplate.getForEntity(
                API_URL + "/clientes",
                ClienteDTO[].class
        );
        ClienteDTO[] clientes = response.getBody();
        List<ClienteDTO> listaClientes = Arrays.asList(clientes);
        return listaClientes;
    }

    @Override
    public ClienteDTO save(ClienteDTO cliente) {
        String URI = String.format("%s/clientes", API_URL);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<ClienteDTO> requestEntity = new HttpEntity<>(cliente, headers);

            ResponseEntity<ClienteDTO> response = restTemplate.exchange(
                    URI,
                    HttpMethod.POST,
                    requestEntity,
                    ClienteDTO.class
            );
            return response.getBody();
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public ClienteDTO findById(Long id) {
        String URI = String.format("%s/clientes/%d", API_URL, id);
        try {
            ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(
                    URI,
                    ClienteDTO.class
            );
            ClienteDTO cliente = response.getBody();
            return cliente;
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public ClienteDTO deleteById(Long id) {
        String URI = String.format("%s/clientes/%d", API_URL, id);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ClienteDTO> response = restTemplate.exchange(
                    URI,
                    HttpMethod.DELETE,
                    requestEntity,
                    ClienteDTO.class
            );
            if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT) {
                return response.getBody();
            } else {
                log.error("Failed to delete client with id {}: HTTP {}", id, response.getStatusCode());
            }
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public ClienteDTO updateById(Long id, ClienteDTO cliente) {
        String URI = String.format("%s/clientes/%d", API_URL, id);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<ClienteDTO> requestEntity = new HttpEntity<>(cliente, headers);

            ResponseEntity<ClienteDTO> response = restTemplate.exchange(
                    URI,
                    HttpMethod.PUT,
                    requestEntity,
                    ClienteDTO.class
            );
            return response.getBody();
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }
}
