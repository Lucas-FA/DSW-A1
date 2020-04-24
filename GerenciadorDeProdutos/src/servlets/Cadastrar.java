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
		name = "Cadastrar.do", 
		urlPatterns = { 
				"/Cadastrar.do" 
		})
public class Cadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cadastrar() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter saida = response.getWriter();
		
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");
		String estoque = request.getParameter("estoque");
		
		if(codigo.equals("") || nome.equals("") || descricao.equals("") || valor.equals("") || estoque.equals("")) {
			saida.print("<p><a href='cadastrar.html'>Volte</a> e informe todos os dados!</p>");
		}
		else {
			int codigoInt = Integer.parseInt(codigo);
			double valorDouble = Double.parseDouble(valor);
			int estoqueInt = Integer.parseInt(estoque);
			
			if(codigoInt < 1 || valorDouble < 0.01 || estoqueInt < 0 || nome.length() > 256) {
				saida.print("<p><a href='cadastrar.html'>Volte</a> e informe um número válido código/valor/estoque e um nome com menos de 256 caracteres!</p>");
			}
			else {
				ProdutoService ps = new ProdutoService();
				Produto p = new Produto();
				p = ps.consultar(codigoInt);
				
				if(p.getCodigo() == codigoInt) {
					saida.print("<p>Já existe um produto cadastrado com essa matrícula <a href='cadastrar.html'>Volte</a> e informe uma matrícula válida!</p>");
				}
				else {
					p.setCodigo(codigoInt);
					p.setNome(nome);
					p.setDescricao(descricao);
					p.setValor(valorDouble);
					p.setEstoque(estoqueInt);
					ps.cadastrar(p);
					saida.print("<p>Produto cadastrado!</p> <a href='cadastrar.html'>Voltar</a>");
				}
			}
		}
	}
}
