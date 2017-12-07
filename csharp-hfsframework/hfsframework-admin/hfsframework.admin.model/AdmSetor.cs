namespace hfsframework.admin.model
{
    public class AdmSetor
    {
	    public virtual string Id { get; set; }
	    public virtual string NomeSetor { get; set; }
	    public virtual string PaiSetor { get; set; }
	    public virtual string TipoSetor { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmSetor other = (AdmSetor)obj;
            if (Id == null)
            {
                if (other.Id != null)
                    return false;
            }
            else if (!Id.Equals(other.Id))
                return false;
            return true;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

    }
}
