package com.sunil.file.reader;

/**
 *
 * @author  Hunghm
 */
public class QuartzJobConfig
{
    protected String name = null;
    protected String className = null;
    protected String seconds = null;
    protected String minute = null;
    protected String hour = null;
    protected String dayOfMonth = null;
    protected String month = null;
    protected String dayOfWeek = null;

    public QuartzJobConfig()
    {
    	
    }

    public QuartzJobConfig(
        String name, String className, String seconds, String minute, String hour,
        String dayOfMonth, String month, String dayOfWeek)
    {
        this.name = name;
        this.className = className;
        this.seconds = seconds;
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public void setSeconds(String seconds)
    {
        this.seconds = seconds;
    }

    public void setMinute(String minute)
    {
        this.minute = minute;
    }

    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public void setDayOfMonth(String dayOfMonth)
    {
        this.dayOfMonth = dayOfMonth;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public void setDayOfWeek(String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    public String getName()
    {
        return this.name;
    }

    public String getClassName()
    {
        return this.className;
    }

    public String getSeconds()
    {
         return this.seconds;
    }

    public String getMinute()
    {
         return this.minute;
    }

    public String getHour()
    {
         return this.hour;
    }

    public String getDayOfMonth()
    {
         return this.dayOfMonth;
    }

    public String getMonth()
    {
         return this.month;
    }

    public String getDayOfWeek()
    {
         return this.dayOfWeek;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Name=").append(name);
        sb.append(", className=").append(className);
        sb.append(", seconds=").append(seconds);
        sb.append(", minute=").append(minute);
        sb.append(", hour=").append(hour);
        sb.append(", dayOfMonth=").append(dayOfMonth);
        sb.append(", month=").append(month);
        sb.append(", dayOfWeek=").append(dayOfWeek);
        return sb.toString();
    }
} // class QuartzJobConfig
