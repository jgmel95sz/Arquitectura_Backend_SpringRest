package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import com.erpsistema.backend.apirest.models.entity.PerfilUsuario;
import com.erpsistema.backend.apirest.models.entity.Role;
import com.erpsistema.backend.apirest.models.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.enabled = 1")
	public List<Usuario> findByEnabledPaged();
		
	@Query("select u from Usuario u where u.enabled = 1")
	public Page<Usuario> findByEnabledPaged(Pageable pageable);
	
	public Usuario findById(Integer id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Integer id);
	
	public Role findRoleById(Integer id);
	
	public PerfilUsuario findPerfilUsuarioById(Integer id);
	
	public Role save(Role role);
	
	public PerfilUsuario save(PerfilUsuario perfilUsuario);
	
	public void deleteRoleById(Integer id);
	
	public void deletePerfilUsuarioById(Integer id);

	/*public Role getRoleById(Long id);*/
}
