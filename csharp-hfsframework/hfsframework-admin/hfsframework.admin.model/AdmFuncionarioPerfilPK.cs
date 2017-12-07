using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionarioPerfilPK
    {        
        public virtual long CodFuncionario { get; set; }
        public virtual long PerfilSeq { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionarioPerfilPK id;
            id = (AdmFuncionarioPerfilPK)obj;
            if (id == null)
                return false;
            if (PerfilSeq == id.PerfilSeq && CodFuncionario == id.CodFuncionario)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (PerfilSeq + "|" + CodFuncionario).GetHashCode();
        }
    }
}
