package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Cacheable(value = "produtoCache", key = "#id")
    public Produto getProdutoById(long id) {
        System.out.println("getProdutoById()");
        return produtoRepository.findById(id).get();
    }

    @Override
    @Cacheable(value = "allProdutosCache", unless = "#result.size() == 0")
    public List<Produto> getAllProdutos() {
        System.out.println("getAllProdutos()");
        List<Produto> lista = new ArrayList<>();
        produtoRepository.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "produtoCache", key = "#produto.id")},
            evict = {@CacheEvict(value = "allProdutosCache", allEntries = true)}
    )
    public Produto addProduto(Produto produto) {
        System.out.println("addProduto()");
        return produtoRepository.save(produto);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "produtoCache", key = "#produto.id")},
            evict = {@CacheEvict(value = "allProdutosCache", allEntries = true)}
    )
    public Produto updateProduto(Produto produto) {
        System.out.println("addProduto()");
        return produtoRepository.save(produto);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "produtoCache", key = "#id"),
                    @CacheEvict(value = "allProdutosCache", allEntries = true)
            }
    )
    public void deleteProduto(long id) {
        System.out.println("deleteProduto()");
        produtoRepository.delete(produtoRepository.findById(id).get());
    }

}
