package com.nt.service;

public class ArithmaticOperations
{
	public int sum(int x, int y)
	{
		System.out.println("ArithmaticOperations.sum()(start)");
		int z = 0;
		z = x + y;
		System.out.println("ArithmaticOperations.sum()(about to end)");
		return z;
	}

	public int sub(int x, int y)
	{
		System.out.println("ArithmaticOperations.sub()(start)");
		int z = 0;
		z = x - y;
		System.out.println("ArithmaticOperations.sub()(about to end)");
		return z;
	}

	public static void main(String[] args)
	{
		System.out.println("ArithmaticOperations.main()(start)");
		ArithmaticOperations airth = new ArithmaticOperations();
		int result1 = airth.sum(10, 20);
		System.out.println("Sum is :: " + result1);
		int result2 = airth.sub(10, 20);
		System.out.println("Sub :: " + result2);
	}
}
