package com.example.programlock.locked.util;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LockedUtil {
    public static void main(String[] args) {
        Map<String,Object> map=getYearAndQuarterByMonth("2020-01");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=(Date) map.get("end");
        System.out.println("==============7====="+timeStampToStr(String.valueOf(date.getTime())));
        System.out.println("=========3444====="+StrToTimeStemp("2020-12-14 14:11:22"));

    }

    /**
     * 判断字符串是否为日期类型
     */
    public static boolean isNumberic(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 时间戳转时间格式字符串
     * @param timeStamp 时间戳
     * @param hourTime  小时制（12，24）---不填默认24小时
     * @return
     */
    public static String timeStampToStr(String timeStamp,int hourTime){
        SimpleDateFormat sdf24=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf12=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long longTimeStamp = new Long(timeStamp);

        Date date=new Date(longTimeStamp);
        String timeStr=sdf24.format(date);

        switch (hourTime){
            case 12:
                if(Integer.parseInt(timeStr.substring(11,13))>12){
                    timeStr=sdf12.format(date)+" PM";
                }else {
                    timeStr=sdf12.format(date)+" AM";
                }
                break;
            case 24:
                //timeStr=timeStr;
                break;
            default:
                timeStr="请确定是12小时制还是24小时制";
                break;
        }
        return timeStr;

    }

    public static String timeStampToStr(String timeStamp){
        return timeStampToStr(timeStamp,24);
    }


    /**
     * 字符串时间格式转时间戳
     * @param timeStr
     * @return
     */
    public static Long StrToTimeStemp(String timeStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        try {
            sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("=====转换日期失败");
        }
        Long timeStemp=date.getTime();
        return timeStemp;
    }


    /**
     * 根据传过来的月份返回该月份所在年度和季度,以及本月开始结束时间
     * @param month= yyyy-MM,
     * @return
     */
    public static Map<String,Object> getYearAndQuarterByMonth(String month){
        Map<String,Object> map=new HashMap<>();
        String pattern ="\\d{4}-\\d{2}";//定义日期格式正则
        Pattern p=Pattern.compile(pattern);//实例化Pattern
        Matcher m=p.matcher(month);//验证字符串内容是否合法
        if(m.matches())//使用正则验证
        {
            System.out.println("输入的日期格式合法！");
            String[] dateArr=month.split("-");
            map.put("year",dateArr[0]);
            int monthNum=Integer.parseInt(dateArr[1]);
            if(0<monthNum&&monthNum<4){
                map.put("quarter","1");
            }else if(3<monthNum&&monthNum<7){
                map.put("quarter","2");
            }else if (6<monthNum&&monthNum<10){
                map.put("quarter","3");
            }else if (9<monthNum&&monthNum<13){
                map.put("quarter","4");
            }else {
                return null;
            }

            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.set(Integer.parseInt(dateArr[0]),monthNum-1,1);//月度赋值从0开始，所以要-1
            end.set(Integer.parseInt(dateArr[0]),monthNum,1);

            end.add(Calendar.DATE,-1);//回退一天到上个月末
            end.set(Calendar.HOUR_OF_DAY,23);
            end.set(Calendar.MINUTE,59);
            end.set(Calendar.SECOND,59);

            start.set(Calendar.HOUR_OF_DAY,00);
            start.set(Calendar.MINUTE,00);
            start.set(Calendar.SECOND,00);
            map.put("start",start.getTime());
            map.put("end",end.getTime());
        }
        else
        {
            System.out.println("输入的日期格式不合法！");
            return null;
        }
        return map;

    }



    /**
     根据季度数获取季度的开始时间结束时间
     */
    public static Map<String, Date> getQuarterStartEndTime(int year,int quarter) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Map<String, Date> result = new HashMap<>();
        Calendar start = Calendar.getInstance();
        start.set(year,1,1);
        Calendar end = Calendar.getInstance();
        end.set(year,1,1);
        try {
            if (quarter == 1 ) {
                start.set(Calendar.MONTH, 0);
                start.set(Calendar.DATE,1);
                end.set(Calendar.MONTH, 2);
                end.set(Calendar.DATE, 31);
            } else if (quarter == 2) {
                start.set(Calendar.MONTH, 3);
                start.set(Calendar.DATE,1);
                end.set(Calendar.MONTH, 5);
                end.set(Calendar.DATE, 30);
            } else if (quarter == 3) {
                start.set(Calendar.MONTH, 6);
                start.set(Calendar.DATE,1);
                end.set(Calendar.MONTH, 8);
                end.set(Calendar.DATE, 30);
            } else if (quarter == 4) {
                start.set(Calendar.MONTH, 9);
                start.set(Calendar.DATE,1);
                end.set(Calendar.MONTH, 11);
                end.set(Calendar.DATE, 31);
            }
            //这里因为设置了结束时间为23:59:59所以不在把日期延后一天
            /*end.add(Calendar.DATE,1);*/
            end.set(Calendar.HOUR_OF_DAY,23);
            end.set(Calendar.MINUTE,59);
            end.set(Calendar.SECOND,59);

            start.set(Calendar.HOUR_OF_DAY,00);
            start.set(Calendar.MINUTE,00);
            start.set(Calendar.SECOND,00);
            result.put("startTime",start.getTime());
            result.put("endTime",end.getTime());
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }



    /**
     * 判断传入时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param inTime 传入时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static boolean isEffectiveDate(Date inTime, Date startTime, Date endTime) {
        //对比时间戳是否相等，等于开始时间或者结束时间也算是在区间内
        if (inTime.getTime() == startTime.getTime()
                || inTime.getTime() == endTime.getTime()) {
            return true;
        }

        //通过java日历类来对比时间
        Calendar date = Calendar.getInstance();
        date.setTime(inTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}
