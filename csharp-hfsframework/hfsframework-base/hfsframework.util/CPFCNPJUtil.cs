using System;
using System.Text.RegularExpressions;

namespace hfsframework.util
{
    [Serializable]
    public sealed class CPFCNPJUtil
    {
        //private static string VALUE_CANNOT_BE_NULL = "o valor não pode ser nulo";

    	private static string VALUE_CANNOT_BE_NULL_OR_EMPTY = "o valor não pode ser nulo ou vazio";

    	private static string SIZE_OF_VALUE_CANNOT_BE_BIGGER_THEN_14 = "o tamanho do valor não pode ser maior do que 14";

    	private static string VALUE_IS_NOT_A_VALID_CPF_OR_CPNJ = "o valor não é valido para CPF ou CPNJ";

    	private static string CNPJ_NFORMAT = "00000000000000";

        private static string CPF_NFORMAT = "00000000000";

        private static int[] weightCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        private static int[] weightCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        public static string formatCPForCPNJ(string value)
        {
            if (value == null || value.Equals(""))
            {
                throw new ArgumentException(VALUE_CANNOT_BE_NULL_OR_EMPTY);
            }
            return formatCPForCPNJ(long.Parse(Regex.Replace(value, @"[^0-9]+", "")), true);
        }

        public static string formatCPForCPNJ(string value, bool check)
        {
            if (value == null || value.Equals(""))
            {
                throw new ArgumentException(VALUE_CANNOT_BE_NULL_OR_EMPTY);
            }
            return formatCPForCPNJ(long.Parse(Regex.Replace(value, @"[^0-9]+", "")), check);
        }

        public static string formatCPForCPNJ(long value)
        {
            return formatCPForCPNJ(value, true);
        }

        public static string formatCPForCPNJ(long value, bool check)
        {
            /*
            if (value == null)
            {
                throw new ArgumentException(VALUE_CANNOT_BE_NULL);
            }
            */

            int valueSize = value.ToString().Length;
            if (valueSize > 14)
            {
                throw new ArgumentException(SIZE_OF_VALUE_CANNOT_BE_BIGGER_THEN_14);
            }

            if (check && !isCPForCPNJ(value))
            {
                throw new ArgumentException(VALUE_IS_NOT_A_VALID_CPF_OR_CPNJ);
            }

            bool isCPF = valueSize < 12;
            string formatDecimal = isCPF ? CPF_NFORMAT : CNPJ_NFORMAT;

            string stringNumber = value.ToString(formatDecimal);

            return isCPF ? Regex.Replace(stringNumber, @"([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4")
                    : Regex.Replace(stringNumber, @"([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})", "$1.$2.$3/$4-$5");
        }

        public static bool isCPForCPNJ(string value)
        {
            if (value == null || value.Equals(""))
            {
                throw new ArgumentException(VALUE_CANNOT_BE_NULL_OR_EMPTY);
            }
            return isCPForCPNJ(long.Parse(Regex.Replace(value, @"[^0-9]+", "")));
        }

        public static bool isCPForCPNJ(long value)
        {
            int valueSize = value.ToString().Length;
            if (valueSize > 14)
            {
                return false;
            }

            bool isCPF = valueSize < 12;

            return isCPF ? IsCPF(value) : IsCNPJ(value);
        }

        private static bool IsCPF(long value)
        {
            string CPF = value.ToString(CPF_NFORMAT);

            int firstPart = calcDigit(CPF.Substring(0, 9), weightCPF);
            int lastPart = calcDigit(CPF.Substring(0, 9) + firstPart, weightCPF);

            return CPF.Substring(9).Equals(string.Format("%d%d", firstPart, lastPart));
        }

        private static bool IsCNPJ(long value)
        {
            string CNPJ = value.ToString(CNPJ_NFORMAT);

            int firstPart = calcDigit(CNPJ.Substring(0, 12), weightCNPJ);
            int lastPart = calcDigit(CNPJ.Substring(0, 12) + firstPart, weightCNPJ);

            return CNPJ.Substring(12).Equals(string.Format("%d%d", firstPart, lastPart));
        }

        private static int calcDigit(String stringBase, int[] weight)
        {
            int sum = 0;
            for (int index = stringBase.Length - 1, digit; index >= 0; index--)
            {
                digit = int.Parse(stringBase.Substring(index, index + 1));
                sum += digit * weight[weight.Length - stringBase.Length + index];
            }
            sum = 11 - sum % 11;
            return sum > 9 ? 0 : sum;
        }

    }
}
