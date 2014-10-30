import java.util.concurrent.CyclicBarrier;


class A extends Production {

	A(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	@Override
	Vertex apply(Vertex vert) {
		System.out.println("A");
		vert.m_a[1][1] = -1.0;
		vert.m_a[2][1] = 1.0;
		vert.m_a[1][2] = 1.0;
		vert.m_a[2][2] = -1.0;
		vert.m_b[1] = 0.0;
		vert.m_b[2] = 0.0;
		return vert;
	}

}

class A1 extends Production {
	A1(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex vert) {
		System.out.println("A");
		vert.m_a[1][1] = 1.0;
		vert.m_a[2][1] = 1.0;
		vert.m_a[1][2] = 0.0;
		vert.m_a[2][2] = -1.0;
		vert.m_b[1] = 0.0;
		vert.m_b[2] = 0.0;
		return vert;
	}
}

class AN extends Production {
	AN(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex T) {
		System.out.println("AN");
		T.m_a[1][1] = -1.0;
		T.m_a[2][1] = 0.0;
		T.m_a[1][2] = 1.0;
		T.m_a[2][2] = 1.0;
		T.m_b[1] = 0.0;
		T.m_b[2] = 1.0;
		return T;
	}
}

class A2 extends Production {
	A2(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
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
	E2(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
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

class Aroot extends A2 {

	Aroot(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

}

class Eroot extends Production {
	Eroot(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	Vertex apply(Vertex T) {
		System.out.println("Eroot");

		// T.m_x = GaussianElimination.lsolve(T.m_a, T.m_b);

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

		T.m_x[2] = T.m_b[2];// T.m_b[2] / T.m_a[2][2];
		T.m_x[1] = T.m_b[1];// (T.m_b[1] - T.m_a[1][2] * T.m_x[2])/T.m_a[1][1];
		T.m_x[0] = T.m_b[0];// (T.m_b[0] - T.m_a[0][1] * T.m_x[1] - T.m_a[0][2]
							// * T.m_x[2])/T.m_a[0][0];

		return T;
	}
}

class BS extends Production {
	BS(Vertex vert, CyclicBarrier barrier) {
		super(vert, barrier);
	}

	@Override
	Vertex apply(Vertex T) {
		System.out.println("BS");
		if (T.m_label.equals("node"))
			return T;

		T.m_left.m_x[1] = T.m_x[1];
		T.m_left.m_x[2] = T.m_x[0];

		T.m_left.m_x[0] = (T.m_left.m_b[0] - T.m_left.m_a[0][1]
				* T.m_left.m_x[1] - T.m_left.m_a[0][2] * T.m_left.m_x[2])
				/ T.m_left.m_a[0][0];

		T.m_right.m_x[1] = T.m_x[0];
		T.m_right.m_x[2] = T.m_x[2];

		T.m_right.m_x[0] = (T.m_right.m_b[0] - T.m_right.m_a[0][1]
				* T.m_right.m_x[1] - T.m_right.m_a[0][2] * T.m_right.m_x[2])
				/ T.m_right.m_a[0][0];

		return T;
	}
}
