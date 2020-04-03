package com.erpsistema.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpsistema.backend.apirest.models.dao.IPerfilUsuario;
import com.erpsistema.backend.apirest.models.dao.IRoleDao;
import com.erpsistema.backend.apirest.models.dao.IUsuarioDao;
import com.erpsistema.backend.apirest.models.entity.PerfilUsuario;
import com.erpsistema.backend.apirest.models.entity.Role;
import com.erpsistema.backend.apirest.models.entity.Usuario;


@Service
public class UsuarioService implements IUsuarioService ,UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IPerfilUsuario perfilUsuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.buscarPorUsuario(username);
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '" + username +"'en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username +"'en el sistema!");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.buscarPorUsuario(username);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByEnabledPaged() {
		return usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		usuarioDao.deleteById(id);
		
	}
	@Override
	public Page<Usuario> findByEnabledPaged(Pageable pageable) {
		return usuarioDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public PerfilUsuario findPerfilUsuarioById(Integer id) {
		return perfilUsuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	@Transactional
	public PerfilUsuario save(PerfilUsuario perfilUsuario) {
		return perfilUsuarioDao.save(perfilUsuario);
	}

	@Override
	@Transactional
	public void deleteRoleById(Integer id) {
		roleDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deletePerfilUsuarioById(Integer id) {
		perfilUsuarioDao.deleteById(id);
		
	}
	
}