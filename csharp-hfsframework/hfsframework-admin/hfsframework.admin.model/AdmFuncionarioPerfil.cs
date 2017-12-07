using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionarioPerfil
    {
        public virtual AdmFuncionarioPerfilPK Id { get; set; }
        public virtual AdmPerfil AdmPerfil { get; set; }

        public AdmFuncionarioPerfil()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = new AdmFuncionarioPerfilPK();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionarioPerfil outro = (AdmFuncionarioPerfil)obj;
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
