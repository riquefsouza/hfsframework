using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmLogColuna
    {
	    public virtual string Nome { get; set; }
	    public virtual string NomeExibicao { get; set; }
	    public virtual string Comando { get; set; }

        public AdmLogColuna()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Nome = null;
            this.NomeExibicao = null;
            this.Comando = null;
        }

    }
}
