package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import service.ProdutoService;

@WebServlet(
		name = "Excluir.do", 
		urlPatterns = { 
				"/Excluir.do" 
		})
public class Excluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Excluir() {
        super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter saida = response.getWriter();
		
		String codigo = request.getParameter("codigo");
		
		if(codigo.equals("")) {
			saida.print("<p><a href='Listar.do'>Volte</a> e selecione um registro para excluir!</p>");
		}
		else {
			int codigoInt = Integer.parseInt(codigo);
			ProdutoService ps = new ProdutoService();
			Produto p = ps.consultar(codigoInt);
			if(p.getCodigo() == 0) {
				saida.print("<p>Produto não encontrado! <a href='Listar.do'>Volte</a> e selecione um registro para excluir!</p>");
			}
			else {
				ps.excluir(p);
				saida.print("<p>Produto deletado!</p> <a href='Listar.do'>Voltar</a>");
			}
		}
	}
}