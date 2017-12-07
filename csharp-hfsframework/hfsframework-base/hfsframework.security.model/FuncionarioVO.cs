using System;
using hfsframework.util;

namespace hfsframework.security.model
{
    [Serializable]
    public class FuncionarioVO
    {
	    public long Id { get; set; }
	    public string Nome { get; set; }
	    public long Cpf { get; set; }
	    public string Email { get; set; }
	    public string Telefone { get; set; }
	    public string Celular { get; set; }
        public long CodCargoPrincipal { get; set; }
        public string SetSigla { get; set; }	    
	    public DateTime DataAdmissao { get; set; }
	    public DateTime DataSaida { get; set; }
	    public bool Ativo { get; set; }
        public CargoVO CargoPrincipal { get; set; }
        public string Setor { get; set; }

        public FuncionarioVO()
        {
            limpar();
        }

        public void limpar()
        {
            this.Id = 0;
            this.Ativo = false;
            this.Celular = "";
            this.CodCargoPrincipal = 0;
            this.Cpf = 0;
            this.DataAdmissao = new DateTime();
            this.DataSaida = new DateTime();
            this.Email = "";
            this.Nome = "";
            this.Setor = "";
            this.Telefone = "";
            this.CargoPrincipal = new CargoVO();
        }

        public string GetCpfFormatado()
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
            FuncionarioVO outro = (FuncionarioVO)obj;
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

    }
}
