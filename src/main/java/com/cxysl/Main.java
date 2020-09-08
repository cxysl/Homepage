package com.cxysl;

import java.util.Scanner;

public class Main {

    static double[][] rates = { { 0, 0,5000 }, { 5000, 0.03,7910 },
            { 8000, 0.1,16010}, { 17000, 0.2 ,26410},
            { 30000, 0.25,33910}, { 40000, 0.3 ,47910},
            { 60000, 0.35 ,64160}, { 85000, 0.45,64160} };


    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu(){
        double x;       //税前工资
        double sum;     //到手工资
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("1、税前工资算到手工资");
        System.out.println("2、到手工资算税前工资");
        Scanner reader = new Scanner(System.in);
        int i = reader.nextInt();

        if (i==1){
            System.out.print("输入你的税前工资:\t");
            x = reader.nextDouble();
            sum = getSum(x);
            System.out.println("你的到手工资为:\t"+sum);
            menu();
        }

        else if (i==2){
            System.out.print("输入你的到手工资:\t");
            sum = reader.nextDouble();
            x =  getX(sum);
            System.out.println("你的税前工资为:\t"+x);
            menu();
        }

        else {
            System.out.print("输入错误");
            menu();
        }
    }


    //根据税前工资算到手工资
    public static double getSum(double x) {
        double b = 0;
        for (int i = 1; i < rates.length; i++) {
            if (x <= rates[i][0]) {
                break;
            } else if (x > rates[i][0]) {
                if (i==rates.length-1){
                    b = b + (x-rates[i][0])*rates[i][1];
                    System.out.print("1b"+i+":\t"+b);
                }
                else {
                    if(x > rates[i+1][0])      b = b + (rates[i+1][0]-rates[i][0])*rates[i][1];
                    else    b = b + (x-rates[i][0])*rates[i][1];
                    System.out.print("2b"+i+":\t"+b);
                }
                System.out.println("");
            }
        }
        System.out.println("x:"+x+"\t\tb:"+b+"\t\tsum:"+(x-b));
        return x-b;
    }


    //根据到手工资算税前工资
    public static double getX(double sum){
        double b = 0;
        double x = 0;
        if (sum <= 5000) return sum;
        for (int i = 1; i < rates.length; i++){
            if (sum > rates[i-1][2]) {      //本区间 需要交税
                if (sum <= rates[i][2] || i == rates.length-1){       //下区间不用交税 或者 已经是最后一个区间
//                    sum = x - (x-rates[i][0])*rates[i][1] - b;
//                    sum + b = x - (rates[i][1]*x - rates[i][1] *rates[i][0])
//                    sum + b = (1-rates[i][1])* x + rates[i][1] *rates[i][0]
                    x = (sum + b - rates[i][1] *rates[i][0]) / (1-rates[i][1]);
                    System.out.println(i+"x:\t"+x);
                    break;

                }else if(sum > rates[i][2]){       // 下区间也要交税
                    b = b + (rates[i+1][0]-rates[i][0])*rates[i][1];
                    System.out.println("b"+i+":\t"+b);
                }
            } else if(sum <= rates[i-1][2]){
                break;
            }
        }
        return x;
    }

}