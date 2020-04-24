package service;

import java.util.ArrayList;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoService {
	private ProdutoDAO produtoDao;
	
	public ProdutoService() {
		this.produtoDao = new ProdutoDAO();
	}
	
	public void cadastrar(Produto produto) {
		this.produtoDao.cadastrar(produto);
	}
	
	public void alterar(Produto produto) {
		this.produtoDao.alterar(produto);
	}
	
	public void excluir(Produto produto) {
		this.produtoDao.excluir(produto);
	}
	
	public Produto consultar(int codigo) {
		return this.produtoDao.consultar(codigo);
	}
	
	public ArrayList<Produto> listar() {
		return this.produtoDao.listarProdutos();
	}
}
