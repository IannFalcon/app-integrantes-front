package pe.edu.cibertec.integrantes_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.integrantes_app.client.AutenticacionClient;
import pe.edu.cibertec.integrantes_app.dto.LoginRequestDTO;
import pe.edu.cibertec.integrantes_app.dto.LoginResponseDTO;
import pe.edu.cibertec.integrantes_app.viewmodel.LoginModel;

@Controller
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionClient autenticacionClient;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("00", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";
    }

    @PostMapping("/iniciar-sesion-feign")
    public String iniciarSesionFeign(@RequestParam("codigo") String codigo,
                                     @RequestParam("password") String password,
                                     Model model) {

        // Validar los campos
        if (codigo == null || codigo.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {

            LoginModel loginModel = new LoginModel("01", "Error: Debe llenar el formulario con sus credenciales");
            model.addAttribute("loginModel", loginModel);
            return "inicio";

        }

        // Realizar la solicitud
        try {

            // Preparamos la solicitud
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO(codigo, password);

            // Realizamos la solicitud
            ResponseEntity<LoginResponseDTO> responseEntity = autenticacionClient.iniciarSesion(loginRequestDTO);

            // Si la respuesta del servicio es exitosa
            if (responseEntity.getStatusCode().is2xxSuccessful()) {

                // Recuperamos el cuerpo de la respuesta
                LoginResponseDTO loginResponseDTO = responseEntity.getBody();

                // Si la respuesta no es null y el codigo es 00
                if (loginResponseDTO != null && loginResponseDTO.codigo().equals("00")) {

                    LoginModel loginModel = new LoginModel("00", "Bienvenido: " + loginResponseDTO.nombre() + " " + loginResponseDTO.apellido());
                    model.addAttribute("loginModel", loginModel);
                    return "redirect:/integrante/listar";

                } else {

                    LoginModel loginModel = new LoginModel("02", "Error: Credenciales incorrectas");
                    model.addAttribute("loginModel", loginModel);
                    return "inicio";

                }

            } else {

                    LoginModel loginModel = new LoginModel("99", "Error: Ocurrio un error desconocido al intentar iniciar sesion");
                    model.addAttribute("loginModel", loginModel);
                    return "inicio";

            }


        } catch (Exception e) {

            LoginModel loginModel = new LoginModel("99", "Error: Ocurrio un error desconocido al intentar iniciar sesion");
            model.addAttribute("loginModel", loginModel);
            return "inicio";

        }

    }

    @PostMapping("/cerrar-sesion")
    public String cerrarSesion(Model model) {

        LoginModel loginModel = new LoginModel("00", "");
        model.addAttribute("loginModel", loginModel);
        return "redirect:/autenticacion/inicio";

    }

}
