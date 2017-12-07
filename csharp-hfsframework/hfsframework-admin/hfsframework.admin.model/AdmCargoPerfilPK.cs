using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmCargoPerfilPK
    {
        public virtual long CodCargo { get; set; }
        public virtual long PerfilSeq { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmCargoPerfilPK id;
            id = (AdmCargoPerfilPK)obj;
            if (id == null)
                return false;
            if (CodCargo == id.CodCargo && PerfilSeq == id.PerfilSeq)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (CodCargo + "|" + PerfilSeq).GetHashCode();
        }
    }
}
