import java.util.ArrayList;

/**
 * @(#)Executor.java
 * 
 * 
 * @author
 * @version 1.00 2011/6/13
 */

/*
class Executor extends Thread {
	public synchronized void run() {
		Counter counter = new Counter(this);
		Vertex S = new Vertex(null, null, null, "S");
		P1 p1 = new P1(S, counter);
		p1.start();
		counter.release();
		P2 p2a = new P2(p1.m_vertex.m_left, counter);
		P2 p2b = new P2(p1.m_vertex.m_right, counter);
		p2a.start();
		p2b.start();
		counter.release();

		P2 p2c = new P2(p2a.m_vertex.m_left, counter);
		P2 p2d = new P2(p2a.m_vertex.m_right, counter);
		P3 p3a = new P3(p2b.m_vertex.m_left, counter);
		P3 p3b = new P3(p2b.m_vertex.m_right, counter);
		p2c.start();
		p2d.start();
		p3a.start();
		p3b.start();
		counter.release();
		P3 p3c = new P3(p2c.m_vertex.m_left, counter);
		P3 p3d = new P3(p2c.m_vertex.m_right, counter);
		P3 p3e = new P3(p2d.m_vertex.m_left, counter);
		P3 p3f = new P3(p2d.m_vertex.m_right, counter);
		p3c.start();
		p3d.start();
		p3e.start();
		p3f.start();

		A1 localMat1 = new A1(p3c.m_vertex, counter);
		A localMat2 = new A(p3d.m_vertex, counter);
		A localMat3 = new A(p3e.m_vertex, counter);
		A localMat4 = new A(p3f.m_vertex, counter);
		A localMat5 = new A(p3a.m_vertex, counter);
		AN localMat6 = new AN(p3b.m_vertex, counter);
		localMat1.start();
		localMat2.start();
		localMat3.start();
		localMat4.start();
		localMat5.start();
		localMat6.start();
		counter.release();
		A2 mergedMat1 = new A2(p2c.m_vertex, counter);
		A2 mergedMat2 = new A2(p2d.m_vertex, counter);
		A2 mergedMat3 = new A2(p2b.m_vertex, counter);
		mergedMat1.start();
		mergedMat2.start();
		mergedMat3.start();

		counter.release();
		E2 gaussElimMat1 = new E2(p2b.m_vertex, counter);
		E2 gaussElimMat2 = new E2(p2c.m_vertex, counter);
		E2 gaussElimMat3 = new E2(p2d.m_vertex, counter);
		gaussElimMat1.start();
		gaussElimMat2.start();
		gaussElimMat3.start();
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

		for ( int i = 0 ; i < fullElimMat.m_vertex.m_x.length ; i++){
			System.err.println(fullElimMat.m_vertex.m_x[i]);
		}
		
		System.err.println();
		
		for ( int i = 0 ; i < backSub4.m_vertex.m_x.length ; i++){
			System.err.println(backSub4.m_vertex.m_left.m_x[i]);
			//System.err.println(backSub4.m_vertex.m_left.m_x[i]);
		}
		
		System.err.println();
		
		
	}
}

*/
