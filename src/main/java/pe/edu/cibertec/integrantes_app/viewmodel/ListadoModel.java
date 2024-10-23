package pe.edu.cibertec.integrantes_app.viewmodel;

import pe.edu.cibertec.integrantes_app.entity.Integrante;

import java.util.List;

public record ListadoModel(String codigo, String mensaje, List<Integrante> integrantes) {
}
