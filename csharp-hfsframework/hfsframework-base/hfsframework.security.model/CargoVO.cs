using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class CargoVO
    {
        public long Id { get; set; }
        public string NomeCargo { get; set; }
        public List<PerfilVO> perfils;

        public CargoVO()
        {
            perfils = new List<PerfilVO>();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            CargoVO outro = (CargoVO)obj;
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
    }
}
