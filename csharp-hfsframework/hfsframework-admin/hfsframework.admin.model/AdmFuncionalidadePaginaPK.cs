using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionalidadePaginaPK
    {
        public virtual long FuncionalidadeSeq { get; set; }
        public virtual long PaginaSeq { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionalidadePaginaPK id;
            id = (AdmFuncionalidadePaginaPK)obj;
            if (id == null)
                return false;
            if (FuncionalidadeSeq == id.FuncionalidadeSeq && PaginaSeq == id.PaginaSeq)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (FuncionalidadeSeq + "|" + PaginaSeq).GetHashCode();
        }

    }
}
