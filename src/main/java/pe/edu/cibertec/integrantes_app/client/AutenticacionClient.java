package pe.edu.cibertec.integrantes_app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.integrantes_app.dto.LoginRequestDTO;
import pe.edu.cibertec.integrantes_app.dto.LoginResponseDTO;

@FeignClient(name="autenticarFeing", url="http://localhost:8080/autenticacion")
public interface AutenticacionClient {

    @PostMapping("/iniciar-sesion")
    ResponseEntity<LoginResponseDTO> iniciarSesion(@RequestBody LoginRequestDTO loginRequestDTO);

}
