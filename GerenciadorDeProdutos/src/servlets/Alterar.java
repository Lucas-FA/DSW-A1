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
		name = "Alterar.do", 
		urlPatterns = { 
				"/Alterar.do" 
		})
public class Alterar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Alterar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		ProdutoService ps = new ProdutoService();
		Produto p = ps.consultar(codigo);
		response.setContentType("text/html");
		PrintWriter saida = response.getWriter();
		
		saida.print("<link rel='stylesheet' type='text/css' href='estilo.css'>");
		saida.print("<form method='post' action='Alterar.do'>");
		saida.print("<p class='texto'>" + 
						"Código: <input type='number' name='codigo' onlyread='true' value='" + p.getCodigo() + "' readonly>" + 
					"</p>");
		saida.print("<p class='texto'>" + 
						"Nome: <input type='text' name='nome' value='" + p.getNome() + "'>" + 
					"</p>");
		saida.print("<p class='texto'>" + 
						"Descrição: <textarea name='descricao' rows='6' cols='60'>" + p.getDescricao() + "</textarea>" + 
					"</p>");
		saida.print("<p class='texto'>" + 
						"Valor: <input type='number' step='0.01' name='valor' value='" + p.getValor() + "'>" + 
					"</p>");
		saida.print("<p class='texto'>" + 
						"Estoque: <input type='number' name='estoque' value='" + p.getEstoque() + "'>" + 
					"</p>");
		saida.print("<input class='botao' type='submit' value='Alterar'>");
		saida.print("<a class=\"botao\" href='index.html'>Voltar</a>");
		saida.print("</form>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter saida = response.getWriter();
		
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");
		String estoque = request.getParameter("estoque");
		
		if(nome.equals("") || descricao.equals("") || valor.equals("") || estoque.equals("")) {
			saida.print("<p><a href='Listar.do'>Volte</a> e informe todos os dados!</p>");
		}
		else {
			int codigoInt = Integer.parseInt(codigo);
			double valorDouble = Double.parseDouble(valor);
			int estoqueInt = Integer.parseInt(estoque);
			
			ProdutoService ps = new ProdutoService();
			Produto p = new Produto(codigoInt, nome, descricao, valorDouble, estoqueInt);
			if(valorDouble < 0.01 || estoqueInt < 0 || nome.length() > 256) {
				saida.print("<p><a href='Listar.do'>Volte</a> e informe um número válido código/valor/estoque e um nome com menos de 256 caracteres!</p>");
			}
			else {
				ps.alterar(p);
				saida.print("<p>Dados alterados!</p> <a href='Listar.do'>Voltar</a>");
			}
		}
	}
}