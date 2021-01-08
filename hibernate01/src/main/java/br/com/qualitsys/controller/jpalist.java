package br.com.qualitsys.controller;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.qualitsys.model.Aluno;
import br.com.qualitsys.util.HibernateUtil;

@WebServlet("/jpalist")
public class jpalist extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public jpalist() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Transaction transaction = null;

		try  {

			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			List<Aluno> listaAlunosAtivos = session.createQuery("from Aluno a order by a.nome", Aluno.class).list();
			
			List<String> listaNomesAlunosAtivos = new ArrayList<String>(); 
			List<String> listaRAAlunosAtivos = new ArrayList<String>(); 
			
			int size = listaAlunosAtivos.size();
			 
			for (int i = 0 ; i < size ; i++) {
				listaNomesAlunosAtivos.add(listaAlunosAtivos.get(i).getNome());
				listaRAAlunosAtivos.add(listaAlunosAtivos.get(i).getRA());
			}
		 
			request.setAttribute("listaNomesAlunosAtivos", listaNomesAlunosAtivos);
			request.setAttribute("listaRAAlunosAtivos", listaRAAlunosAtivos);
				
			getServletContext().getRequestDispatcher("/jsp02.jsp").forward(request, response);
		
			session.close();
				
		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
