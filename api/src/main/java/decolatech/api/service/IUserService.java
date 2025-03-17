package decolatech.api.service;

import decolatech.api.dto.read.UserDTO;
import java.util.List;

public interface IUserService {
    List<UserDTO> buscarUsuarios();
    UserDTO buscarUsuarioporId(Long id);
    Object  DeletarUsuarioporId(Long id);
    Object  CriarUsuario(decolatech.api.dto.write.UserDTO userDTO);
    Object  AtualizarUsuario(decolatech.api.dto.write.UserDTO userDTO , Long id);
}
