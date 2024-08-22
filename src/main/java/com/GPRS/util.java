package com.GPRS;



import com.example.Entity.power_quality_realtime;
import com.example.Entity.power_realtime;


import java.util.*;

public class util {
    public util() {

    }
    static class CMDSTRUCT {
        private String CMD;
        private String Name;
        private String data;
        private float rate;

        private boolean IsSign;
        public CMDSTRUCT(String CMD, String Name, float rate, boolean IsSign) {
            this.CMD = CMD;
            this.Name = Name;
            this.rate = rate;
            this.IsSign = IsSign;

            }

        public CMDSTRUCT(String CMD, String Name, float rate) {
            this.CMD = CMD;
            this.Name = Name;
            this.rate = rate;
            this.IsSign = false;
        }
        //这里有必要可以加入get、set方法
        public String getCMD() {
            return CMD;
        }


        public double getRate() {
            return rate;
        }

        public String getName() {
            return Name;
        }
    }

//    public static Map<String, String> W_cmd=new LinkedHashMap<String, String>();// cmd--data对
    public static String ammeterID = "01";

    public static List<CMDSTRUCT> cmd_array;
    public static int CMD_NUM=0;


    public static void init_CMD(){

        cmd_array = new ArrayList<CMDSTRUCT>();
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 6A 00 02"),"totalLoad",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 64 00 02"),"aLoad",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 66 00 02"),"bLoad",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 68 00 02"),"cLoad",0.001f,true));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 72 00 02"),"totalReactivePower",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 6C 00 02"),"aReactivePower",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 6E 00 02"),"bReactivePower",0.001f,true));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 70 00 02"),"cReactivePower",0.001f,true));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 7F 00 01"),"totalPowerFactor",0.001f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 7C 00 01"),"aPowerFactor",0.001f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 7D 00 01"),"bPowerFactor",0.001f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 01 7E 00 01"),"cPowerFactor",0.001f));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 61 00 01"),"ua",0.1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 62 00 01"),"ub",0.1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 63 00 01"),"uc",0.1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 64 00 01"),"ia",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 65 00 01"),"ib",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 66 00 01"),"ic",0.01f));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 78 00 01"),"uab",0.1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 79 00 01"),"ubc",0.1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 7A 00 01"),"uca",0.1f));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 E0 00 01"),"iaPHD",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 E1 00 01"),"ibPHD",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 E2 00 01"),"icPHD",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 DD 00 01"),"uaPHD",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 DE 00 01"),"uaPHD",0.01f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 05 DF 00 01"),"uaPHD",0.01f));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 93 00 01"),"iUnbalance",1f));
        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 94 00 01"),"uUnbalance",1f));

        cmd_array.add(new CMDSTRUCT(getCMDResult("03 00 77 00 01"),"frequencyDeiation",0.01f));


        CMD_NUM = cmd_array.size();
    }


    public static void set_Entity(String data, CMDSTRUCT cd, power_quality_realtime pqr, power_realtime pr){
        String name = cd.Name;
        boolean IsSign = cd.IsSign;
        float rate = cd.rate;

        int intdata = DataToInt(IsSign,data);
        float fdata = intdata;
        fdata = (float) (Math.round(fdata * rate * 1000)) / 1000;
        String strdata = Float.toString(fdata);
        System.out.println("这里是转换后的数据：  "+strdata);

        switch(name){
            case "totalLoad": {pr.setTotalload(strdata);}
            case"aLoad":{pr.setAload(strdata);}
            case"bLoad":{pr.setBload(strdata);}
            case"cLoad":{pr.setCload(strdata);}
            case"totalReactivePower":{pr.setTotalreactivepower(strdata);}
            case"aReactivePower":{pr.setAreactivepower(strdata);}
            case"bReactivePower":{pr.setBreactivepower(strdata);}
            case"cReactivePower":{pr.setCreactivepower(strdata);}
            case"totalPowerFactor":{pr.setTotalpowerfactor(strdata);}
            case"aPowerFactor":{pr.setApowerfactor(strdata);}
            case"bPowerFactor":{pr.setBpowerfactor(strdata);}
            case"cPowerFactor":{pr.setCpowerfactor(strdata);}

            case"ua":{pr.setUa(strdata);}
            case"ub":{pr.setUb(strdata);}
            case"uc":{pr.setUc(strdata);}
            case"ia":{pr.setIa(strdata);}
            case"ib":{pr.setIb(strdata);}
            case"ic":{pr.setIc(strdata);}
            case"uab":{pr.setUab(strdata);}
            case"ubc":{pr.setUbc(strdata);}
            case"uca":{pr.setUca(strdata);}


            case"iaPHD":{pqr.setIaphd(strdata);}
            case"ibPHD":{pqr.setIbphd(strdata);}
            case"icPHD":{pqr.setIcphd(strdata);}
            case"uaPHD":{pqr.setUaphd(strdata);}
            case"ubPHD":{pqr.setUbphd(strdata);}
            case"ucPHD":{pqr.setUcphd(strdata);}
            case"iUnbalance":{pqr.setIunbalance(strdata);}
            case"uUnbalance":{pqr.setUunbalance(strdata);}
            case"frequencyDeiation":{
/*               data = frequency
            frequencyDeiation = [(f-fe)/fe]*100%
*/
                float f = fdata;
                float fe = 50;
                float frequencyDeiation;
                System.out.println("这里是读取到的频率" + strdata);

//                pqr.setFrequencydeviation();

            }
        }


    }
    public static int DataToInt(boolean sign,String data){
        if(getCRC16Result(data).equals("0") == false){
            return 0;
        }
        String ammeter = data.substring(0,2); //01
        String cmdtype = data.substring(3,5); //03
        String DATALEN = data.substring(6,8); //02或04
        String strDATA = data.substring(9,data.length()-6); // ** ** 或 ** ** ** **
        String CRC = data.substring(data.length()-5); // ** **

        strDATA = strDATA.replace(" ", "");

        if(sign == false) {  //无符号处理

            int intdata = Integer.parseInt(strDATA, 16);
            return intdata;
        }
        else{ //有符号处理
            int intdata = Long.valueOf(strDATA,16).intValue();
            return intdata;
        }

    }


    public static String bytesToHexString(byte[] bytes) {//16进制整数转为
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }


        return bytes;
    }

    public static String getCRC16Result(String s){
        s = s.replace(" ", "");
        int CRC = 0x0000ffff;
        for (int i=0;i<s.length();i=i+2){
            int CRCL=CRC&0x000000FF;//低八位
            int CRCH=CRC&0x0000FF00;//高八位
            String CRCIn = s.substring(i,i+2);
//            System.out.println(CRCIn);
            int a = Integer.parseInt(CRCIn,16);//待处理数据转16进制
            CRC=CRCH+CRCL^a;
            for(int j=0;j<8;j++){
                if((CRC&0x0001)==0){
                    CRC=CRC>>1;
                }else {
                    CRC>>=1;
                    CRC=CRC^0xA001;
                }
            }
        }
        return Integer.toHexString(CRC);
    }
    public static String getCMDResult(String cmd){
        String CRC = getCRC16Result(ammeterID + " " + cmd);
        while(CRC.length()<4){
            CRC = "0"+CRC;
        }
        String CRC1 = CRC.substring(0, CRC.length()-2);  //起始索引，结束索引
        String CRC2 = CRC.substring(2);        //起始索引
        return ammeterID+ " " +cmd+" "+CRC2+" "+CRC1;
    }
    public static void SetammeterID(String id){
        ammeterID = id;
    }



    //获取10位时间戳
    public static Integer getCurrentTimestamp() {
        return Math.toIntExact(System.currentTimeMillis() / 1000);
    }



    public static void main(String[] args) {

//        System.out.println(getCurrentTimestamp());
//        System.out.println(getCRC16Result("01 03 02 0f ab fc 0b"));
        System.out.println(getCMDResult("03 00 77 00 01"));
        System.out.println(getCurrentTimestamp());
//
//        DataToInt("01 03 02 0f ab fc 0b");


/*
        System.out.println(ammeterID);
        SetammeterID("02");
        System.out.println(ammeterID);
*/

//cmd_array数组使用方法
/*
        init_CMD();
        System.out.println(cmd_array.size());
        for (int i = 0; i < cmd_array.size(); i++) {
            CMD_data cd = cmd_array.get(i);
            System.out.println(cd.getCMD());
        }
*/
/*
        String unsignedstr = "11";
        String signedstr = "81";

        //无符号数处理
        int intdata = Integer.parseInt(unsignedstr,16);
        System.out.println(intdata);
        float floatdata = intdata;
        System.out.println(floatdata*0.01);
        System.out.println();
*/
        //有符号数处理
/*
        int HEX2 = Integer.valueOf("81", 16).byteValue();//如果为1字节 取byteValue
        int HEX4 = Integer.valueOf("ffff", 16).shortValue();//如果为2字节 取shortValue
        int Hex8 = Long.valueOf("80000000",16).intValue();
        System.out.println(HEX2+ "        " + HEX4 + "          " +Hex8);

*/

//        System.out.println(DataToInt(true,"01 03 04 00 00 00 00 fa 33"));




    }




}
