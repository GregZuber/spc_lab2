class A extends Production {
	A(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {

		System.out.println("A");
		T.m_a[1][1] = -1.0;
		T.m_a[2][1] = 1.0;
		T.m_a[1][2] = 1.0;
		T.m_a[2][2] = -1.0;
		T.m_b[1] = 0.0;
		T.m_b[2] = 0.0;
		return T;
	}
}

class A1 extends Production {
	A1(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {
		System.out.println("A1");
		T.m_a[1][1] = 1.0;
		T.m_a[2][1] = 1.0;
		T.m_a[1][2] = 0.0;
		T.m_a[2][2] = -1.0;
		T.m_b[1] = 0.0;
		T.m_b[2] = 0.0;
		return T;
	}
}

class AN extends Production {
	private int k = 0;
	AN(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}
	
	AN(Vertex Vert, Counter Count, int k) {
		super(Vert, Count);
		this.k = k;
	}

	Vertex apply(Vertex T) {
		System.out.println("AN");
		T.m_a[1][1] = -1.0;
		T.m_a[2][1] = 0.0;
		T.m_a[1][2] = 1.0;
		T.m_a[2][2] = 1.0;
		T.m_b[1] = 0.0;
		//liczba uruchomień tej ostatniej liczby
		T.m_b[2] = 1/Math.pow(2,k-1);
		return T;
	}

}

class A2 extends Production {
	A2(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {
		System.out.println("A2");
		T.m_a[0][0] = T.m_left.m_a[2][2] + T.m_right.m_a[1][1];
		T.m_a[1][0] = T.m_left.m_a[1][2];
		T.m_a[2][0] = T.m_right.m_a[2][1];
		T.m_a[0][1] = T.m_left.m_a[2][1];
		T.m_a[1][1] = T.m_left.m_a[1][1];
		T.m_a[2][1] = 0.0;
		T.m_a[0][2] = T.m_right.m_a[1][2];
		T.m_a[1][2] = 0.0;
		T.m_a[2][2] = T.m_right.m_a[2][2];
		T.m_b[0] = T.m_left.m_b[2] + T.m_right.m_b[1];
		T.m_b[1] = T.m_left.m_b[1];
		T.m_b[2] = T.m_right.m_b[2];
		return T;
	}
}

class E2 extends Production {
	E2(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {
		System.out.println("E2");
		T.m_b[0] /= T.m_a[0][0];
		T.m_a[0][2] /= T.m_a[0][0];
		T.m_a[0][1] /= T.m_a[0][0];
		T.m_a[0][0] /= T.m_a[0][0];
		T.m_b[1] -= T.m_b[0] * T.m_a[1][0];
		T.m_a[1][2] -= T.m_a[0][2] * T.m_a[1][0];
		T.m_a[1][1] -= T.m_a[0][1] * T.m_a[1][0];
		T.m_a[1][0] -= T.m_a[0][0] * T.m_a[1][0];
		T.m_b[2] -= T.m_b[0] * T.m_a[2][0];
		T.m_a[2][2] -= T.m_a[0][2] * T.m_a[2][0];
		T.m_a[2][1] -= T.m_a[0][1] * T.m_a[2][0];
		T.m_a[2][0] -= T.m_a[0][0] * T.m_a[2][0];
		
		return T;
	}
}

// 2.3. Graph-grammar based model of concurrency of the multi-frontal solver
// algorithm

class Aroot extends A2 {
	Aroot(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}
}

class Eroot extends Production {
	Eroot(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {
		System.out.println("Eroot");
		T.m_b[1] /= T.m_a[1][1];
		T.m_a[1][2] /= T.m_a[1][1];
		T.m_a[1][1] /= T.m_a[1][1];
		T.m_b[2] -= T.m_b[1] * T.m_a[2][1];
		T.m_a[2][2] -= T.m_a[1][2] * T.m_a[2][1];
		T.m_a[2][1] -= T.m_a[1][1] * T.m_a[2][1];
		T.m_b[2] /= T.m_a[2][2];
		T.m_a[2][2] /= T.m_a[2][2];
		
		T.m_b[1] -= T.m_b[2] * T.m_a[1][2];
		T.m_a[1][2] -= T.m_a[2][2] * T.m_a[1][2];
		T.m_b[1] /= T.m_a[1][1];
		T.m_a[1][1] /= T.m_a[1][1];
		T.m_b[0] -= T.m_b[2] * T.m_a[0][2];
		T.m_a[0][2] -= T.m_a[2][2] * T.m_a[0][2];
		T.m_b[0] -= T.m_b[1] * T.m_a[0][1];
		T.m_a[0][1] -= T.m_a[1][1] * T.m_a[0][1];
		T.m_b[0] /= T.m_a[0][0];
		T.m_a[0][0] /= T.m_a[0][0];
		
		//System.err.println ("\n"+T.m_a[1][2]);
		
		T.m_x[2] = T.m_b[2] / T.m_a[2][2];
		T.m_x[1] = (T.m_b[1] - T.m_a[1][2] * T.m_x[2]) / T.m_a[1][1];
		T.m_x[0] = (T.m_b[0] - T.m_a[0][1] * T.m_x[1] - T.m_a[0][2] * T.m_x[2]) / T.m_a[0][0];
		
		
		return T;
	}
}

// Finally, we need productions for backward substitution
class BS extends Production {
	BS(Vertex Vert, Counter Count) {
		super(Vert, Count);
	}

	Vertex apply(Vertex T) {
		System.out.println("BS");
		if (T.m_label.equals("node"))
			return T;
		
		/*
		T.m_left.m_a[1][0] = 0.0;

		T.m_left.m_a[1][1] = 1.0;
		T.m_left.m_a[1][2] = 0.0;
		//T.m_left.m_b[1] = T.m_b[1];
		T.m_left.m_x[1] = T.m_x[0];
		
		T.m_left.m_a[2][0] = 0.0;
		T.m_left.m_a[2][1] = 0.0;
		T.m_left.m_a[2][2] = 1.0;
		//T.m_left.m_b[2] = T.m_b[0];
		T.m_left.m_x[2] = T.m_x[1];
		
		////////////////TO USUNAC
		System.err.println ("\n"+T.m_a[0][0]);
		*/
		// tu wszystko na left
		
		T.m_left.m_x[1] = T.m_x[0];
		T.m_left.m_x[2] = T.m_x[1];
		
		T.m_left.m_x[0] = (T.m_left.m_b[0] - T.m_left.m_a[0][1] * T.m_left.m_x[1] - T.m_left.m_a[0][2] * T.m_left.m_x[2]) / T.m_left.m_a[0][0];
		
		/*
		T.m_right.m_a[1][0] = 0.0;
		T.m_right.m_a[1][1] = 1.0;
		T.m_right.m_a[1][2] = 0.0; */
		//T.m_right.m_b[1] = T.m_b[0];
		T.m_right.m_x[1] = T.m_x[1];
		
		/*
		T.m_right.m_a[2][0] = 0.0;
		T.m_right.m_a[2][1] = 0.0;
		T.m_right.m_a[2][2] = 1.0;*/
		T.m_right.m_x[2] = T.m_x[2];
		

		T.m_right.m_x[0] = (T.m_right.m_b[0] - T.m_right.m_a[0][1] * T.m_right.m_x[1] - T.m_right.m_a[0][2] * T.m_right.m_x[2]) / T.m_right.m_a[0][0];
		
		return T;
	}
}

/*
class Executor extends Thread {
public synchronized void run() {
// CONSTRUCTION OF ELIMINATION TREE
// ...
// MULTI-FRONTAL SOLVER ALGORITHM


*/