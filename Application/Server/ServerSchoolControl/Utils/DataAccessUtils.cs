using Core.DTO;
using Microsoft.SqlServer.Server;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Text;

namespace Utils
{
    public static class DataAccessUtils
    {
        //public static DataTable ToDataTable(this List<ProductAmount> products)
        //{
        //    DataTable result = new DataTable();
        //    result.Columns.Add("Guid", typeof(Guid));
        //    result.Columns.Add("Amount", typeof(int));
        //    foreach (var productAmount in products)
        //    {
        //        result.Rows.Add(productAmount.ProductGuid, productAmount.Amount);
        //    }
        //    return result;
        //}

        #region Char

        public static char GetCharDontUseIt(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetChar(dataReader.GetOrdinal(name));
        }

        #endregion

        #region String
        public static string GetString(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetString(dataReader.GetOrdinal(name));
        }

        public static string GetStringNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return dataReader.GetString(ordinal);
        }

        public static string GetStringOrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return string.Empty;
            else
                return dataReader.GetString(ordinal);
        }
        #endregion

        #region DateTime
        public static DateTime GetDateTime(this SqlDataReader dataReader, string name)
        {
            return DateTime.SpecifyKind(
                dataReader.GetDateTime(dataReader.GetOrdinal(name)),
                DateTimeKind.Unspecified);
        }

        public static DateTime? GetDateTimeNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (DateTime?)DateTime.SpecifyKind(
                    dataReader.GetDateTime(ordinal),
                    DateTimeKind.Unspecified);
        }
        #endregion

        #region GUID
        public static Guid GetGuid(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetGuid(dataReader.GetOrdinal(name));
        }

        public static Guid? GetGuidNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (Guid?)dataReader.GetGuid(ordinal);
        }

        public static Guid GetGuidOrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return Guid.Empty;
            else
                return dataReader.GetGuid(ordinal);
        }

        public static Guid GetGuidOrDefaultIfExists(this SqlDataReader dataReader, string name)
        {
            if (dataReader.HasColumn(name))
            {
                return GetGuidOrDefault(dataReader, name);
            }
            else
            {
                return Guid.Empty;
            }
        }
        #endregion

        #region Boolean
        public static bool GetBooleanIfFieldExists(this SqlDataReader dataReader, string name)
        {
            if (dataReader.DoesColumnExist(name))
                return dataReader.GetBoolean(dataReader.GetOrdinal(name));
            return false;
        }
        public static bool GetBoolean(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetBoolean(dataReader.GetOrdinal(name));
        }

        public static bool? GetBooleanNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (bool?)dataReader.GetBoolean(ordinal);
        }
        #endregion

        #region Int16
        public static short GetInt16(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetInt16(dataReader.GetOrdinal(name));
        }
        #endregion

        #region Int32
        public static int GetInt32(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetInt32(dataReader.GetOrdinal(name));
        }

        public static int? GetInt32Nullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (int?)dataReader.GetInt32(ordinal);
        }

        public static int GetInt32OrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return 0;
            else
                return dataReader.GetInt32(ordinal);
        }

        public static int GetInt32IfFieldExists(this SqlDataReader dataReader, string name)
        {
            if (dataReader.DoesColumnExist(name))
                return dataReader.GetInt32(dataReader.GetOrdinal(name));
            return 0;
        }
        #endregion

        #region Int64
        public static Int64 GetInt64(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetInt64(dataReader.GetOrdinal(name));
        }

        public static Int64? GetInt64Nullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (Int64?)dataReader.GetInt64(ordinal);
        }

        public static Int64 GetInt64OrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return 0;
            else
                return dataReader.GetInt64(ordinal);
        }
        #endregion

        #region Float
        public static double GetDouble(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetDouble(dataReader.GetOrdinal(name));
        }

        public static double? GetDoubleNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (double?)dataReader.GetDouble(ordinal);
        }

        public static double GetDoubleOrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return 0;
            else
                return dataReader.GetDouble(ordinal);
        }
        #endregion

        #region Decimal
        public static Decimal GetDecimal(this SqlDataReader dataReader, string name)
        {
            return dataReader.GetDecimal(dataReader.GetOrdinal(name));
        }

        public static Decimal? GetDecimalNullable(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return null;
            else
                return (Decimal?)dataReader.GetDecimal(ordinal);
        }

        public static Decimal GetGetDecimalOrDefault(this SqlDataReader dataReader, string name)
        {
            int ordinal = dataReader.GetOrdinal(name);

            if (dataReader.IsDBNull(ordinal))
                return 0;
            else
                return dataReader.GetDecimal(ordinal);
        }
        #endregion

        #region Timestamp

        public static byte[] GetTimestamp(this SqlDataReader dataReader, string parameterName = "Timestamp")
        {
            int ordinal = dataReader.GetOrdinal(parameterName);

            byte[] timestamp = new byte[8];
            dataReader.GetBytes(ordinal, 0, timestamp, 0, 8);
            return timestamp;
        }

        #endregion

        #region Add SqlParameter

        public static SqlParameter AddWithValue_Guid(this SqlParameterCollection collection, string parameterName, Guid? parameterValue)
        {
            SqlParameter param;

            param = collection.Add(parameterName, System.Data.SqlDbType.UniqueIdentifier);

            if (parameterValue.HasValue)
                param.Value = parameterValue.Value;
            else
                param.Value = DBNull.Value;

            return param;
        }

        public static SqlParameter AddWithValue_Bool(this SqlParameterCollection collection, string parameterName, Boolean? parameterValue)
        {
            SqlParameter param;

            param = collection.Add(parameterName, System.Data.SqlDbType.Bit);

            if (parameterValue.HasValue)
                param.Value = parameterValue.Value;
            else
                param.Value = DBNull.Value;

            return param;
        }

        public static SqlParameter AddWithValue_Int(this SqlParameterCollection collection, string parameterName, Int32? parameterValue)
        {
            SqlParameter param;

            param = collection.Add(parameterName, System.Data.SqlDbType.Int);

            if (parameterValue.HasValue)
                param.Value = parameterValue.Value;
            else
                param.Value = DBNull.Value;

            return param;
        }

        public static SqlParameter AddWithValue_Double(this SqlParameterCollection collection, string parameterName, Double? parameterValue)
        {
            SqlParameter param;

            param = collection.Add(parameterName, System.Data.SqlDbType.Float);

            if (parameterValue.HasValue)
                param.Value = parameterValue.Value;
            else
                param.Value = DBNull.Value;

            return param;
        }

        public static SqlParameter AddWithValue_DateTime(this SqlParameterCollection collection, string parameterName, DateTime? parameterValue)
        {
            SqlParameter param;

            param = collection.Add(parameterName, System.Data.SqlDbType.DateTime);

            if (parameterValue.HasValue)
                param.Value = parameterValue.Value.UnspecifyKind();
            else
                param.Value = DBNull.Value;

            return param;
        }

        public static SqlParameter AddWithValue_String(this SqlParameterCollection collection, string parameterName, string parameterValue)
        {
            var param = collection.AddWithValue(parameterName, new SqlString(parameterValue));

            return param;
        }

        public static SqlParameter AddGuidListTvp(this SqlParameterCollection collection, string parameterName, IList<Guid> guidList)
        {
            var param = collection.Add(parameterName, SqlDbType.Structured);
            collection[parameterName].Direction = ParameterDirection.Input;
            collection[parameterName].TypeName = "GuidListTVP";
            collection[parameterName].Value = ConvertToGuidListTvp(guidList);

            return param;
        }

        #endregion

        public static bool DoesColumnExist(this SqlDataReader dataReader, string columnName)
        {
            dataReader.GetSchemaTable().DefaultView.RowFilter = "ColumnName= '" + columnName + "'";
            return (dataReader.GetSchemaTable().DefaultView.Count > 0);
        }

        public static bool HasColumn(this IDataRecord dr, string columnName)
        {
            for (var i = 0; i < dr.FieldCount; i++)
            {
                if (dr.GetName(i).Equals(columnName, StringComparison.InvariantCultureIgnoreCase))
                    return true;
            }
            return false;
        }

        internal static List<SqlDataRecord> ConvertToGuidListTvp(IList<Guid> guids)
        {
            if (guids.Count == 0)
                return null;

            var result = new List<SqlDataRecord>();

            SqlMetaData[] tvpDefinition = 
			{ 
				new SqlMetaData("Guid", SqlDbType.UniqueIdentifier), 
			};


            foreach (var guid in guids)
            {
                var tvpItem = new SqlDataRecord(tvpDefinition);
                tvpItem.SetGuid(0, guid);

                result.Add(tvpItem);
            }

            return result;
        }

        internal static List<SqlDataRecord> ConvertToGuidTimestampListTvp(Dictionary<Guid, byte[]> timestampList)
        {
            var result = new List<SqlDataRecord>();

            SqlMetaData[] tvpDefinition = 
			{ 
				new SqlMetaData("Guid", SqlDbType.UniqueIdentifier), 
				new SqlMetaData("Timestamp", SqlDbType.Binary, 8)
			};

            foreach (var pair in timestampList)
            {
                var timesheetGuid = pair.Key;
                var timestamp = pair.Value;


                var tvpItem = new SqlDataRecord(tvpDefinition);
                tvpItem.SetGuid(0, timesheetGuid);
                tvpItem.SetBytes(1, 0, timestamp, 0, 8);

                result.Add(tvpItem);
            }

            return result;
        }
    }
}
