using System.IO;
using System.Collections.Generic;
using System.Linq;

namespace hfsframework.util
{
    public class PropertiesUtil
    {
        private Dictionary<string, string> list;

        private string filename;

        public PropertiesUtil(string file)
        {
            Reload(file);
        }

        public string Get(string field, string defValue)
        {
            return (Get(field) == null) ? (defValue) : (Get(field));
        }
        public string Get(string field)
        {
            return (list.ContainsKey(field)) ? (list[field]) : (null);
        }

        public void Set(string field, object value)
        {
            field = TrimUnwantedChars(field);
            value = TrimUnwantedChars(value.ToString());

            if (!list.ContainsKey(field))
                list.Add(field, value.ToString());
            else
                list[field] = value.ToString();
        }

        public string TrimUnwantedChars(string toTrim)
        {
            toTrim = toTrim.Replace(";", string.Empty);
            toTrim = toTrim.Replace("#", string.Empty);
            toTrim = toTrim.Replace("'", string.Empty);
            toTrim = toTrim.Replace("=", string.Empty);
            return toTrim;
        }

        public void Save()
        {
            Save(filename);
        }

        public void Save(string filename)
        {
            this.filename = filename;

            if (!File.Exists(filename))
               File.Create(filename);

            StreamWriter file = new StreamWriter(filename);

            foreach (string prop in list.Keys.ToArray())
                if (!string.IsNullOrWhiteSpace(list[prop]))
                    file.WriteLine(prop + "=" + list[prop]);

            file.Close();
        }

        public void Reload()
        {
            Reload(filename);
        }

        public void Reload(string filename)
        {
            this.filename = filename;
            list = new Dictionary<string, string>();

            if (File.Exists(filename))
                LoadFromFile(filename);
            else
                File.Create(filename);
        }

        private void LoadFromFile(string file)
        {
            foreach (string line in File.ReadAllLines(file))
            {
                if ((!string.IsNullOrEmpty(line)) &&
                    (!line.StartsWith(";")) &&
                    (!line.StartsWith("#")) &&
                    (!line.StartsWith("'")) &&
                    (line.Contains('=')))
                {
                    int index = line.IndexOf('=');
                    string key = line.Substring(0, index).Trim();
                    string value = line.Substring(index + 1).Trim();

                    if ((value.StartsWith("\"") && value.EndsWith("\"")) ||
                        (value.StartsWith("'") && value.EndsWith("'")))
                    {
                        value = value.Substring(1, value.Length - 2);
                    }

                    try
                    {
                        list.Add(key, value);
                    }
                    catch { }
                }
            }
        }
    }
}
