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
		name = "Pesquisar.do", 
		urlPatterns = { 
				"/Pesquisar.do" 
		})
public class Pesquisar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Pesquisar() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter saida = response.getWriter();
		
		String codigo = request.getParameter("codigo");
		
		if(codigo.equals("")) {
			saida.print("<p><a href='index.html'>Volte</a> e informe a matrícula!</p>");
		}
		else {
			int codigoInt = Integer.parseInt(codigo);
			ProdutoService ps = new ProdutoService();
			Produto p = new Produto();
			p = ps.consultar(codigoInt);
			if(p.getCodigo() == 0) {
				saida.print("<p>Produto não encontrado!</p> <a href='pesquisar.html'>Voltar</a>");
			}
			else {
				saida.print("<p>Produto encontrado! <a href='pesquisar.html'>Voltar</a> </p>");
				saida.print("<table border='2' cellpadding='5'>");
				saida.print("<tr>");
				saida.print("<th>Código</th>");
				saida.print("<th>Nome</th>");
				saida.print("<th>Descrição</th>");
				saida.print("<th>Valor</th>");
				saida.print("<th>Estoque</th>");
				saida.print("</tr>");
				saida.print("<tr>");
				saida.print("<td>" + p.getCodigo() + "</td>");
				saida.print("<td>" + p.getNome() + "</td>");
				saida.print("<td>" + p.getDescricao() + "</td>");
				saida.print("<td>" + p.getValor() + "</td>");
				saida.print("<td>" + p.getEstoque() + "</td>");
				saida.print("</tr>");
				saida.print("</table>");
			}
		}
	}
}
