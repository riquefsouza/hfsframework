using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmCargoPerfil
    {
	    public virtual AdmCargoPerfilPK Id { get; set; }
	    public virtual AdmPerfil AdmPerfil { get; set; }

        public AdmCargoPerfil()
        {
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = new AdmCargoPerfilPK();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmCargoPerfil outro = (AdmCargoPerfil)obj;
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
