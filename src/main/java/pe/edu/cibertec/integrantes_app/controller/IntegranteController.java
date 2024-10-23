package pe.edu.cibertec.integrantes_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.integrantes_app.dto.ListadoIntegrantesResponseDTO;
import pe.edu.cibertec.integrantes_app.viewmodel.ListadoModel;

@Controller
@RequestMapping("/integrante")
public class IntegranteController {

    @Autowired
    RestTemplate restTemplateIntegrante;

    @GetMapping("/listar")
    public String listarIntegrantes(Model model) {

        try {

            // Llamada al backend para obtener los integrantes
            ListadoIntegrantesResponseDTO response = restTemplateIntegrante.getForObject("/listar", ListadoIntegrantesResponseDTO.class);

            if (response != null && response.codigo().equals("00")) {

                model.addAttribute("listadoModel", new ListadoModel("00", "", response.integrantes()));

            } else {

                model.addAttribute("listadoModel", new ListadoModel("01", "Error: No se encontraron integrantes", null));

            }

        } catch (Exception e) {

            model.addAttribute("listadoModel", new ListadoModel("99", "Error: Ocurrió un problema", null));

        }

        return "principal";

    }

}
