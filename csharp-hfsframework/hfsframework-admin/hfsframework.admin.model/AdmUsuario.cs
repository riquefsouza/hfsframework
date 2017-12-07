using System;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmUsuario
    {
        public virtual AdmUsuarioPK Id { get; set; }
        public virtual string Login { get; set; }
	    public virtual string Nome { get; set; }
	    public virtual DateTime Data { get; set; }
	    public virtual long Cpf { get; set; }
	    public virtual string Email { get; set; }
	    public virtual string LdapDN { get; set; }
	    public virtual string Senha { get; set; }

        public AdmUsuario()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = new AdmUsuarioPK();
            this.Cpf = 0;
            this.Data = new DateTime();
            this.Email = "";
            this.LdapDN = "";
            this.Login = "";
            this.Nome = "";
            this.Senha = "";
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmUsuario other = (AdmUsuario)obj;
            if (Id == null)
            {
                if (other.Id != null)
                    return false;
            }
            else if (!Id.Equals(other.Id))
                return false;
            return true;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

        public override string ToString()
        {
            return Nome;
        }

        public virtual UsuarioVO ToUsuarioVO()
        {
            UsuarioVO u = new UsuarioVO();

            u.Matricula = Id.Matricula;
            u.Ip = Id.Ip;
            u.Cpf = Cpf;
            u.Data = Data;
            u.Email = Email;
            u.LdapDN = LdapDN;
            u.Login = Login;
            u.Nome = Nome;
            u.Senha = Senha;

            return u;
        }

    }
}
