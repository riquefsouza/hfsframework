using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmParametro
    {
	    public virtual long Id { get; set; }
	    public virtual string Valor { get; set; }
	    public virtual string Descricao { get; set; }
	    public virtual string Codigo { get; set; }
	    public virtual long IdAdmParametroCategoria { get; set; }
        public virtual AdmParametroCategoria AdmParametroCategoria { get; set; }

        public AdmParametro()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.Codigo = null;
            this.Descricao = null;
            this.Valor = null;
            this.AdmParametroCategoria = new AdmParametroCategoria();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmParametro outro = (AdmParametro)obj;
            if (outro == null)
                return false;
            if (Id.Equals(outro.Id))
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

    }
}
