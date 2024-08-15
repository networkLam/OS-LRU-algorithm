package com.os;

import java.util.Arrays;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//user must enter two message divide is memory how much divide for memory and (page stream)页面走向 
		Scanner s1 = new Scanner(System.in); //输入
		Scanner s2 = new Scanner(System.in);
		double change_page = 0; //置换次数
		double deficiency_page = 0; //缺页次数
		System.out.println("请输入内存块数(正整数类型):");
		int memory_block = s2.nextInt();
		int[] memory = new int[memory_block]; //内存块数
		int[] closest = new int[memory_block]; //记录最近出现的位置
		for(int i = 0 ; i < memory.length; i++) {//初始化内存块中的数据和最近出现的位置
			     memory[i] = -1;
			     closest[i] = -1;
		}
		System.out.println("请输入页面走向:");
		String user_enter = s1.nextLine();
		String[] temp_line = user_enter.split(" ");//对字符串进行分割
		int[] page_stream = new int[temp_line.length];
		for(int i = 0 ; i <temp_line.length; i++ ) { //string 类型转成 int类型 
			try {
				page_stream[i] = Integer.parseInt(temp_line[i]);
			}catch(Exception e){
				System.out.print("输入的内容有误可能包括非数字，请检查。");
				System.out.println(e.getMessage());
				System.out.print("程序结束");
				System.exit(0);
			}
		}
		//System.out.println(Arrays.toString(page_stream));
		for(int i = 0; i < page_stream.length ;i ++) {
			int k = page_stream[i];
			int flag = 1;
			for(int j = 0 ; j < memory.length;j++) {
				if(k == memory[j]) {
					//既没有缺页 也没有置换
					System.out.println("内存中有页面"+k+"在内存块中"+j+"的位置上，无需缺页中断");
					flag = 0;
					break;
				}else if(memory[j] == -1) {
					System.out.println("发生缺页中断但是内存中有空闲区"+k+"直接调入");
					deficiency_page++; //发生缺页(空内存块新装页面)
					memory[j] = k;
					flag = 0;
					break;
				}
			}
			//没有命中
			if(flag == 1) {
				//生成一个closest并找到最大值
				for(int m = 0 ; m < memory.length; m++) {
					int count = 0;
					for(int j = i; j >=0; j-- ) {
						if(memory[m]==page_stream[j]) {
							closest[m] = count;
							break;
						}
						count++;
					}
				}
				System.out.println("memory status"+Arrays.toString(memory));
				//System.out.println("closest"+Arrays.toString(closest));
				//说明缺页了 需要置换
				int maxIndex = findMaxIndex(closest);
				//System.out.println("maxIndex:" + maxIndex);
				System.out.println("发生缺页中断"+memory[maxIndex]+"页面调出,"+k+"页面调入.");
				memory[maxIndex] = k;
				change_page++; //置换次数加一
				deficiency_page++;//缺页次数加一
			}
		}
		System.out.println("memory status"+Arrays.toString(memory));
		System.out.println("一共发生"+(int)deficiency_page+"次缺页和"+(int)change_page+"次页面置换,缺页率为"+ (deficiency_page / page_stream.length)*100+"%。");
	}
	//找数组中最大值的索引
	public static int findMaxIndex(int[] arr) {
		int maxValue = arr[0];
		int MaxIndex = 0;
		for(int i = 1 ; i < arr.length ; i ++) {
			if(maxValue < arr[i]) {
				maxValue = arr[i];
				MaxIndex = i;
			}
		}
		return MaxIndex;
	}
}
