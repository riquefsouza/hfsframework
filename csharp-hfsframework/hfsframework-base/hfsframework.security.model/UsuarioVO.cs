using System;
using hfsframework.util;

namespace hfsframework.security.model
{
    [Serializable]
    public class UsuarioVO
    {
        public long Matricula { get; set; }
        public string Ip { get; set; }
        public string Login { get; set; }
	    public string Nome { get; set; }
	    public DateTime Data { get; set; }
	    public long Cpf { get; set; }
	    public string Email { get; set; }
	    public string LdapDN { get; set; }
	    public string Senha { get; set; }

        public UsuarioVO()
        {
            limpar();
        }

        public void limpar()
        {
            this.Matricula = 0;
            this.Ip = "";
            this.Cpf = 0;
            this.Data = new DateTime();
            this.Email = "";
            this.LdapDN = "";
            this.Login = "";
            this.Nome = "";
            this.Senha = "";
        }

        public string GetCpfFormatado()
        {
            try
            {
                return CPFCNPJUtil.formatCPForCPNJ(Cpf, false);
            }
            catch (Exception)
            {
                return this.Cpf.ToString();
            }
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            UsuarioVO other = (UsuarioVO)obj;
            if (other == null)
                return false;
            if (Ip == null)
            {
                if (other.Ip != null)
                    return false;
            }
            else if (!Ip.Equals(other.Ip))
                return false;
            if (!Matricula.Equals(other.Matricula))
                return false;
            return true;
        }

        public override int GetHashCode()
        {
            const int prime = 31;
            int result = 1;
            result = prime * result + ((Ip == null) ? 0 : Ip.GetHashCode());
            result = prime * result + Matricula.GetHashCode();
            return result;
        }

        public override string ToString()
        {
            return Nome;
        }
    }
}
