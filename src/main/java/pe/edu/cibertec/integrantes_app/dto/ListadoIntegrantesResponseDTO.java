package pe.edu.cibertec.integrantes_app.dto;

import pe.edu.cibertec.integrantes_app.entity.Integrante;

import java.util.List;

public record ListadoIntegrantesResponseDTO(String codigo, String mensaje, List<Integrante> integrantes) {
}
