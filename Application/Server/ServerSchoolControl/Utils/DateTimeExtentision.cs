using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Utils
{
    public static class DateTimeExtension
    {
        public static DateTime WeekStart(this DateTime datetime)
        {
            DayOfWeek day = datetime.Date.DayOfWeek;
            int corrector;
            if (day == DayOfWeek.Sunday)
                corrector = 6;
            else
                corrector = (int)day - 1;

            return datetime.Date.AddDays(-corrector);
        }

        public static DateTime ChangeDate(this DateTime that, DateTime targetDate)
        {
            return new DateTime(targetDate.Year, targetDate.Month, targetDate.Day, that.Hour, that.Minute, that.Second);
        }

        public static DateTime PayrollWeekEnd(this DateTime datetime)
        {
            return datetime.WeekEnd().AddDays(-7);
        }

        public static DateTime WeekEnd(this DateTime datetime)
        {
            var day = datetime.Date.DayOfWeek;
            int corrector;
            if (day == DayOfWeek.Sunday)
                corrector = 0;
            else
                corrector = 7 - (int)day;

            return datetime.Date.AddDays(corrector);
        }

        public static DateTime UsWeekStart(this DateTime datetime)
        {
            return datetime.AddDays(-(int)datetime.Date.DayOfWeek);
        }

        public static DateTime UsWeekEnd(this DateTime datetime)
        {
            return datetime.AddDays(6 - (int)datetime.Date.DayOfWeek);
        }

        public static DateTime UnspecifyKind(this DateTime value)
        {
            DateTime unspecifiedValue = DateTime.SpecifyKind(value, DateTimeKind.Unspecified);
            return unspecifiedValue;
        }

        public static DateTime RoundUpMinutes(this DateTime value, int roundUpToMinutes)
        {
            //we need recreate loose last digits of ticks
            var val = new DateTime(value.Year, value.Month, value.Day, value.Hour, value.Minute, value.Second);

            //Round up seconds
            if (val.Second != 0)
            {
                int secondCorrection;

                if (val.Second > 30)
                    secondCorrection = 60 - val.Second;
                else
                    secondCorrection = -val.Second;

                val = val.AddSeconds(secondCorrection);
            }

            //Round up minutes
            int curMinutes = val.Minute;
            int roundedMinutes = (curMinutes / roundUpToMinutes) * roundUpToMinutes;

            int extraMins = curMinutes - roundedMinutes;

            int minuteCorrection;
            if (extraMins < ((double)roundUpToMinutes) / 2)
                minuteCorrection = -extraMins;
            else
                minuteCorrection = roundUpToMinutes - extraMins;

            return val + TimeSpan.FromMinutes(minuteCorrection);
        }

        public static DateTime MonthStartDate(this DateTime datetime)
        {
            return new DateTime(datetime.Year, datetime.Month, 1);
        }

        public static DateTime MonthEndDate(this DateTime datetime)
        {
            return new DateTime(datetime.Year, datetime.Month, 1).AddMonths(1).AddDays(-1);
        }

        public static DateTime YearStartDate(this DateTime datetime)
        {
            return new DateTime(datetime.Year, 1, 1);
        }

        public static DateTime YearEndDate(this DateTime datetime)
        {
            return new DateTime(datetime.Year, 1, 1).AddYears(1).AddDays(-1);
        }

        public static Boolean CheckForOverlapps(this DateTime startTime, DateTime stopTime, DateTime startTime2, DateTime stopTime2)
        {
            var a = startTime.Ticks;
            var b = stopTime.Ticks;
            var c = startTime2.Ticks;
            var d = stopTime2.Ticks;

            var result = ((b - a) + (d - c) - Math.Abs(c - a) - Math.Abs(d - b)) / 2;

            if (result > 0)
                return true;

            if (c == d && a < c && c < b)
                return true;

            if (a == b && c < a && a < d)
                return true;

            return false;
        }

        /// <summary>
        /// Returns second full week.
        /// </summary>
        /// <param name="datetime"></param>
        /// <returns></returns>
        public static DateTime VehicleAllowancePayrollProcessingWeek(this DateTime datetime)
        {
            var date = datetime.MonthStartDate().WeekEnd();
            var current = DateTime.Today.MonthStartDate().WeekEnd();

            var res = date >= current ? date.AddMonths(1).MonthStartDate().WeekEnd() : current;
            if (res.AddDays(res.Day < 7 ? 7 : 0) < DateTime.Today)
                res = DateTime.Today.AddMonths(1).MonthStartDate().WeekEnd();

            return res.AddDays(res.Day < 7 ? 7 : 0);
        }

        /// <summary>
        /// Replace specified part in date time value.
        /// </summary>
        public static DateTime ReplacePart(this DateTime value, int? year = null, int? month = null, int? day = null, int? hour = null, int? minute = null, int? second = null, int? millisecond = null)
        {
            return new DateTime(
                year == null ? value.Year : year.GetValueOrDefault(),
                month == null ? value.Month : month.GetValueOrDefault(),
                day == null ? value.Day : day.GetValueOrDefault(),
                hour == null ? value.Hour : hour.GetValueOrDefault(),
                minute == null ? value.Minute : minute.GetValueOrDefault(),
                second == null ? value.Second : second.GetValueOrDefault(),
                millisecond == null ? value.Millisecond : millisecond.GetValueOrDefault());
        }


    }
}
