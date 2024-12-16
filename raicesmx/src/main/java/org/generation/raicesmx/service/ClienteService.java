package org.generation.raicesmx.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.generation.raicesmx.model.ClienteEntity;
import org.generation.raicesmx.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	// Implementa los repositorios necesarios para el proyecto. Ejemplos: UsuarioRepository, ProductoRepository y/o ServicioRepository,  que se conectan con la base de datos usando los modelos/entidades.
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService (ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public List<ClienteEntity> getAllCliente(){
        return this.clienteRepository.findAll();
    }
	
	public ClienteEntity getCliente(Long id_cliente){
	    return clienteRepository.findById(id_cliente).orElse(null);
	}

    public ClienteEntity createCliente (ClienteEntity newCliente) {
        return this.clienteRepository.save(newCliente);
    }
    
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    
    public ClienteEntity updateCliente(ClienteEntity clienteEntity) {
        Optional < ClienteEntity > clienteDb = this.clienteRepository.findById(clienteEntity.getId_clientes());

        if (clienteDb.isPresent()) {
            ClienteEntity clienteUpdate = clienteDb.get();
            clienteUpdate.setId_clientes(clienteEntity.getId_clientes());
            clienteUpdate.setNombre(clienteEntity.getNombre());
            clienteUpdate.setApellido( clienteEntity.getApellido() );
            clienteUpdate.setCorreo( clienteEntity.getCorreo() );
            clienteUpdate.setContrasena( clienteEntity.getContrasena() );
            clienteUpdate.setDireccion( clienteEntity.getDireccion() );
            clienteUpdate.setTelefono( clienteEntity.getTelefono() );
            clienteUpdate.setCodigo_postal( clienteEntity.getCodigo_postal() );
            clienteUpdate.setEstado( clienteEntity.getEstado() );
            clienteUpdate.setTipo_usuario( clienteEntity.getTipo_usuario() );
            clienteRepository.save(clienteUpdate);
            return clienteUpdate;
        } else {
            throw new NoSuchElementException("No se encontró al cliente con ID: " + clienteEntity.getId_clientes());
        }
    }
}