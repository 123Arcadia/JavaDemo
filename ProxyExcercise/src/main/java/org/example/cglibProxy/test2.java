package org.example.cglibProxy;

import java.util.Arrays;
import java.util.Scanner;

public class test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        first = first.substring(1, first.length() - 1);
        String[] fiAr = first.split(",");
        int[] buses = new int[fiAr.length];
        for (int i = 0; i < buses.length; i++) {
            buses[i] = Integer.parseInt(fiAr[i]);
        }
        String second = sc.nextLine();
        second = second.substring(1, second.length() - 1);
        String[] seAr = first.split(",");
        int[] passengers = new int[seAr.length];
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = Integer.parseInt(seAr[i]);
        }
        int capa = Integer.parseInt(sc.nextLine());


        Arrays.sort(buses);
        Arrays.sort(passengers);
        int passengerIdx = 0, busIdx = 0;
        int last = -1;
        while (busIdx < buses.length) {
            int curCapa = capa; // 当前容量
            int busTime = buses[busIdx]; // 触发时间
            while (curCapa > 0 && passengerIdx < passengers.length
             && passengers[passengerIdx] <= busTime) {
                last = passengers[passengerIdx];
                passengerIdx++;
                curCapa--;
            }

            if (curCapa > 0 ||last < busTime ) {
                last = busTime;
                if (busIdx < buses.length - 1 &&  passengerIdx < passengers.length
                        && passengers[passengerIdx] == last) {
                    last--;
                }

            }
            busIdx++;
        }
        while (Arrays.binarySearch(passengers, last) >=0) {
            last--;
        }
        System.out.println(last);
    }
}
