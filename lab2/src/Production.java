import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @(#)Production.java
 * 
 * 
 * @author
 * @version 1.00 2011/6/13
 */

abstract class Production extends Thread {

	// vertex where the production will be applied
	Vertex m_vertex;
	// graph drawer
	GraphDrawer m_drawer;
	// productions counter
	CyclicBarrier m_barrier;

	Production(Vertex Vert, CyclicBarrier barrier) {
		m_vertex = Vert;
		m_barrier = barrier;
		m_drawer = new GraphDrawer();
	}

	// returns first vertex from the left
	abstract Vertex apply(Vertex v);

	// run the thread
	public void run() {
		//apply the production
		m_vertex = apply(m_vertex);
		//plot the graph
		m_drawer.draw(m_vertex);
		try {
			m_barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P1 extends Production {
	P1(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex S) {
		System.out.println("p1");
		Vertex T1 = new Vertex(null, null, S, "T");
		Vertex T2 = new Vertex(null, null, S, "T");
		S.set_left(T1);
		S.set_right(T2);
		S.set_label("root");
		return S;
	}
}

class P2 extends Production {
	P2(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex vert) {
		System.out.println("p2");
		Vertex t1 = new Vertex(null, null, vert, "T");
		Vertex t2 = new Vertex(null, null, vert, "T");
		vert.set_left(t1);
		vert.set_right(t2);
		vert.set_label("int");
		return vert;
	}
}

class P3 extends Production {
	P3(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex vert) {
		System.out.println("p3");
		Vertex t1 = new Vertex(null, null, vert, "node");
		Vertex t2 = new Vertex(null, null, vert, "node");
		vert.set_left(t1);
		vert.set_right(t2);
		vert.set_label("int");
		return vert;
	}
}
