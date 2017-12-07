using System;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmUsuarioPK
    {
        public virtual long Matricula { get; set; }
        public virtual string Ip { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmUsuarioPK id;
            id = (AdmUsuarioPK)obj;
            if (id == null)
                return false;
            if (Matricula == id.Matricula && Ip == id.Ip)
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return (Matricula + "|" + Ip).GetHashCode();
        }
    }
}
