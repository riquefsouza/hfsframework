using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmCargoFuncionario
    {
        public virtual AdmCargoFuncionarioPK Id { get; set; }
        public virtual DateTime DataExercicio { get; set; }
	    public virtual DateTime DataDesligamento { get; set; }
	    public virtual DateTime DataPosse { get; set; }
	    public virtual string Situacao { get; set; }
	    public virtual string Presidente { get; set; }
	    public virtual string DiretorGeral { get; set; }
	    public virtual string ResponsavelOrcamento { get; set; }
	    public virtual string ChefeSepo { get; set; }

        public AdmCargoFuncionario()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = new AdmCargoFuncionarioPK();
            this.ChefeSepo = null;
            this.DataDesligamento = DateTime.Now;
            this.DataExercicio = DateTime.Now;
            this.DataPosse = DateTime.Now;
            this.DiretorGeral = null;
            this.Presidente = null;
            this.ResponsavelOrcamento = null;
            this.Situacao = null;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmCargoFuncionario outro = (AdmCargoFuncionario)obj;
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
