using System;
using log4net;

namespace hfsframework.util.exceptions
{
    public class TransacaoException: Exception
    {
        public TransacaoException(ILog log, string mensagem) : base(mensagem)
        {
            //ExcecaoUtil.getErros(log, this, mensagem, true);
        }
    }
}
