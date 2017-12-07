using System.Collections.Generic;

namespace hfsframework.hfsbase
{
    public interface IBaseCrud<T, I> {	
	    T load(I id);
        List<T> findAll();
        T insert(T bean);
        T update(T bean);
        void delete(T bean);
        List<T> listarPaginada(int numeroPagina, int tamanhoPagina);
        List<T> listarPorIntervalo(int intervaloInicial, int intervaloFinal);
        string getDescricao(I id);
        bool existeNovo(string novo);
        bool existeAntigo(I id, string novo);
    }
}
