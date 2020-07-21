package br.com.qualitsys.scpe.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qualitsys.scpe.controller.dto.CursoDto;
import br.com.qualitsys.scpe.model.Curso;
import br.com.qualitsys.scpe.repository.CursoRepository;


@RestController
public class CursosController {
	
	@Autowired
	private CursoRepository cursoRepository;	
	
	@GetMapping("/cursos")
	public List<CursoDto> listaCursos() { 
		List<Curso> cursos = cursoRepository.findAll();
		return converter(cursos);
	}

	//metodo que recebe lista de cursos e retorna lista de cursoDto
	public static List<CursoDto> converter(List<Curso> listaCursos ) {

		List<CursoDto> listaCursosDto = new ArrayList<CursoDto>(); 
		int n = listaCursos.size(); 

		for (int i = 0; i < n; i++) { 
			CursoDto c = new CursoDto(listaCursos.get(i).getIdCurso(), listaCursos.get(i).getNomeCurso()); 
			listaCursosDto.add(c); 
		}
		return listaCursosDto;
	}
} 

