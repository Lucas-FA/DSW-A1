package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import service.ProdutoService;

@WebServlet(
		name = "Listar.do", 
		urlPatterns = { 
				"/Listar.do" 
		})
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Listar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter saida = response.getWriter();
		
    	ProdutoService ps = new ProdutoService();
		ArrayList<Produto> lista = new ArrayList<>();
		lista = ps.listar();
		
		saida.print("<link rel='stylesheet' type='text/css' href='estilo.css'>");
		saida.print("<table border='2' cellpadding='5'>");
		saida.print("<tr>");
		saida.print("<th>Código</th>");
		saida.print("<th>Nome</th>");
		saida.print("<th>Descrição</th>");
		saida.print("<th>Valor</th>");
		saida.print("<th>Estoque</th>");
		saida.print("</tr>");
		
		lista.forEach(
			p -> {
				saida.print("<tr>");
				saida.print("<td>" + p.getCodigo() + "</td>");
				saida.print("<td>" + p.getNome() + "</td>");
				saida.print("<td>" + p.getDescricao() + "</td>");
				saida.print("<td>" + p.getValor() + "</td>");
				saida.print("<td>" + p.getEstoque() + "</td>");
				saida.print("<td> <a href='Alterar.do?codigo=" + p.getCodigo() + "'>Alterar</a> </td>");
				saida.print("<td> <a href='Excluir.do?codigo=" + p.getCodigo() + "'>Excluir</a> </td>");
				saida.print("</tr>");
			}
		);
		saida.print("</table>");
		saida.print("<br> <a class='botao' href='index.html'>Voltar</a>");
	}
}
