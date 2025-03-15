package decolatech.api.controller;

import decolatech.api.dto.write.UserDTO;
import decolatech.api.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;

    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<Object> findAll() {
        var result = userService.buscarUsuarios();
        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuário por ID")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        var result = userService.buscarUsuarioporId(id);
        return ResponseEntity.ok(result);

    }


    @PostMapping()
    @Operation(summary = "Adicionar usuário")
    public ResponseEntity<Object> create(UserDTO userDTO) {
        var result = userService.CriarUsuario(userDTO);
        return new ResponseEntity<Object>(result,HttpStatus.CREATED);


    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados pelo ID")
    public ResponseEntity<Object> update(@PathVariable Long id) {
        // var updatedUser = userService.update(id, userToUpdate);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usúario pelo ID")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var result = userService.DeletarUsuarioporId(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}