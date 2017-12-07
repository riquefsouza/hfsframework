using System;
using System.Collections.Generic;
using log4net;

namespace hfsframework.hfsbase
{
    public abstract class BaseBusinessController<T, I, C> : IBaseCrud<T, I> where C : EntityRepository<T, I>
    {
        protected ILog log;

        protected C repositorio;

        protected AplicacaoUtil aplicacaoUtil;

        public BaseBusinessController(C repositorio)
        {
            log = LogManager.GetLogger(typeof(BaseBusinessController<T, I, C>));
            this.repositorio = repositorio;
            aplicacaoUtil = AplicacaoUtil.Instancia;
        }

        public T load(I id)
        {
            return repositorio.load(id);
        }

        public List<T> findAll()
        {
            return repositorio.findAll();
        }

        public string getDescricao(I id)
        {
            return repositorio.getDescricao(id);
        }

        public T insert(T bean)
        {
            return repositorio.insert(bean);
        }
        
        public T update(T bean)
        {
            return repositorio.update(bean);
        }

        public void delete(T bean)
        {
            repositorio.delete(bean);
        }

        public List<T> listarPaginada(int numeroPagina, int tamanhoPagina)
        {
            return repositorio.listarPaginada(numeroPagina, tamanhoPagina);
        }

        public List<T> listarPorIntervalo(int intervaloInicial, int intervaloFinal)
        {
            return repositorio.listarPorIntervalo(intervaloInicial, intervaloFinal);
        }

        public bool existeAntigo(I id, string novo)
        {
            return existeAntigo(id, novo);
        }

        public bool existeNovo(string novo)
        {
            return existeNovo(novo);
        }
    }
}
