package br.com.qualitsys.listcursos.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qualitsys.listcursos.controller.dto.CursoDto;
import br.com.qualitsys.model.Curso;
@RestController	
public class ListCursosController {
	
	@RequestMapping("/")
	public List<CursoDto> listaCursos() {
		
		Curso curso1 = new Curso ( 	1,
									"Sistemas de Informação",
									"2020-07-17 09:20:39",
									"2020-07-17 09:20:39");
		Curso curso2 = new Curso ( 	1,
				"Gestão de Tecnologia da Informação",
				"2020-07-17 09:21:27",
				"2020-07-17 09:21:27");
		return converter(Arrays.asList(curso1,curso2));
	}
	//metodo que recebe a lista de cursos e retorna lista de cursoDto
		public static List<CursoDto> converter(List<Curso> listaCursos)  {
		List<CursoDto> listaCursosDto = new ArrayList<CursoDto>(); 
		int n = listaCursos.size();
		for (int i = 0; i < n; i++) {
			CursoDto c = new CursoDto(listaCursos.get(i).getIdCurso(), listaCursos.get(i).getNomeCurso()); 
			listaCursosDto.add(c); 
		}
		return listaCursosDto;
	}
}





