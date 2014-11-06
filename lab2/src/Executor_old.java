import java.util.ArrayList;

/**
 * @(#)Executor.java
 *
 *
 * @author
 * @version 1.00 2011/6/13
 */

/*


class Executor_old extends Thread {
 public synchronized void run() {
	try{
	 int k = 4;
	 
	Counter counter = new Counter(this);
	Vertex S = new Vertex(null,null,null,"S");
	P1 p1 = new P1(S,counter);
	p1.start();
	counter.release();
	
	int max = (int) Math.pow(2, k);
	
	P2 p2a = new P2(p1.m_vertex.m_left,counter);
	P2 p2b = new P2(p1.m_vertex.m_right,counter);
	p2a.start();
	p2b.start();
	counter.release();
	
	ArrayList<P2> list = new ArrayList<P2>();
	
	ArrayList<P2> tempList = new ArrayList<P2>();
	
	tempList.add(p2a);
	tempList.add(p2b);
	
	for (int i = 0 ; i < k - 1 ; i++){
		list = (ArrayList<P2>) tempList.clone();
		System.out.println("Rozmiar listy "+ list.size());
		tempList.clear();
		int licznik = 0;
		for (P2 e: list){
			System.out.println("Iterator1 "+ licznik);
			P2 p2 = new P2(e.m_vertex.m_left,counter);
			tempList.add(p2);
			p2.start();
			p2 = new P2(e.m_vertex.m_right,counter);
			tempList.add(p2);
			p2.start();
			
			/*
			Runtime runtime = Runtime.getRuntime();
			runtime.freeMemory();
			*/

/*
			licznik++;
		}
		
		counter.release();
		
	}
	
	int i = 0;
	for (P2 e : tempList){
		System.out.println("Wierzcholek P3 "+ i);
		i++;
		P3 p3 = new P3(e.m_vertex.m_left,counter);
		p3.start();
		p3 = new P3(e.m_vertex.m_right,counter);
		p3.start();
		
	}
	
	System.out.println("Ilosc wierzcholkow "+i);
	
	//counter.release();
	} catch (Exception e){
		
	}
	
 
 }
}
*/

/*

A1 localMat1 = new A1(p3c.m_vertex, counter);
A localMat2 = new A(p3d.m_vertex, counter);
A localMat3 = new A(p3e.m_vertex, counter);
A localMat4 = new A(p3f.m_vertex, counter);
A localMat5 = new A(p3a.m_vertex, counter);
AN localMat6 = new AN(p3b.m_vertex, counter);
localMat1.start(); localMat2.start(); localMat3.start();
localMat4.start(); localMat5.start(); localMat6.start();
counter.release();
A2 mergedMat1 = new A2(p2c.m_vertex, counter);
A2 mergedMat2 = new A2(p2d.m_vertex, counter);
A2 mergedMat3 = new A2(p2b.m_vertex, counter);
mergedMat1.start(); mergedMat2.start(); mergedMat3.start();

counter.release();
E2 gaussElimMat1 = new E2(p2b.m_vertex, counter);
E2 gaussElimMat2 = new E2(p2c.m_vertex, counter);
E2 gaussElimMat3 = new E2(p2d.m_vertex, counter);
gaussElimMat1.start(); gaussElimMat2.start(); gaussElimMat3.start();
counter.release();
A2 mergedMat4 = new A2(p2a.m_vertex, counter);
mergedMat4.start();
counter.release();
E2 gaussElimMat4 = new E2(p2a.m_vertex, counter);
gaussElimMat4.start();
counter.release();
Aroot mergedRootMat = new Aroot(p1.m_vertex, counter);
mergedRootMat.start();
counter.release();
Eroot fullElimMat = new Eroot(p1.m_vertex, counter);
fullElimMat.start();
counter.release();
BS backSub1 = new BS(p1.m_vertex, counter);
BS backSub2 = new BS(p2a.m_vertex, counter);
backSub1.start();
backSub2.start();
counter.release();

BS backSub3 = new BS(p2c.m_vertex, counter);
BS backSub4 = new BS(p2d.m_vertex, counter);
backSub3.start();
backSub4.start();
counter.release();

*/