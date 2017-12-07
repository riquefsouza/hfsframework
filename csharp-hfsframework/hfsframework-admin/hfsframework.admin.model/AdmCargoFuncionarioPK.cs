using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmCargoFuncionarioPK
    {
        public virtual long CodCargo { get; set; }
        public virtual long CodFuncionario { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmCargoFuncionarioPK id;
            id = (AdmCargoFuncionarioPK)obj;
            if (id == null)
                return false;
            if (CodCargo == id.CodCargo && CodFuncionario == id.CodFuncionario)
                return true;
            return false;
        }
        public override int GetHashCode()
        {
            return (CodCargo + "|" + CodFuncionario).GetHashCode();
        }
    }
}
