using System;
using System.Collections.Generic;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmParametroCategoria
    {
	    public virtual long Id { get; set; }
	    public virtual string Descricao { get; set; }
	    public virtual long Ordem { get; set; }
        public virtual List<AdmParametro> AdmParametros { get; set; }

        public AdmParametroCategoria()
        {
            this.AdmParametros = new List<AdmParametro>();
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.Descricao = null;
            this.Ordem = -1;
            this.AdmParametros.Clear();
        }

        public virtual AdmParametro AddAdmParametro(AdmParametro admParametro)
        {
            AdmParametros.Add(admParametro);
            admParametro.AdmParametroCategoria = this;

            return admParametro;
        }

        public virtual AdmParametro RemoveAdmParametro(AdmParametro admParametro)
        {
            AdmParametros.Remove(admParametro);
            admParametro.AdmParametroCategoria = null;

            return admParametro;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmParametroCategoria outro = (AdmParametroCategoria)obj;
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
