package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
