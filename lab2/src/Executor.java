import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @(#)Executor.java
 *
 *
 * @author
 * @version 1.00 2011/6/13
 */


class Executor extends Thread {
	private int kPower = 4;
	List<List<Vertex>> levelsOfTrees = new ArrayList();
	

	public synchronized void run() {

		Vertex S = new Vertex(null, null, null, "S");

		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		P1 p1 = new P1(S, cyclicBarrier);
		p1.start();
		try {
			cyclicBarrier.await();

			buildTree(p1);
			buildLevelsOfTree(p1.m_vertex, 0);
			int treeHeight = levelsOfTrees.size() - 1;
			
			System.out.println("IDX ############################### "+treeHeight);
			
			cyclicBarrier = new CyclicBarrier(levelsOfTrees.get(treeHeight).size() + 1);
			A1 a1 = new A1(levelsOfTrees.get(treeHeight).get(0), cyclicBarrier);
			a1.start();
			for (int i = 1; i < levelsOfTrees.get(treeHeight).size() - 1; ++i) {
				A a = new A(levelsOfTrees.get(treeHeight).get(i), cyclicBarrier);
				a.start();
			}
			AN an = new AN(levelsOfTrees.get(treeHeight).get(
					levelsOfTrees.get(treeHeight).size() - 1), cyclicBarrier);
			an.start();

			cyclicBarrier.await();
			for (int i = treeHeight - 1; i > 0; --i) {
				cyclicBarrier = new CyclicBarrier(levelsOfTrees.get(i).size() + 1);
				for (Vertex v : levelsOfTrees.get(i)) {
					A2 a2 = new A2(v, cyclicBarrier);
					a2.start();
				}

				cyclicBarrier.await();
				cyclicBarrier = new CyclicBarrier(levelsOfTrees.get(i).size() + 1);
				for (Vertex v : levelsOfTrees.get(i)) {
					E2 e2 = new E2(v, cyclicBarrier);
					e2.start();
				}
				cyclicBarrier.await();
			}
			cyclicBarrier = new CyclicBarrier(2);
			Aroot aRoot = new Aroot(levelsOfTrees.get(0).get(0), cyclicBarrier);
			aRoot.start();
			cyclicBarrier.await();
			cyclicBarrier = new CyclicBarrier(2);
			Eroot eRoot = new Eroot(levelsOfTrees.get(0).get(0), cyclicBarrier);
			eRoot.start();
			cyclicBarrier.await();

			for (int i = 0; i < treeHeight; ++i) {
				cyclicBarrier = new CyclicBarrier(levelsOfTrees.get(i).size() + 1);
				for (Vertex v : levelsOfTrees.get(i)) {
					BS b = new BS(v, cyclicBarrier);
					b.start();
				}
				cyclicBarrier.await();
			}
			

			for (int i = 0; i < levelsOfTrees.get(treeHeight).size(); ++i) {
				System.out.println("x" + i + "="
						+ levelsOfTrees.get(treeHeight).get(i).m_x[1]);
				//System.out.println("x" + (i + 1) + "="+ levelsOfTrees.get(treeHeight).get(i).m_x[2]);

			}
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildLevelsOfTree(Vertex v, int level){
		if(v == null){
			return;
		}
		if(levelsOfTrees.size()== level){
			levelsOfTrees.add(new ArrayList<Vertex>());
		}
		levelsOfTrees.get(level).add(v);
		buildLevelsOfTree(v.m_left, level+1);
		buildLevelsOfTree(v.m_right, level+1);
	}

	private void buildTree(Production production) {
		Queue<Production> queue = new LinkedList<Production>();
		queue.add(production);
		int k = 1;
		while (!queue.isEmpty()) {
			Iterator<Production> i = queue.iterator();
			List<Production> l = new LinkedList<Production>();
			int size = queue.size();
			CyclicBarrier barrier = new CyclicBarrier(size*2+1);
			while (i.hasNext()) {
				l.add(queue.poll());
			}
			for (Production p : l) {
				if (k < kPower) {
					P2 p2a = new P2(p.m_vertex.m_left, barrier);
					P2 p2b = new P2(p.m_vertex.m_right, barrier);
					p2a.start();
					p2b.start();
					queue.add(p2b);
					queue.add(p2a);
				} else {
					P3 p3a = new P3(p.m_vertex.m_left, barrier);
					P3 p3b = new P3(p.m_vertex.m_right, barrier);
					p3a.start();
					p3b.start();
				}
			}
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++k;
		}
	}
}

