using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionalidadePerfilPK
    {
        public virtual long FuncionalidadeSeq { get; set; }
        public virtual long PerfilSeq { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionalidadePerfilPK id;
            id = (AdmFuncionalidadePerfilPK)obj;
            if (id == null)
                return false;
            if (FuncionalidadeSeq == id.FuncionalidadeSeq && PerfilSeq == id.PerfilSeq)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (FuncionalidadeSeq + "|" + PerfilSeq).GetHashCode();
        }

    }
}
