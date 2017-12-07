using System;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmCargo
    {
        public virtual long Id { get; set; }
        public virtual string NomeCargo { get; set; }

        public AdmCargo()
        {
            limpar();
        }

        public AdmCargo(CargoVO c): base()
        {
            this.Id = c.Id;
            this.NomeCargo = c.NomeCargo;
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.NomeCargo = null;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmCargo outro = (AdmCargo)obj;
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

        public override string ToString()
        {
            return NomeCargo;
        }

        public virtual CargoVO ToCargoVO()
        {
            CargoVO c = new CargoVO();
            c.Id = Id;
            c.NomeCargo = NomeCargo;

            return c;
        }

    }
}
