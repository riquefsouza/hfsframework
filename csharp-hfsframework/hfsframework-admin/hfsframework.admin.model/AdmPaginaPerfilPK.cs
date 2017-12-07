using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmPaginaPerfilPK
    {
        public virtual long PaginaSeq { get; set; }
        public virtual long PerfilSeq { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmPaginaPerfilPK id;
            id = (AdmPaginaPerfilPK)obj;
            if (id == null)
                return false;
            if (PaginaSeq == id.PaginaSeq && PerfilSeq == id.PerfilSeq)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (PaginaSeq + "|" + PerfilSeq).GetHashCode();
        }
    }
}
