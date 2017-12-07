using System;
using hfsframework.util;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionario
    {
	    public virtual long Id { get; set; }
	    public virtual string Nome { get; set; }
	    public virtual long Cpf { get; set; }
	    public virtual string Email { get; set; }
	    public virtual string Telefone { get; set; }
	    public virtual string Celular { get; set; }
        public virtual long CodCargoPrincipal { get; set; }
        public virtual string Setor { get; set; }	    
	    public virtual DateTime DataAdmissao { get; set; }
	    public virtual DateTime DataSaida { get; set; }
	    public virtual string Ativo { get; set; }
        public virtual AdmCargo AdmCargoPrincipal { get; set; }

        public AdmFuncionario()
        {
            limpar();
        }

        public AdmFuncionario(FuncionarioVO f): base()
        {
            this.Id = f.Id;
            this.Ativo = (f.Ativo ? "S" : "N");
            this.Celular = f.Celular;
            this.CodCargoPrincipal = f.CodCargoPrincipal;
            this.Cpf = f.Cpf;
            this.DataAdmissao = f.DataAdmissao;
            this.DataSaida = f.DataSaida;
            this.Email = f.Email;
            this.Nome = f.Nome;
            this.Setor = f.Setor;
            this.Telefone = f.Telefone;
            this.AdmCargoPrincipal = new AdmCargo(f.CargoPrincipal);
        }

        public virtual void limpar()
        {
            this.Id = 0;
            this.Ativo = "N";
            this.Celular = "";
            this.CodCargoPrincipal = 0;
            this.Cpf = 0;
            this.DataAdmissao = new DateTime();
            this.DataSaida = new DateTime();
            this.Email = "";
            this.Nome = "";
            this.Setor = "";
            this.Telefone = "";
            this.AdmCargoPrincipal = new AdmCargo();
        }

        public virtual string GetCpfFormatado()
        {
            try
            {
                return CPFCNPJUtil.formatCPForCPNJ(Cpf, false);
            }
            catch (Exception)
            {
                return Cpf.ToString();
            }
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionario outro = (AdmFuncionario)obj;
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

        public override string ToString()
        {
            try
            {
                return this.Nome + " (" + CPFCNPJUtil.formatCPForCPNJ(Cpf, false) + ")";
            }
            catch (Exception)
            {
                return this.Nome + " (" + Cpf + ")";
            }
        }

        public virtual FuncionarioVO ToFuncionarioVO()
        {
            FuncionarioVO f = new FuncionarioVO();

            f.Id = Id;
            f.Ativo = Ativo.Equals("S") ? true : false;
            f.Celular = Celular;
            f.CodCargoPrincipal = CodCargoPrincipal;
            f.Cpf = Cpf;
            f.DataAdmissao = DataAdmissao;
            f.DataSaida = DataSaida;
            f.Email = Email;
            f.Nome = Nome;
            f.Setor = Setor;
            f.Telefone = Telefone;

            if (AdmCargoPrincipal != null)
            {
                f.CargoPrincipal = AdmCargoPrincipal.ToCargoVO();
            }

            return f;
        }

    }
}
