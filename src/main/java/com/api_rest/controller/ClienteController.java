package com.api_rest.controller;

import com.api_rest.model.dto.ClienteDTO;
import com.api_rest.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes(){
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ClienteDTO> consultarClientePorId(@PathVariable("id") Long id){
        ClienteDTO cliente = clienteService.findById(id);
        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody @Valid ClienteDTO cliente){
        ClienteDTO createdCliente = clienteService.save(cliente);
        if (createdCliente == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO){
        ClienteDTO updatedCliente = clienteService.updateById(id, clienteDTO);
        if (updatedCliente == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<ClienteDTO> borrarCliente(@PathVariable("id") Long id) {
        ClienteDTO deletedCliente = clienteService.deleteById(id);
        if (deletedCliente == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(deletedCliente, HttpStatus.OK);
    }
}
